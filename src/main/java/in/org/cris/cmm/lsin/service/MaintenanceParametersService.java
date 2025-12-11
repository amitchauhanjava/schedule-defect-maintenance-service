package in.org.cris.cmm.lsin.service;

import in.org.cris.cmm.lsin.dto.MaintenanceParametersDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceParametersMaster;

import java.util.List;

public interface MaintenanceParametersService {

    List<MaintenanceParametersMaster> getAllValidParameters();

    MaintenanceParametersMaster saveOrUpdate(MaintenanceParametersDTO dto);

    String softDelete(Long parameterId);
}
