package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MaintenanceChecklistMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceChecklistRepository extends JpaRepository<MaintenanceChecklistMaster, Long> {

        List<MaintenanceChecklistMaster> findByValidFlagTrueOrderByChecklistIdAsc();
}
