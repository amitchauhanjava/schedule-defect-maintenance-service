package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MaintenanceJobCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MaintenanceJobCardRepository extends JpaRepository<MaintenanceJobCard, Long> {

//        List<MaintenanceJobCard> findByOrgCodeAndValidFlagTrueOrderByJobCardIdAsc(String orgCode);

        @Query("""
            SELECT m
            FROM MaintenanceJobCard m
            WHERE m.orgCode = ?1
              AND m.validFlag = true
              AND m.startTime >= ?2
              AND m.startTime <= ?3
            ORDER BY m.jobCardId ASC
        """)
        List<MaintenanceJobCard> findByStartDateRange(String orgCode, Date fromDate, Date toDate);


        @Query(value = """
            WITH filter_depots AS (
               SELECT ml."depot_code"
               FROM "cmm_dev"."mt_location" ml
               WHERE
                   :loginLevel = 'BOARD'
                   OR (:loginLevel = 'DEPOT' AND ml."depot_code" = :loginCode)
                   OR (:loginLevel = 'DIVISION' AND ml."div_code" = :loginCode)
                   OR (:loginLevel = 'ZONE' AND ml."zone_code" = :loginCode)
           )
            SELECT *
                  FROM "sdms"."maintenance_job_card" mjc
                  WHERE valid_flag = true
                      (:loginLevel = 'BOARD'
                       OR mjc.org_code IN (SELECT "depot_code" FROM filter_depots))
                   AND (CAST(mjc.job_card_id AS TEXT) ILIKE %:search%
                     OR CAST(mjc.job_no AS TEXT) ILIKE %:search%)
            """,
                nativeQuery = true)
        Page<MaintenanceJobCard> findJobCardWithFilters(@Param("loginLevel") String loginLevel, @Param("loginCode") String loginCode, @Param("search") String search, Pageable pageable);
}