package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceParametersDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceParametersMaster;

import java.util.List;

public interface MaintenanceParametersService {

    List<MaintenanceParametersMaster> getAllValidParameters();

    MaintenanceParametersMaster saveOrUpdate(MaintenanceParametersDTO dto);

    String softDelete(Long parameterId);
}
