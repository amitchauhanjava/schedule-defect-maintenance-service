package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.dto.MaintenanceManpowerTxnDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceManpowerTxn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaintenanceManpowerTxnRepository extends JpaRepository<MaintenanceManpowerTxn, Long> {

	/*@Query("""
    SELECT m FROM MaintenanceManpowerTxn m
    WHERE m.validFlag = true
      AND (:maintenanceId IS NULL OR m.maintenanceDetails.id = :maintenanceId)
      AND (:defectTxnId IS NULL OR m.defect.defectTxnId = :defectTxnId)
""")
	List<MaintenanceManpowerTxn> findActiveList(@Param("maintenanceId") Long maintenanceId,	@Param("defectTxnId") Long defectTxnId);*/

	@Query("""
			SELECT new in.org.cris.cmm.sdms.dto.MaintenanceManpowerTxnDTO(
				m.manpowerTxnId,
				m.maintenanceDetails.id,
				m.defect.defectTxnId,
				m.category,
				m.manpowerName,
				m.designation,
				m.hoursSpent,
				m.manpowerCost
			)
			FROM MaintenanceManpowerTxn m
			WHERE m.validFlag = true
			  AND (:maintenanceId IS NULL OR m.maintenanceDetails.id = :maintenanceId)
			  AND (:defectTxnId IS NULL OR m.defect.defectTxnId = :defectTxnId)
		""")
	List<MaintenanceManpowerTxnDTO> findActiveList(@Param("maintenanceId") Long maintenanceId, @Param("defectTxnId") Long defectTxnId);


}

