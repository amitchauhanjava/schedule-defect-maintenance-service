package in.org.cris.cmm.lsin.repo;

import in.org.cris.cmm.lsin.entity.MaintenanceTaskListMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceTaskListRepository extends JpaRepository<MaintenanceTaskListMaster, Long> {

    List<MaintenanceTaskListMaster> findByValidFlagTrueOrderByTaskIdAsc();
}

