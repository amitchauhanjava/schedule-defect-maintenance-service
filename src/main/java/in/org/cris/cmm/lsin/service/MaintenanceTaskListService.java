package in.org.cris.cmm.lsin.service;

import in.org.cris.cmm.lsin.dto.MaintenanceTaskListDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceTaskListMaster;

import java.util.List;

public interface MaintenanceTaskListService {

    List<MaintenanceTaskListMaster> getAllValidTasks();

    MaintenanceTaskListMaster saveOrUpdate(MaintenanceTaskListDTO dto);

    String softDelete(Long taskId);
}
