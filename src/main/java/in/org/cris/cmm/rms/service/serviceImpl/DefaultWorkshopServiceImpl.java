package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.DefaultWorkshopDTO;
import in.org.cris.cmm.rms.entity.DefaultWorkshop;
import in.org.cris.cmm.rms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.rms.repo.DefaultWorkshopRepository;
import in.org.cris.cmm.rms.repo.MasterRsTypeMaintenanceRepository;
import in.org.cris.cmm.rms.service.DefaultWorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultWorkshopServiceImpl implements DefaultWorkshopService {

    @Autowired
    private DefaultWorkshopRepository repository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private MasterRsTypeMaintenanceRepository masterRepo;

    @Override
    public DefaultWorkshop saveOrUpdate(DefaultWorkshopDTO dto) {

        String username = authenticationFacade.getLoggedInUser().getUser_name();
        String orgCode = authenticationFacade.getLoggedInUser().getDepot();

        DefaultWorkshop entity;

        if (dto.getDefaultWorkshopId() != null) {
            entity = repository.findById(dto.getDefaultWorkshopId()).orElseThrow(() -> new RuntimeException("Default Workshop not found"));

            if (dto.getMasterRsTypeMaintenanceId() != null) {
                MasterRsTypeMaintenance master = masterRepo.findById(dto.getMasterRsTypeMaintenanceId()).orElseThrow(() -> new RuntimeException("RS Type Maintenance not found"));
                entity.setMasterRsTypeMaintenance(master);
            }

            if (dto.getMasterRsTypeMaintenanceId() != null) entity.getMasterRsTypeMaintenance().setRsTypeMaintenanceId(dto.getMasterRsTypeMaintenanceId());
            if (dto.getWorkshopOrgCode() != null) entity.setWorkshopOrgCode(dto.getWorkshopOrgCode());
            if (dto.getDeviceType() != null) entity.setDeviceType(dto.getDeviceType());

            entity.setOrgCode(orgCode);
            entity.setUpdatedBy(username);
            entity.setUpdatedAt(new Date());

                return repository.save(entity);

        }

        // New record
        entity = new DefaultWorkshop();

        entity.setWorkshopOrgCode(dto.getWorkshopOrgCode());
        entity.setDeviceType(dto.getDeviceType());

        if (dto.getMasterRsTypeMaintenanceId() != null) {
            MasterRsTypeMaintenance master = masterRepo.findById(dto.getMasterRsTypeMaintenanceId()).orElseThrow(() -> new RuntimeException("RS Type Maintenance not found"));
            entity.setMasterRsTypeMaintenance(master);
        }

        entity.setOrgCode(orgCode);
        entity.setCreatedBy(username);
        entity.setCreatedAt(new Date());
        return repository.save(entity);
    }

    @Override
    public boolean deleteById(Long id) {

        String username = authenticationFacade.getLoggedInUser().getUser_name();

        Optional<DefaultWorkshop> existingOpt = repository.findById(id);
        if (existingOpt.isPresent()) {
            DefaultWorkshop existing = existingOpt.get();
            existing.setValidFlag(false);
            existing.setUpdatedBy(username);
            existing.setUpdatedAt(new Date());
            repository.save(existing);
            return true;
        }
        return false;
    }

    @Override
    public ApiResponse<List<DefaultWorkshop>> getByOrgCode() {

        String orgCode = authenticationFacade.getLoggedInUser().getDepot();
        List<DefaultWorkshop> list = repository.findByOrgCodeAndValidFlagTrue(orgCode);

        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Record Fetched Successfully." : "No Record Found";

        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    public DefaultWorkshop getById(Long id) {
        return repository.findByDefaultWorkshopIdAndValidFlagTrue(id);
    }
}
