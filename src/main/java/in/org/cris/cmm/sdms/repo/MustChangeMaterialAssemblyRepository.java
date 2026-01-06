package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MustChangeMaterialAssemblyMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MustChangeMaterialAssemblyRepository extends JpaRepository<MustChangeMaterialAssemblyMaster, Long> {

        List<MustChangeMaterialAssemblyMaster> findByValidFlagTrueOrderByMustChangeIdAsc();

        @Query("""
        SELECT m FROM MustChangeMaterialAssemblyMaster m
        WHERE m.validFlag = true
        AND (?1 IS NULL
             OR m.rsTypeMaintenance.rsTypeMaintenanceId = ?1)
        ORDER BY m.mustChangeId ASC
        """)
        List<MustChangeMaterialAssemblyMaster> findActiveByOptionalRsType(Long rsTypeMaintenanceId);
}
