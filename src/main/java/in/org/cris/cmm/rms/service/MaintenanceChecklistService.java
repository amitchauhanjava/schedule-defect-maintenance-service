package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.entity.MaintenanceChecklistMaster;

import java.util.List;

public interface MaintenanceChecklistService {
        List<MaintenanceChecklistMaster> getAllValidChecklistItems();
}
