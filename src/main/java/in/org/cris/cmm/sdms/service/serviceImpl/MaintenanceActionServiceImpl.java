package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
import in.org.cris.cmm.sdms.dto.MaintenanceActionDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceActionMaster;
import in.org.cris.cmm.sdms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.sdms.repo.MaintenanceActionRepository;
import in.org.cris.cmm.sdms.repo.MasterRsTypeMaintenanceRepository;
import in.org.cris.cmm.sdms.service.MaintenanceActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceActionServiceImpl implements MaintenanceActionService {

        private final MaintenanceActionRepository maintenanceActionRepository;
        private final MasterRsTypeMaintenanceRepository masterRsTypeMaintenanceRepository;
        private final AuthenticationFacade authenticationFacade;

        @Override
        public MaintenanceActionMaster saveOrUpdate(MaintenanceActionDTO dto) {

                MaintenanceActionMaster entity;
                if (dto.getActionId() != null) {
                        entity = maintenanceActionRepository.findById(dto.getActionId())
                                .orElseThrow(() -> new RuntimeException("Action not found: " + dto.getActionId()));

                        entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setUpdatedAt(new Date());

                } else {
                        entity = new MaintenanceActionMaster();

                        entity.setValidFlag(true);
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setCreatedAt(new Date());
                }

                if (dto.getRsTypeMaintenanceId() != null) {
                        MasterRsTypeMaintenance ref = masterRsTypeMaintenanceRepository.findById(dto.getRsTypeMaintenanceId()).orElseThrow(() -> new RuntimeException("RS Type not found"));
                        entity.setRsTypeMaintenance(ref);
                }

                if (dto.getActionCode() != null) entity.setActionCode(dto.getActionCode());
                if (dto.getActionDescription() != null) entity.setActionDescription(dto.getActionDescription());

                return maintenanceActionRepository.save(entity);
        }

        @Override
        public String delete(Long id) {

                MaintenanceActionMaster entity = maintenanceActionRepository.findById(id).orElseThrow(() -> new RuntimeException("Action not found: " + id));
                entity.setValidFlag(false);
                entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                entity.setUpdatedAt(new Date());

                maintenanceActionRepository.save(entity);
                return "Record deleted successfully";
        }

        @Override
        public List<MaintenanceActionMaster> getAllValidActions() {
                return maintenanceActionRepository.findByValidFlagTrueOrderByActionIdAsc();
        }
}
