package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.MaintenanceUnitTypeDTO;
import in.org.cris.cmm.rms.entity.MaintenanceUnitType;
import in.org.cris.cmm.rms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.rms.repo.MaintenanceUnitTypeRepository;
import in.org.cris.cmm.rms.repo.MasterRsTypeMaintenanceRepository;
import in.org.cris.cmm.rms.service.MaintenanceUnitTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MaintenanceUnitTypeServiceImpl implements MaintenanceUnitTypeService {

    @Autowired
    private MaintenanceUnitTypeRepository repository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private MasterRsTypeMaintenanceRepository masterRepo;

    @Override
    public List<MaintenanceUnitType> saveOrUpdateAll(List<MaintenanceUnitTypeDTO> dtoList) {

        String orgCode = authenticationFacade.getLoggedInUser().getDepot();
        String username = authenticationFacade.getLoggedInUser().getUser_name();

        List<MaintenanceUnitType> resultList = new ArrayList<>();

        for (MaintenanceUnitTypeDTO dto : dtoList) {
            MaintenanceUnitType entity;
            if (dto.getId() != null) {
                entity = repository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("MaintenanceUnitType not found: " + dto.getId()));

                if (dto.getMasterRsTypeMaintenanceId() != null) {
                    MasterRsTypeMaintenance master = masterRepo.findById(dto.getMasterRsTypeMaintenanceId())
                            .orElseThrow(() -> new RuntimeException("Invalid RS Type Maintenance ID: " + dto.getMasterRsTypeMaintenanceId()));
                    entity.setMasterRsTypeMaintenance(master);
                }

                if (dto.getDescription() != null) {
                    entity.setDescription(dto.getDescription());
                }

                entity.setOrgCode(orgCode);
                entity.setUpdatedBy(username);
                entity.setUpdatedAt(new Date());
            }
            // ====== CREATE ======
            else {
                entity = new MaintenanceUnitType();
                entity.setOrgCode(orgCode);

                if (dto.getMasterRsTypeMaintenanceId() != null) {
                    MasterRsTypeMaintenance master = masterRepo.findById(dto.getMasterRsTypeMaintenanceId())
                            .orElseThrow(() -> new RuntimeException("Invalid RS Type Maintenance ID: " + dto.getMasterRsTypeMaintenanceId()));
                    entity.setMasterRsTypeMaintenance(master);
                }

                entity.setDescription(dto.getDescription());
                entity.setValidFlag(true);
                entity.setCreatedBy(username);
                entity.setCreatedAt(new Date());
                entity.setUpdatedBy(username);
                entity.setUpdatedAt(new Date());
            }

            resultList.add(repository.save(entity));
        }

        return resultList;
    }



    @Override
    public ApiResponse<List<MaintenanceUnitType>> getById(Long id) {
        if (id == null) {
            return new ApiResponse<>(0, Collections.emptyList(), HttpStatus.BAD_REQUEST.value(), "ID cannot be null");
        }
        List<MaintenanceUnitType> list = repository.findByIdAndValidFlagTrue(id);
        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Maintenance Unit type fetched successfully" : "No records found";
        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    public ApiResponse<List<MaintenanceUnitType>> getByOrgCode() {
        String orgCode = authenticationFacade.getLoggedInUser().getDepot();

        List<MaintenanceUnitType> list = repository.findByOrgCodeAndValidFlagTrueOrderByIdAsc(orgCode);
        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Maintenance Unit type fetched successfully" : "No records found";
        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    @Transactional
    public ApiResponse<String> deleteById(Long id) {

        String username = authenticationFacade.getLoggedInUser().getUser_name();

        Optional<MaintenanceUnitType> optionalUnit = repository.findById(id);
        if (!optionalUnit.isPresent()) {
            return new ApiResponse<>(0, null, 404, "MaintenanceUnitType not found for id: " + id);
        }

        MaintenanceUnitType unit = optionalUnit.get();
        unit.setValidFlag(false);
        unit.setUpdatedAt(new Date());
        unit.setUpdatedBy(username); // optional
        repository.save(unit);

        return new ApiResponse<>(1, null, 200, "MaintenanceUnitType deleted successfully");
    }
}
