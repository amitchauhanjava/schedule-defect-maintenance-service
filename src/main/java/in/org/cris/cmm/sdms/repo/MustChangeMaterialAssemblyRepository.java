package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MustChangeMaterialAssemblyMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MustChangeMaterialAssemblyRepository extends JpaRepository<MustChangeMaterialAssemblyMaster, Long> {

        List<MustChangeMaterialAssemblyMaster> findByValidFlagTrueOrderByMustChangeIdAsc();

        @Query("""
            SELECT m FROM MustChangeMaterialAssemblyMaster m
            WHERE m.validFlag = true
              AND (:rsTypeMaintenanceId IS NULL OR m.rsTypeMaintenance.rsTypeMaintenanceId = :rsTypeMaintenanceId)
              AND (:coachKind IS NULL OR m.coachKind = :coachKind)
              AND (:utilityType IS NULL OR m.utilityType = :utilityType)
            ORDER BY m.mustChangeId ASC
        """)
        List<MustChangeMaterialAssemblyMaster> findActiveByOptionalFilters(@Param("rsTypeMaintenanceId") Long rsTypeMaintenanceId, @Param("coachKind") String coachKind, @Param("utilityType") String utilityType);

}
