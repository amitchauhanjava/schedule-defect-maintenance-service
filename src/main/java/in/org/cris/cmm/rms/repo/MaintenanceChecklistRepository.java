package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MaintenanceChecklistMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceChecklistRepository extends JpaRepository<MaintenanceChecklistMaster, Long> {

        List<MaintenanceChecklistMaster> findByValidFlagTrueOrderByChecklistIdAsc();

        List<MaintenanceChecklistMaster> findByChecklistId(Long id);

        @Query("""
            SELECT DISTINCT m FROM MaintenanceChecklistMaster m
            WHERE m.validFlag = true
            AND (?1 IS NULL OR m.rsTypeMaintenance.rsTypeMaintenanceId = ?1)
            AND (?2 IS NULL OR m.coachKind = ?2)
            AND (?3 IS NULL OR m.utilityType = ?3)
        """)
        List<MaintenanceChecklistMaster> findByFilters(Long rsTypeMaintenanceId, String coachKind, String utilityType);

}
