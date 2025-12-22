package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.MaintenanceTaskListDTO;
import in.org.cris.cmm.rms.entity.MaintenanceTaskListMaster;

import java.util.List;

public interface MaintenanceTaskListService {

    List<MaintenanceTaskListMaster> getAllValidTasks();

    MaintenanceTaskListMaster saveOrUpdate(MaintenanceTaskListDTO dto);

    String softDelete(Long taskId);
}
