package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.MaintenanceUnitTypeDTO;
import in.org.cris.cmm.rms.entity.MaintenanceUnitType;

import java.util.List;

public interface MaintenanceUnitTypeService {

    List<MaintenanceUnitType> saveOrUpdateAll(List<MaintenanceUnitTypeDTO> dtoList);
    ApiResponse<List<MaintenanceUnitType>> getById(Long id);
    ApiResponse<List<MaintenanceUnitType>> getByOrgCode();
    ApiResponse<String> deleteById(Long id);
}
