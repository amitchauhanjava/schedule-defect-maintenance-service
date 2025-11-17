package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.MasterRsTypeMaintenanceDTO;
import in.org.cris.cmm.rms.entity.MasterRsTypeMaintenance;

import java.util.List;

public interface MasterRsTypeMaintenanceService {

    List<MasterRsTypeMaintenance> saveOrUpdate(List<MasterRsTypeMaintenanceDTO> list);

    ApiResponse<List<MasterRsTypeMaintenance>> getByMaintenanceTypeId(Long maintenanceTypeId);

    ApiResponse<List<MasterRsTypeMaintenance>> getByRsTypeId(Long rsTypeId);

    String deleteById(Long id);

    ApiResponse<List<MasterRsTypeMaintenance>> getList();
}
