package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MaintenanceChecklistTxn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MaintenanceChecklistTxnRepository extends JpaRepository<MaintenanceChecklistTxn, Long> {

        List<MaintenanceChecklistTxn> findByValidFlagTrueOrderByChecklistTxnIdAsc();

        Optional<MaintenanceChecklistTxn> findByMaintenanceDetails_MaintenanceIdAndChecklistMaster_ChecklistId(Long maintenanceId, Long checklistId);

        @Query("""
            SELECT mct
            FROM MaintenanceChecklistTxn mct
            WHERE mct.validFlag = true
              AND (:checklistTxnId IS NULL OR mct.checklistTxnId = :checklistTxnId)
              AND (:maintenanceId IS NULL OR mct.maintenanceDetails.maintenanceId = :maintenanceId)
              AND (:checklistId IS NULL OR mct.checklistMaster.checklistId = :checklistId)
        """)
        List<MaintenanceChecklistTxn> findActiveChecklistTxn(
                @Param("checklistTxnId") Long checklistTxnId,
                @Param("maintenanceId") Long maintenanceId,
                @Param("checklistId") Long checklistId
        );

}
