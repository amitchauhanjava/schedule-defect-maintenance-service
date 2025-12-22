package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.MaintenanceParametersDTO;
import in.org.cris.cmm.rms.entity.MaintenanceParametersMaster;

import java.util.List;

public interface MaintenanceParametersService {

    List<MaintenanceParametersMaster> getAllValidParameters();

    MaintenanceParametersMaster saveOrUpdate(MaintenanceParametersDTO dto);

    String softDelete(Long parameterId);
}
