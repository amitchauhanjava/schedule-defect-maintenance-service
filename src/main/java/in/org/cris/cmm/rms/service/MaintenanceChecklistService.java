package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.CopyChecklistRequestDTO;
import in.org.cris.cmm.rms.dto.MaintenanceChecklistDTO;
import in.org.cris.cmm.rms.entity.MaintenanceChecklistMaster;

import java.util.List;

public interface MaintenanceChecklistService {

        MaintenanceChecklistMaster saveOrUpdate(MaintenanceChecklistDTO dto);

        String delete(Long id);

        List<MaintenanceChecklistMaster> getAllValidChecklistItems();

        String copyChecklistItems(CopyChecklistRequestDTO requestDTO);

        List<MaintenanceChecklistMaster> getChecklist(Long rsTypeMaintenanceId,String coachKind,String utilityType);
}
