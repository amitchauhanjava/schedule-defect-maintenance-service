package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MaintenanceChecklistMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceChecklistRepository extends JpaRepository<MaintenanceChecklistMaster, Long> {
        List<MaintenanceChecklistMaster> findByValidFlagTrueOrderByChecklistIdAsc();
}
