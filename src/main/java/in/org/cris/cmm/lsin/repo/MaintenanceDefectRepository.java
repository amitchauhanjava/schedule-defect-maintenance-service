package in.org.cris.cmm.lsin.repo;

import in.org.cris.cmm.lsin.entity.MaintenanceDefectMaster;
import in.org.cris.cmm.lsin.entity.MaintenanceDefectTxn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceDefectRepository extends JpaRepository<MaintenanceDefectMaster, Long> {

        List<MaintenanceDefectMaster> findByValidFlagTrueOrderByDefectIdAsc();

        @Query("""
        FROM MaintenanceDefectMaster d
        WHERE ?1 IS NULL OR d.rsTypeMaintenance.rsTypeMaintenanceId = ?1 AND d.validFlag = true order by d.defectId ASC
        """)
        List<MaintenanceDefectMaster> findDefects(Long rsTypeMaintenanceId);
}
