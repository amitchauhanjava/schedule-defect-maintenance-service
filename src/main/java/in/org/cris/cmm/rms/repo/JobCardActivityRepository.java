package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.JobCardActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobCardActivityRepository extends JpaRepository<JobCardActivity, Long> {

        List<JobCardActivity> findAllByOrderByActivityIdAsc();
}
