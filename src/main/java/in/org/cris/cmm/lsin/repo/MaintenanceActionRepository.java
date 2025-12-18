package in.org.cris.cmm.lsin.repo;

import in.org.cris.cmm.lsin.entity.MaintenanceActionMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceActionRepository extends JpaRepository<MaintenanceActionMaster, Long> {

        List<MaintenanceActionMaster> findByValidFlagTrueOrderByActionIdAsc();

        @Query("""
        FROM MaintenanceActionMaster a
        WHERE (?1 IS NULL OR a.rsTypeMaintenance.rsTypeMaintenanceId = ?1)
          AND a.validFlag = true ORDER BY a.actionId ASC
        """)
        List<MaintenanceActionMaster> findActiveActions(Long rsTypeMaintenanceId);
}
