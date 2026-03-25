package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.dto.MaintenanceProjection;
import in.org.cris.cmm.sdms.entity.MaintenanceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaintenanceDetailsRepository extends JpaRepository<MaintenanceDetails, Long> {

        List<MaintenanceDetails> findByValidFlagTrueOrderByMaintenanceIdAsc();

        @Query(value = """
        SELECT
            md.maintenance_id AS maintenanceId,
            am.asset_no AS assetNo,
            md.maintenance_type AS maintenanceType,
            CAST(md.start_date AS DATE) AS startDate
        FROM sdms.maintenance_details md
        JOIN rolling_stock.asset_master am
            ON am.dk = md.asset_id
        WHERE
            md.valid_flag = true
            AND md.start_date >= CURRENT_DATE - INTERVAL '30 days'
            AND am.depot = :depot
        """, nativeQuery = true)
        List<MaintenanceProjection> getMaintenanceData(@Param("depot") String depot);
}
