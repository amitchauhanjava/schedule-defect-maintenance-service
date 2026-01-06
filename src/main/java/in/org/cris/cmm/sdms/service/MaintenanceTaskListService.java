package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceTaskListDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceTaskListMaster;

import java.util.List;

public interface MaintenanceTaskListService {

    List<MaintenanceTaskListMaster> getAllValidTasks();

    MaintenanceTaskListMaster saveOrUpdate(MaintenanceTaskListDTO dto);

    String softDelete(Long taskId);
}
