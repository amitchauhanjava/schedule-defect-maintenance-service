package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.CopyChecklistRequestDTO;
import in.org.cris.cmm.rms.dto.MaintenanceChecklistDTO;
import in.org.cris.cmm.rms.entity.MaintenanceChecklistMaster;
import in.org.cris.cmm.rms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.rms.repo.MaintenanceChecklistRepository;
import in.org.cris.cmm.rms.repo.MasterRsTypeMaintenanceRepository;
import in.org.cris.cmm.rms.service.MaintenanceChecklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceChecklistServiceImpl implements MaintenanceChecklistService {

        private final MaintenanceChecklistRepository checklistRepository;
        private final MasterRsTypeMaintenanceRepository masterRsTypeMaintenanceRepository;
        private final AuthenticationFacade authenticationFacade;

        @Override
        public MaintenanceChecklistMaster saveOrUpdate(MaintenanceChecklistDTO dto) {

                MaintenanceChecklistMaster entity;

                if (dto.getChecklistId() != null) {
                        // Update existing
                        entity = checklistRepository.findById(dto.getChecklistId()).orElseThrow(() -> new RuntimeException("Checklist not found: " + dto.getChecklistId()));
                        entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setUpdatedAt(new Date());
                } else {
                        // Create new
                        entity = new MaintenanceChecklistMaster();
                        entity.setValidFlag(true);
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setCreatedAt(new Date());
                }

                // RS Type Maintenance reference
                if (dto.getRsTypeMaintenanceId() != null) {
                        MasterRsTypeMaintenance ref = masterRsTypeMaintenanceRepository.findById(dto.getRsTypeMaintenanceId()).orElseThrow(() -> new RuntimeException("RS Type not found: " + dto.getRsTypeMaintenanceId()));
                        entity.setRsTypeMaintenance(ref);
                }

                // Set other fields if present in DTO
                if (dto.getSafetyFlag() != null) entity.setSafetyFlag(dto.getSafetyFlag());
                if (dto.getSlNo() != null) entity.setSlNo(dto.getSlNo());
                if (dto.getAssembly() != null) entity.setAssembly(dto.getAssembly());
                if (dto.getSubAssembly() != null) entity.setSubAssembly(dto.getSubAssembly());
                if (dto.getItemNo() != null) entity.setItemNo(dto.getItemNo());
                if (dto.getChecklistItem() != null) entity.setChecklistItem(dto.getChecklistItem());
                if (dto.getCoachKind() != null) entity.setCoachKind(dto.getCoachKind());
                if (dto.getUtilityType() != null) entity.setUtilityType(dto.getUtilityType());
                if (dto.getMandCondition() != null) entity.setMandCondition(dto.getMandCondition());
                if (dto.getMethod() != null) entity.setMethod(dto.getMethod());

                return checklistRepository.save(entity);
        }

        @Override
        public String delete(Long id) {
                MaintenanceChecklistMaster entity = checklistRepository.findById(id).orElseThrow(() -> new RuntimeException("Checklist not found: " + id));

                entity.setValidFlag(false);
                entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                entity.setUpdatedAt(new Date());

                checklistRepository.save(entity);
                return "Checklist deleted successfully";
        }

        @Override
        public List<MaintenanceChecklistMaster> getAllValidChecklistItems() {
                return checklistRepository.findByValidFlagTrueOrderByChecklistIdAsc();
        }

        @Override
        public String copyChecklistItems(CopyChecklistRequestDTO dto) {

                List<MaintenanceChecklistMaster> oldRecords = checklistRepository.findByChecklistId(dto.getOldRsTypeMaintenanceId());
                if (oldRecords.isEmpty()) {
                        return "No records found for Old RS Type ID = " + dto.getOldRsTypeMaintenanceId();
                }

                List<MaintenanceChecklistMaster> saveList = new ArrayList<>();
                for (Long newRsTypeId : dto.getNewRsTypeMaintenanceIds()) {
                        MasterRsTypeMaintenance newRsType = masterRsTypeMaintenanceRepository.findById(newRsTypeId)
                                .orElseThrow(() -> new RuntimeException("New RS Type not found: " + newRsTypeId));

                        for (MaintenanceChecklistMaster old : oldRecords) {
                                MaintenanceChecklistMaster copy = new MaintenanceChecklistMaster();

                                // Copy fields
                                copy.setRsTypeMaintenance(newRsType);
                                copy.setSafetyFlag(old.getSafetyFlag());
                                copy.setSlNo(old.getSlNo());
                                copy.setAssembly(old.getAssembly());
                                copy.setSubAssembly(old.getSubAssembly());
                                copy.setItemNo(old.getItemNo());
                                copy.setChecklistItem(old.getChecklistItem());
                                copy.setCoachKind(old.getCoachKind());
                                copy.setUtilityType(old.getUtilityType());
                                copy.setMandCondition(old.getMandCondition());
                                copy.setMethod(old.getMethod());
                                copy.setValidFlag(true);

                                // Audit fields
                                copy.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                                copy.setCreatedAt(new Date());

                                saveList.add(copy);
                        }
                }

                checklistRepository.saveAll(saveList);

                return "Copied " + saveList.size() + " checklist records from RS Type "
                        + dto.getOldRsTypeMaintenanceId() + " to New RS Types.";
        }

        @Override
        public List<MaintenanceChecklistMaster> getChecklist(Long rsTypeMaintenanceId, String coachKind, String utilityType) {
                return checklistRepository.findByFilters(rsTypeMaintenanceId, coachKind, utilityType);
        }

}
