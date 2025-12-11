package in.org.cris.cmm.lsin.service;

import in.org.cris.cmm.lsin.dto.MaintenanceActionDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceActionMaster;

import java.util.List;

public interface MaintenanceActionService {

        MaintenanceActionMaster saveOrUpdate(MaintenanceActionDTO dto);

        String delete(Long id);

        List<MaintenanceActionMaster> getAllValidActions();
}
