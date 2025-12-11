package in.org.cris.cmm.lsin.repo;

import in.org.cris.cmm.lsin.entity.MaintenanceActionMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceActionRepository extends JpaRepository<MaintenanceActionMaster, Long> {

        List<MaintenanceActionMaster> findByValidFlagTrueOrderByActionIdAsc();
}
