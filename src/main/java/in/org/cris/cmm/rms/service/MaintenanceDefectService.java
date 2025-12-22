package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.MaintenanceDefectDTO;
import in.org.cris.cmm.rms.dto.MaintenanceDefectResponseDTO;
import in.org.cris.cmm.rms.entity.MaintenanceDefectMaster;

import java.util.List;

public interface MaintenanceDefectService {

        List<MaintenanceDefectMaster> getAllValidDefects();

        MaintenanceDefectMaster saveOrUpdate(MaintenanceDefectDTO dto);

        String delete(Long defectId);

        List<MaintenanceDefectResponseDTO> getDefects(Long rsTypeMaintenanceId);
}
