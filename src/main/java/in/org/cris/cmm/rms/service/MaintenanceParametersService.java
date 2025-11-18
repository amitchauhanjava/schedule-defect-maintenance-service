package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.entity.MaintenanceParametersMaster;

import java.util.List;

public interface MaintenanceParametersService {
        List<MaintenanceParametersMaster> getAllValidParameters();
}
