package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
import in.org.cris.cmm.sdms.dto.MaintenanceDefectTxnDTO;
import in.org.cris.cmm.sdms.dto.MaintenanceDefectTxnRequestDTO;
import in.org.cris.cmm.sdms.dto.MaintenanceDefectTxnResponseDTO;
import in.org.cris.cmm.sdms.entity.*;
import in.org.cris.cmm.sdms.repo.*;
import in.org.cris.cmm.sdms.entity.*;
import in.org.cris.cmm.sdms.repo.*;
import in.org.cris.cmm.sdms.service.MaintenanceDefectTxnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
                        entity = repository.findById(dto.getDefectTxnId()).orElseThrow(() -> new RuntimeException("Defect Txn not found: " + dto.getDefectTxnId()));
                        entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setUpdatedAt(new Date());

                } else {
                        entity = new MaintenanceDefectTxn();
                        entity.setValidFlag(true);
                        entity.setCreatedAt(new Date());
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                }
                if (dto.getMaintenanceId() != null) {
                        MaintenanceDetails md = detailsRepo.findById(dto.getMaintenanceId()).orElseThrow(() -> new RuntimeException("Maintenance details not found"));
                        entity.setMaintenanceDetails(md);
                }
                if (dto.getDefectId() != null) {
                        MaintenanceDefectMaster d = defectRepo.findById(dto.getDefectId()).orElseThrow(() -> new RuntimeException("Defect not found"));
                        entity.setDefect(d);
                }
                if (dto.getActionId() != null) {
                        MaintenanceActionMaster action = actionRepo.findById(dto.getActionId()).orElseThrow(() -> new RuntimeException("Action not found"));
                        entity.setAction(action);
                }
                if (dto.getChecklistTxnId() != null) {
                        MaintenanceChecklistTxn txn = checklistRepo.findById(dto.getChecklistTxnId()).orElseThrow(() -> new RuntimeException("Checklist txn not found"));
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

        @Override
        public List<MaintenanceDefectTxn> getMaintenanceDefects(Long defectTxnId, Long maintenanceId, Long defectId, Long actionId, Long checklistTxnId) {
                return repository.searchAll(defectTxnId, maintenanceId, defectId, actionId, checklistTxnId);
        }

        @Override
        public List<MaintenanceDefectTxnResponseDTO>
        saveOrUpdateDefects(List<MaintenanceDefectTxnRequestDTO> dtoList) {

                String user = authenticationFacade.getLoggedInUser().getUser_name();
                List<MaintenanceDefectTxnResponseDTO> responseList = new ArrayList<>();

                for (MaintenanceDefectTxnRequestDTO dto : dtoList) {

                        Optional<MaintenanceDefectTxn> optionalTxn = dto.getDefectTxnId() != null ? repository.findById(dto.getDefectTxnId()) : Optional.empty();
                        MaintenanceDefectTxn entity;
                        if (optionalTxn.isPresent()) {
                                // UPDATE
                                entity = optionalTxn.get();
                                entity.setUpdatedBy(user);
                                entity.setUpdatedAt(new Date());
                        } else {
                                // INSERT
                                entity = new MaintenanceDefectTxn();
                                entity.setCreatedBy(user);
                                entity.setCreatedAt(new Date());
                                entity.setValidFlag(true);

                                if (dto.getMaintenanceId() != null) entity.setMaintenanceDetails(detailsRepo.getReferenceById(dto.getMaintenanceId()));
                                if (dto.getDefectId() != null) entity.setDefect(defectRepo.getReferenceById(dto.getDefectId()));
                        }

                        // optional references
                        if (dto.getActionId() != null)
                                entity.setAction(actionRepo.getReferenceById(dto.getActionId()));

                        if (dto.getChecklistTxnId() != null)
                                entity.setChecklistTxn(checklistRepo.getReferenceById(dto.getChecklistTxnId()));

                        // non-null updates
                        if (dto.getMaterialDemandId() != null)
                                entity.setMaterialDemandId(dto.getMaterialDemandId());

                        if (dto.getRemarks() != null)
                                entity.setRemarks(dto.getRemarks());

                        if (dto.getStatus() != null)
                                entity.setStatus(dto.getStatus());

                        if (dto.getValidFlag() != null)
                                entity.setValidFlag(dto.getValidFlag());

                        MaintenanceDefectTxn saved = repository.save(entity);

                        // map to response DTO
                        MaintenanceDefectTxnResponseDTO res = new MaintenanceDefectTxnResponseDTO();
                        res.setDefectTxnId(saved.getDefectTxnId());
                        res.setMaintenanceId(saved.getMaintenanceDetails() == null ? null : saved.getMaintenanceDetails().getMaintenanceId());
                        res.setDefectId(saved.getDefect() == null ? null : saved.getDefect().getDefectId());
                        res.setActionId(saved.getAction() == null ? null : saved.getAction().getActionId());
                        res.setChecklistTxnId(saved.getChecklistTxn() == null ? null : saved.getChecklistTxn().getChecklistTxnId());
                        res.setMaterialDemandId(saved.getMaterialDemandId());
                        res.setRemarks(saved.getRemarks());
                        res.setStatus(saved.getStatus());
                        res.setValidFlag(saved.getValidFlag());
                        res.setCreatedBy(saved.getCreatedBy());
                        res.setUpdatedBy(saved.getUpdatedBy());
                        res.setCreatedAt(saved.getCreatedAt());
                        res.setUpdatedAt(saved.getUpdatedAt());

                        responseList.add(res);
                }

                return responseList;
        }
}
