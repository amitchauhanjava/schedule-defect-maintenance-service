package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
import in.org.cris.cmm.sdms.dto.MaintenanceChecklistTxnDTO;
import in.org.cris.cmm.sdms.dto.MaintenanceChecklistTxnRequestDTO;
import in.org.cris.cmm.sdms.dto.MaintenanceChecklistTxnResponseDTO;
import in.org.cris.cmm.sdms.entity.*;
import in.org.cris.cmm.sdms.repo.*;
import in.org.cris.cmm.sdms.service.MaintenanceChecklistTxnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
                        entity = repository.findById(dto.getChecklistTxnId()).orElseThrow(() -> new RuntimeException("Checklist Txn not found: " + dto.getChecklistTxnId()));

                        entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setUpdatedAt(new Date());
                } else {
                        entity = new MaintenanceChecklistTxn();
                        entity.setValidFlag(true);
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setCreatedAt(new Date());
                }

                        entity.getMaintenanceDetails().setMaintenanceId(dto.getMaintenanceId());

                if (dto.getChecklistId() != null) {
                        MaintenanceChecklistMaster cm = checklistRepo.findById(dto.getChecklistId()).orElseThrow(() -> new RuntimeException("Checklist not found"));
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

        @Override
        public List<MaintenanceChecklistTxnResponseDTO>
        saveOrUpdateChecklist(List<MaintenanceChecklistTxnRequestDTO> dtoList) {

                String user = authenticationFacade.getLoggedInUser().getUser_name();

                List<MaintenanceChecklistTxnResponseDTO> responseList = new ArrayList<>();

                for (MaintenanceChecklistTxnRequestDTO dto : dtoList) {

                        Optional<MaintenanceChecklistTxn> optionalTxn = repository.findByMaintenanceDetails_MaintenanceIdAndChecklistMaster_ChecklistId(dto.getMaintenanceId(), dto.getChecklistId());

                        MaintenanceChecklistTxn entity;

                        if (optionalTxn.isPresent()) {
                                entity = optionalTxn.get();
                                entity.setUpdatedBy(user);
                                entity.setUpdatedAt(new Date());
                        } else {
                                entity = new MaintenanceChecklistTxn();
                                entity.setCreatedBy(user);
                                entity.setCreatedAt(new Date());
                                entity.setValidFlag(true);
                                dto.setMaintenanceId(entity.getMaintenanceDetails() != null ? entity.getMaintenanceDetails().getMaintenanceId() : null);
                                entity.setChecklistMaster(checklistRepo.getReferenceById(dto.getChecklistId()));
                        }
                        if (dto.getPreValue() != null) entity.setPreValue(dto.getPreValue());
                        if (dto.getPostValue() != null) entity.setPostValue(dto.getPostValue());
                        if (dto.getRemarks() != null) entity.setRemarks(dto.getRemarks());

                        entity.setValidFlag(true);
                        MaintenanceChecklistTxn saved = repository.save(entity);

                        // 🔹 Map Entity → Response DTO
                        MaintenanceChecklistTxnResponseDTO res = new MaintenanceChecklistTxnResponseDTO();
                        res.setChecklistTxnId(saved.getChecklistTxnId());
//                        res.setMaintenanceId(saved.getMaintenanceDetails().getMaintenanceId());
                        res.setMaintenanceId(saved.getMaintenanceDetails() != null ? saved.getMaintenanceDetails().getMaintenanceId() : null );
                        res.setChecklistId(saved.getChecklistMaster().getChecklistId());
                        res.setPreValue(saved.getPreValue());
                        res.setPostValue(saved.getPostValue());
                        res.setRemarks(saved.getRemarks());
                        res.setValidFlag(saved.getValidFlag());
                        res.setCreatedBy(saved.getCreatedBy());
                        res.setUpdatedBy(saved.getUpdatedBy());
                        res.setCreatedAt(saved.getCreatedAt());
                        res.setUpdatedAt(saved.getUpdatedAt());

                        responseList.add(res);
                }

                return responseList;
        }

        @Override
        public List<MaintenanceChecklistTxn> getActiveChecklistTxn(
                Long checklistTxnId,
                Long maintenanceId,
                Long checklistId) {

                return repository.findActiveChecklistTxn(
                        checklistTxnId,
                        maintenanceId,
                        checklistId
                );
        }

}
