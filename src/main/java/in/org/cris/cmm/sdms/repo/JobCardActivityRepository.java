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

}
