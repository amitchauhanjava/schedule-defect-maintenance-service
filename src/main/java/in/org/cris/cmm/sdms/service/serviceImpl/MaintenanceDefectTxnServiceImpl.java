package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
import in.org.cris.cmm.sdms.dto.MaintenanceDefectTxnDTO;
import in.org.cris.cmm.sdms.entity.*;
import in.org.cris.cmm.sdms.repo.*;
import in.org.cris.cmm.sdms.service.MaintenanceDefectTxnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceDefectTxnServiceImpl implements MaintenanceDefectTxnService {

        private final MaintenanceDefectTxnRepository repository;
        private final MaintenanceDetailsRepository detailsRepo;
        private final MaintenanceDefectRepository defectRepo;
        private final MaintenanceActionRepository actionRepo;
        private final MaintenanceChecklistTxnRepository checklistRepo;
        private final AuthenticationFacade authenticationFacade;

        @Override
        public MaintenanceDefectTxn saveOrUpdate(MaintenanceDefectTxnDTO dto) {

                MaintenanceDefectTxn entity;

                if (dto.getDefectTxnId() != null) {
                        entity = repository.findById(dto.getDefectTxnId())
                                .orElseThrow(() -> new RuntimeException("Defect Txn not found: " + dto.getDefectTxnId()));

                        entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setUpdatedAt(new Date());

                } else {
                        entity = new MaintenanceDefectTxn();
                        entity.setValidFlag(true);
                        entity.setCreatedAt(new Date());
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                }

                if (dto.getMaintenanceId() != null) {
                        MaintenanceDetails md = detailsRepo.findById(dto.getMaintenanceId())
                                .orElseThrow(() -> new RuntimeException("Maintenance details not found"));
                        entity.setMaintenanceDetails(md);
                }

                if (dto.getDefectId() != null) {
                        MaintenanceDefectMaster d = defectRepo.findById(dto.getDefectId())
                                .orElseThrow(() -> new RuntimeException("Defect not found"));
                        entity.setDefect(d);
                }

                if (dto.getActionId() != null) {
                        MaintenanceActionMaster action = actionRepo.findById(dto.getActionId())
                                .orElseThrow(() -> new RuntimeException("Action not found"));
                        entity.setAction(action);
                }

                if (dto.getChecklistTxnId() != null) {
                        MaintenanceChecklistTxn txn = checklistRepo.findById(dto.getChecklistTxnId())
                                .orElseThrow(() -> new RuntimeException("Checklist txn not found"));
                        entity.setChecklistTxn(txn);
                }

                entity.setMaterialDemandId(dto.getMaterialDemandId());
                entity.setRemarks(dto.getRemarks());
                entity.setStatus(dto.getStatus());

                return repository.save(entity);
        }

        @Override
        public String delete(Long id) {

                MaintenanceDefectTxn entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Defect Txn not found: " + id));
                entity.setValidFlag(false);
                entity.setUpdatedAt(new Date());
                entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());

                repository.save(entity);
                return "Record deleted successfully";
        }

        @Override
        public List<MaintenanceDefectTxn> getAllValidDefectTxns() {
                return repository.findByValidFlagTrueOrderByDefectTxnIdAsc();
        }
}
