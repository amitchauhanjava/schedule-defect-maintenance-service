package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceChecklistDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceChecklistMaster;

import java.util.List;

public interface MaintenanceChecklistService {

        MaintenanceChecklistMaster saveOrUpdate(MaintenanceChecklistDTO dto);

        String delete(Long id);

        List<MaintenanceChecklistMaster> getAllValidChecklistItems();
}
