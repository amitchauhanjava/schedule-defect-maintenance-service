package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.entity.MaintenanceActionMaster;

import java.util.List;

public interface MaintenanceActionService {
        List<MaintenanceActionMaster> getAllValidActions();
}
