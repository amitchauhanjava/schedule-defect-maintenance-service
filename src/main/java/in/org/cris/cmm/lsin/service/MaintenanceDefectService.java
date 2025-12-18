package in.org.cris.cmm.lsin.service;

import in.org.cris.cmm.lsin.dto.MaintenanceDefectDTO;
import in.org.cris.cmm.lsin.dto.MaintenanceDefectResponseDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceDefectMaster;

import java.util.List;

public interface MaintenanceDefectService {

        List<MaintenanceDefectMaster> getAllValidDefects();

        MaintenanceDefectMaster saveOrUpdate(MaintenanceDefectDTO dto);

        String delete(Long defectId);

        List<MaintenanceDefectResponseDTO> getDefects(Long rsTypeMaintenanceId);
}
