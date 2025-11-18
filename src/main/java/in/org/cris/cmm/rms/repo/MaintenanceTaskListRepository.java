package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MaintenanceTaskListMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceTaskListRepository extends JpaRepository<MaintenanceTaskListMaster, Long> {

    // Custom finder / Derived Query method
    List<MaintenanceTaskListMaster> findByValidFlagTrueOrderByTaskIdAsc();
}

