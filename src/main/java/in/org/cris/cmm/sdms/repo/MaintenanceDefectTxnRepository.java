package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MaintenanceDefectTxn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MaintenanceDefectTxnRepository extends JpaRepository<MaintenanceDefectTxn, Long> {

        List<MaintenanceDefectTxn> findByValidFlagTrueOrderByDefectTxnIdAsc();

        @Query("""
            SELECT m FROM MaintenanceDefectTxn m
            WHERE (:defectTxnId IS NULL OR m.defectTxnId = :defectTxnId)
              AND (:maintenanceId IS NULL OR m.maintenanceDetails.maintenanceId = :maintenanceId)
              AND (:defectId IS NULL OR m.defect.defectId = :defectId)
              AND (:actionId IS NULL OR m.action.actionId = :actionId)
              AND (:checklistTxnId IS NULL OR m.checklistTxn.checklistTxnId = :checklistTxnId)
              AND m.validFlag = true
        """)
        List<MaintenanceDefectTxn> searchAll(
                @Param("defectTxnId") Long defectTxnId,
                @Param("maintenanceId") Long maintenanceId,
                @Param("defectId") Long defectId,
                @Param("actionId") Long actionId,
                @Param("checklistTxnId") Long checklistTxnId
        );

        Optional<MaintenanceDefectTxn> findByDefectTxnId(Long defectTxnId);

}
