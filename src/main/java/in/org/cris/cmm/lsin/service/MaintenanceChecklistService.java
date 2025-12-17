package in.org.cris.cmm.lsin.service;

import in.org.cris.cmm.lsin.dto.CopyChecklistRequestDTO;
import in.org.cris.cmm.lsin.dto.MaintenanceChecklistDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceChecklistMaster;

import java.util.List;

public interface MaintenanceChecklistService {

        MaintenanceChecklistMaster saveOrUpdate(MaintenanceChecklistDTO dto);

        String delete(Long id);

        List<MaintenanceChecklistMaster> getAllValidChecklistItems();

        String copyChecklistItems(CopyChecklistRequestDTO requestDTO);

        List<MaintenanceChecklistMaster> getChecklist(Long rsTypeMaintenanceId,String coachKind,String utilityType);
}
