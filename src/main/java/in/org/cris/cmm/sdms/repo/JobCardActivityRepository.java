package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.JobCardActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobCardActivityRepository extends JpaRepository<JobCardActivity, Long> {

        List<JobCardActivity> findByJobCard_JobCardIdOrderByActivityIdAsc(Long jobCardId);

        Long countByJobCard_JobCardId(Long jobCardId);

        Long countByJobCard_JobCardIdAndStatus(Long jobCardId, String status);

        @Query("SELECT DISTINCT j.sectionId FROM JobCardActivity j WHERE j.jobCard.jobCardId = :jobCardId")
        List<Long> findDistinctSectionIdsByJobCardId(@Param("jobCardId") Long jobCardId);

        @Query(value = """
            SELECT DISTINCT sec.section_name
            FROM sdms.job_card_activity j
            JOIN lsin.sections sec ON sec.section_id = j.section_id
            WHERE j.job_card_id = :jobCardId
        """, nativeQuery = true)
        List<String> findDistinctSectionNamesByJobCardId(@Param("jobCardId") Long jobCardId);
}
