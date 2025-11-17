package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MasterMaintenanceType;

import java.util.List;

public interface MasterMaintenanceTypeService {
    ApiResponse<List<MasterMaintenanceType>> getAllMaintenanceTypes();
}