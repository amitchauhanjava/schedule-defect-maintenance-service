package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.entity.MaintenanceDefectMaster;

import java.util.List;

public interface MaintenanceDefectService {
        List<MaintenanceDefectMaster> getAllValidDefects();

}
