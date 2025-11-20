package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceDefectDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceDefectMaster;

import java.util.List;

public interface MaintenanceDefectService {

        List<MaintenanceDefectMaster> getAllValidDefects();

        MaintenanceDefectMaster saveOrUpdate(MaintenanceDefectDTO dto);

        String delete(Long defectId);

}
