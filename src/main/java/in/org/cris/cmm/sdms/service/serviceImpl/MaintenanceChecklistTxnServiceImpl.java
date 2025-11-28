package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
import in.org.cris.cmm.sdms.dto.MaintenanceChecklistTxnDTO;
import in.org.cris.cmm.sdms.entity.*;
import in.org.cris.cmm.sdms.repo.*;
import in.org.cris.cmm.sdms.service.MaintenanceChecklistTxnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceChecklistTxnServiceImpl implements MaintenanceChecklistTxnService {

        private final MaintenanceChecklistTxnRepository repository;
        private final MaintenanceDetailsRepository detailsRepo;
        private final MaintenanceChecklistRepository checklistRepo;
        private final AuthenticationFacade authenticationFacade;

        @Override
        public MaintenanceChecklistTxn saveOrUpdate(MaintenanceChecklistTxnDTO dto) {

                MaintenanceChecklistTxn entity;

                if (dto.getChecklistTxnId() != null) {
                        entity = repository.findById(dto.getChecklistTxnId())
                                .orElseThrow(() -> new RuntimeException("Checklist Txn not found: " + dto.getChecklistTxnId()));

                        entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setUpdatedAt(new Date());
                } else {
                        entity = new MaintenanceChecklistTxn();
                        entity.setValidFlag(true);
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setCreatedAt(new Date());
                }

                if (dto.getMaintenanceId() != null) {
                        MaintenanceDetails md = detailsRepo.findById(dto.getMaintenanceId())
                                .orElseThrow(() -> new RuntimeException("Maintenance details not found"));
                        entity.setMaintenanceDetails(md);
                }

                if (dto.getChecklistId() != null) {
                        MaintenanceChecklistMaster cm = checklistRepo.findById(dto.getChecklistId())
                                .orElseThrow(() -> new RuntimeException("Checklist not found"));
                        entity.setChecklistMaster(cm);
                }

                entity.setPreValue(dto.getPreValue());
                entity.setPostValue(dto.getPostValue());
                entity.setRemarks(dto.getRemarks());

                return repository.save(entity);
        }

        @Override
        public String delete(Long id) {

                MaintenanceChecklistTxn entity = repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Checklist Txn not found: " + id));

                entity.setValidFlag(false);
                entity.setUpdatedAt(new Date());
                entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());

                repository.save(entity);

                return "Record deleted successfully";
        }

        @Override
        public List<MaintenanceChecklistTxn> getAllValidChecklistTxns() {
                return repository.findByValidFlagTrueOrderByChecklistTxnIdAsc();
        }
}
