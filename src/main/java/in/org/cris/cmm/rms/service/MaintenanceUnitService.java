package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.entity.MaintenanceUnit;

import java.util.List;
import java.util.Optional;

public interface MaintenanceUnitService {

    MaintenanceUnit saveOrUpdate(MaintenanceUnit unit);

    Optional<MaintenanceUnit> getById(Long id);

    List<MaintenanceUnit> getAll();

    void deleteById(Long id);
}
