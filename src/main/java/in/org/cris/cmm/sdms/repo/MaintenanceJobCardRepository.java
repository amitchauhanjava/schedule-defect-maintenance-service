package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.dto.MaintenanceJobCardDTO;
import in.org.cris.cmm.sdms.dto.MaintenanceJobCardProjection;
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


        /*@Query(
                value = """
        WITH filter_depots AS (
            SELECT ml.depot_code
            FROM cmm_dev.mt_location ml
            WHERE
                  :loginLevel = 'BOARD'
               OR (:loginLevel = 'DEPOT'    AND ml.depot_code = :loginCode)
               OR (:loginLevel = 'DIVISION' AND ml.div_code   = :loginCode)
               OR (:loginLevel = 'ZONE'     AND ml.zone_code  = :loginCode)
        )
        SELECT
            mjc.job_card_id,
            mjc.org_code,
            mjc.job_no,
            mjc.rake_id,
            mjc.start_time,
            mjc.end_time,
            mjc.status,
            mjc.valid_flag,
            mjc.created_by,
            mjc.updated_by,
            mjc.created_at,
            mjc.updated_at,
            COUNT(jca.activity_id) AS totalActivity,
            COUNT(jca.activity_id) FILTER (WHERE jca.status = 'PENDING') AS pendingActivity
        FROM sdms.maintenance_job_card mjc
        LEFT JOIN sdms.job_card_activity jca
               ON jca.job_card_id = mjc.job_card_id
        WHERE mjc.valid_flag = TRUE
          AND (
                :loginLevel = 'BOARD'
                OR mjc.org_code IN (SELECT fd.depot_code FROM filter_depots fd)
              )
          AND (
                :search IS NULL
             OR :search = ''
             OR CAST(mjc.job_card_id AS TEXT) ILIKE CONCAT('%', :search, '%')
             OR mjc.job_no ILIKE CONCAT('%', :search, '%')
              )
        GROUP BY
            mjc.job_card_id,
            mjc.org_code,
            mjc.job_no,
            mjc.rake_id,
            mjc.start_time,
            mjc.end_time,
            mjc.status,
            mjc.valid_flag,
            mjc.created_by,
            mjc.updated_by,
            mjc.created_at,
            mjc.updated_at
        """,
                countQuery = """
        SELECT COUNT(DISTINCT mjc.job_card_id)
        FROM sdms.maintenance_job_card mjc
        WHERE mjc.valid_flag = TRUE
        """,
                nativeQuery = true
        )
        Page<MaintenanceJobCard> findJobCardWithFilters(
                @Param("loginLevel") String loginLevel,
                @Param("loginCode") String loginCode,
                @Param("search") String search,
                Pageable pageable
        );*/


        @Query(
                value = """
        WITH filter_depots AS (
            SELECT ml.depot_code
            FROM cmm_dev.mt_location ml
            WHERE
                  :loginLevel = 'BOARD'
               OR (:loginLevel = 'DEPOT'    AND ml.depot_code = :loginCode)
               OR (:loginLevel = 'DIVISION' AND ml.div_code   = :loginCode)
               OR (:loginLevel = 'ZONE'     AND ml.zone_code  = :loginCode)
        )
        SELECT
            mjc.job_card_id AS jobCardId,
            mjc.org_code AS orgCode,
            mjc.job_no AS jobNo,
            mjc.rake_id AS rakeId,  -- <-- Add this
            mjc.start_time AS startTime,
            mjc.end_time AS endTime,
            mjc.status AS status,
            mjc.valid_flag AS validFlag,
            mjc.created_by AS createdBy,
            mjc.updated_by AS updatedBy,
            mjc.created_at AS createdAt,
            mjc.updated_at AS updatedAt,
            COUNT(jca.activity_id) AS totalActivity,
            COUNT(jca.activity_id) FILTER (WHERE jca.status = 'PENDING') AS pendingActivity
        FROM sdms.maintenance_job_card mjc
        LEFT JOIN sdms.job_card_activity jca
               ON jca.job_card_id = mjc.job_card_id
        WHERE mjc.valid_flag = TRUE AND mjc.status IN ('PENDING','IN_PROGRESS')
          AND (
                :loginLevel = 'BOARD'
                OR mjc.org_code IN (SELECT fd.depot_code FROM filter_depots fd)
              )
          AND (
                :search IS NULL
             OR :search = ''
             OR CAST(mjc.job_card_id AS TEXT) ILIKE CONCAT('%', :search, '%')
             OR mjc.job_no ILIKE CONCAT('%', :search, '%')
              )
        GROUP BY
            mjc.job_card_id,
            mjc.rake_id, 
            mjc.org_code,
            mjc.job_no,
            mjc.start_time,
            mjc.end_time,
            mjc.status,
            mjc.valid_flag,
            mjc.created_by,
            mjc.updated_by,
            mjc.created_at,
            mjc.updated_at
    """,
                countQuery = """
        SELECT COUNT(DISTINCT mjc.job_card_id)
        FROM sdms.maintenance_job_card mjc
        WHERE mjc.valid_flag = TRUE
    """,
                nativeQuery = true
        )
        Page<MaintenanceJobCardProjection> findJobCardWithFilters(
                @Param("loginLevel") String loginLevel,
                @Param("loginCode") String loginCode,
                @Param("search") String search,
                Pageable pageable
        );





}