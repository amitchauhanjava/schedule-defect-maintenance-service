package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MaintenanceActionMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceActionRepository extends JpaRepository<MaintenanceActionMaster, Long> {

        List<MaintenanceActionMaster> findByValidFlagTrueOrderByActionIdAsc();
}
