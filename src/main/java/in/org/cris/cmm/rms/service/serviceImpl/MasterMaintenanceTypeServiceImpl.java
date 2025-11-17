package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MasterMaintenanceType;
import in.org.cris.cmm.rms.repo.MasterMaintenanceTypeRepository;
import in.org.cris.cmm.rms.service.MasterMaintenanceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterMaintenanceTypeServiceImpl implements MasterMaintenanceTypeService {

    @Autowired
    private MasterMaintenanceTypeRepository repository;

    @Override
    public ApiResponse<List<MasterMaintenanceType>> getAllMaintenanceTypes() {
        List<MasterMaintenanceType> list = repository.findByValidFlagTrueOrderByMaintenanceTypeIdAsc();

        long count = list!=null ? list.size() : 0;
        String message = count > 0 ? "Maintenance types fetched successfully" : "No records found";
        return new ApiResponse<>(count,list, HttpStatus.OK.value(), message);

    }
}
