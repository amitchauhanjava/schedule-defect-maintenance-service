package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MaintenanceChecklistMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

        @Query("""
                SELECT DISTINCT mcm.assembly
                FROM MaintenanceChecklistMaster mcm
                WHERE mcm.orgCode IS NULL
                   OR mcm.orgCode = :orgCode
            """)
        List<String> findDistinctAssemblies(@Param("orgCode") String orgCode);

}
