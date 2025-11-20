package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceActionDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceActionMaster;

import java.util.List;

public interface MaintenanceActionService {

        MaintenanceActionMaster saveOrUpdate(MaintenanceActionDTO dto);

        String delete(Long id);

        List<MaintenanceActionMaster> getAllValidActions();
}
