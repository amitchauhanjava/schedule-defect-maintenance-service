package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
import in.org.cris.cmm.sdms.dto.MaintenanceTaskListDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceTaskListMaster;
import in.org.cris.cmm.sdms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.sdms.repo.MaintenanceTaskListRepository;
import in.org.cris.cmm.sdms.repo.MasterRsTypeMaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceTaskListServiceImpl implements MaintenanceTaskListService {

    private final MaintenanceTaskListRepository maintenanceTaskListRepository;
    private final MasterRsTypeMaintenanceRepository masterRsTypeMaintenanceRepository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public List<MaintenanceTaskListMaster> getAllValidTasks() {
        return maintenanceTaskListRepository.findByValidFlagTrueOrderByTaskIdAsc();
    }

    @Override
    @Transactional
    public MaintenanceTaskListMaster saveOrUpdate(MaintenanceTaskListDTO dto) {



        MaintenanceTaskListMaster entity;

        if (dto.getTaskId() != null) {
            entity = maintenanceTaskListRepository.findById(dto.getTaskId()).orElseThrow(() -> new RuntimeException("Task not found: " + dto.getTaskId()));

            entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
            entity.setUpdatedAt(new Date());
        } else {
            entity = new MaintenanceTaskListMaster();

            entity.setValidFlag(true);
            entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
            entity.setCreatedAt(new Date());
        }

        if (dto.getRsTypeMaintenanceId() != null) {
            MasterRsTypeMaintenance ref = masterRsTypeMaintenanceRepository.findById(dto.getRsTypeMaintenanceId())
                    .orElseThrow(() -> new RuntimeException("RS Type not found"));
            entity.setRsTypeMaintenance(ref);
        }

        if (dto.getTaskCode() != null) entity.setTaskCode(dto.getTaskCode());
        if (dto.getTaskDescription() != null) entity.setTaskDescription(dto.getTaskDescription());
        if (dto.getSkillRequired() != null) entity.setSkillRequired(dto.getSkillRequired());
        if (dto.getEstimatedTimeMinutes() != null) entity.setEstimatedTimeMinutes(dto.getEstimatedTimeMinutes());

        return maintenanceTaskListRepository.save(entity);
    }

    @Override
    @Transactional
    public String softDelete(Long taskId) {

        MaintenanceTaskListMaster entity = maintenanceTaskListRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));

        entity.setValidFlag(false);
        entity.setUpdatedAt(new Date());
        maintenanceTaskListRepository.save(entity);

        return "Task deleted successfully";
    }
}
