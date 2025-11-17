package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.entity.MaintenanceUnit;
import in.org.cris.cmm.rms.repo.MaintenanceUnitRepository;
import in.org.cris.cmm.rms.service.MaintenanceUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceUnitServiceImpl implements MaintenanceUnitService {

    @Autowired
    private MaintenanceUnitRepository repository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public MaintenanceUnit saveOrUpdate(MaintenanceUnit unit) {

        String username = authenticationFacade.getLoggedInUser().getUser_name();

        if (unit.getMuId() == null) {
            // New record
            unit.setCreatedBy(username);
            unit.setCreatedAt(new Date());
            return repository.save(unit);
        } else {
            // Update only non-null values
            return repository.findById(unit.getMuId()).map(existing -> {
                if (unit.getMuCode() != null) existing.setMuCode(unit.getMuCode());
                if (unit.getMuName() != null) existing.setMuName(unit.getMuName());
                if (unit.getDivisionId() != null) existing.setDivisionId(unit.getDivisionId());
                if (unit.getParentMuId() != null) existing.setParentMuId(unit.getParentMuId());
                if (unit.getLocationCity() != null) existing.setLocationCity(unit.getLocationCity());
                if (unit.getLocationState() != null) existing.setLocationState(unit.getLocationState());
                if (unit.getLatitude() != null) existing.setLatitude(unit.getLatitude());
                if (unit.getLongitude() != null) existing.setLongitude(unit.getLongitude());
                if (unit.getCommissioningDate() != null) existing.setCommissioningDate(unit.getCommissioningDate());
                if (unit.getDesignedCapacity() != null) existing.setDesignedCapacity(unit.getDesignedCapacity());
                if (unit.getCurrentUtilization() != null) existing.setCurrentUtilization(unit.getCurrentUtilization());
                if (unit.getLastMaintenanceDate() != null) existing.setLastMaintenanceDate(unit.getLastMaintenanceDate());
                if (unit.getAreaSqMeters() != null) existing.setAreaSqMeters(unit.getAreaSqMeters());
                if (unit.getNearestStationCode() != null) existing.setNearestStationCode(unit.getNearestStationCode());
                if (unit.getRemarks() != null) existing.setRemarks(unit.getRemarks());
                if (unit.getStatus() != null) existing.setStatus(unit.getStatus());

                existing.setUpdatedBy(username);
                existing.setUpdatedAt(new Date());
                return repository.save(existing);
            }).orElseThrow(() -> new RuntimeException("Maintenance Unit not found with id: " + unit.getMuId()));
        }
    }

    @Override
    public Optional<MaintenanceUnit> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<MaintenanceUnit> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
