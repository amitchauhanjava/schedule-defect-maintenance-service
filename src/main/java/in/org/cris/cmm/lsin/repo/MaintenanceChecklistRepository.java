package in.org.cris.cmm.lsin.repo;

import in.org.cris.cmm.lsin.entity.MaintenanceChecklistMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceChecklistRepository extends JpaRepository<MaintenanceChecklistMaster, Long> {

        List<MaintenanceChecklistMaster> findByValidFlagTrueOrderByChecklistIdAsc();

        List<MaintenanceChecklistMaster> findByChecklistId(Long id);
}
