package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MaintenanceUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceUnitRepository extends JpaRepository<MaintenanceUnit, Long> {
}
