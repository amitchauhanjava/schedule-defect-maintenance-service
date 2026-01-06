package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MaintenanceMaterialsTxn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaintenanceMaterialsTxnRepository extends JpaRepository<MaintenanceMaterialsTxn, Long> {

	@Query("""
        SELECT m FROM MaintenanceMaterialsTxn m
        WHERE m.validFlag = true
          AND (:maintenanceId IS NULL OR m.maintenanceDetails.id = :maintenanceId)
          AND (:defectTxnId IS NULL OR m.defect.defectTxnId = :defectTxnId)
    """)
	List<MaintenanceMaterialsTxn> findActiveList(@Param("maintenanceId") Long maintenanceId, @Param("defectTxnId") Long defectTxnId);
}
