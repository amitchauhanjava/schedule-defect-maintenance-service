package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MaintenanceManpowerTxn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaintenanceManpowerTxnRepository extends JpaRepository<in.org.cris.cmm.sdms.entity.MaintenanceManpowerTxn, Long> {

	@Query("""
    SELECT m FROM MaintenanceManpowerTxn m
    WHERE m.validFlag = true
      AND (:maintenanceId IS NULL OR m.maintenanceDetails.id = :maintenanceId)
      AND (:defectTxnId IS NULL OR m.defect.defectTxnId = :defectTxnId)
""")
	List<MaintenanceManpowerTxn> findActiveList(@Param("maintenanceId") Long maintenanceId,	@Param("defectTxnId") Long defectTxnId);

}

