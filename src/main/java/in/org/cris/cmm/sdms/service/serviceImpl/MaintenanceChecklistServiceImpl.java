package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
import in.org.cris.cmm.sdms.dto.MaintenanceChecklistDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceChecklistMaster;
import in.org.cris.cmm.sdms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.sdms.repo.MaintenanceChecklistRepository;
import in.org.cris.cmm.sdms.repo.MasterRsTypeMaintenanceRepository;
import in.org.cris.cmm.sdms.service.MaintenanceChecklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                        entity = checklistRepository.findById(dto.getChecklistId())
                                .orElseThrow(() -> new RuntimeException("Checklist not found: " + dto.getChecklistId()));

                        entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setUpdatedAt(new Date());

                } else {
                        entity = new MaintenanceChecklistMaster();
                        entity.setValidFlag(true);
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setCreatedAt(new Date());
                }

                if (dto.getRsTypeMaintenanceId() != null) {
                        MasterRsTypeMaintenance ref =
                                masterRsTypeMaintenanceRepository.findById(dto.getRsTypeMaintenanceId())
                                        .orElseThrow(() -> new RuntimeException("RS Type not found"));
                        entity.setRsTypeMaintenance(ref);
                }

                if (dto.getSafetyFlag() != null) entity.setSafetyFlag(dto.getSafetyFlag());
                if (dto.getItemNo() != null) entity.setItemNo(dto.getItemNo());
                if (dto.getChecklistItem() != null) entity.setChecklistItem(dto.getChecklistItem());
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
}
