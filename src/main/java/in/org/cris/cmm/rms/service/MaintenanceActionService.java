package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.MaintenanceActionDTO;
import in.org.cris.cmm.rms.dto.MaintenanceActionResponseDTO;
import in.org.cris.cmm.rms.entity.MaintenanceActionMaster;

import java.util.List;

public interface MaintenanceActionService {

        MaintenanceActionMaster saveOrUpdate(MaintenanceActionDTO dto);

        String delete(Long id);

        List<MaintenanceActionMaster> getAllValidActions();

        List<MaintenanceActionResponseDTO> getAllValidActions(Long rsTypeMaintenanceId);
}
