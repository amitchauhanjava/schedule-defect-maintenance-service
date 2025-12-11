package in.org.cris.cmm.lsin.repo;

import in.org.cris.cmm.lsin.entity.JobCardActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobCardActivityRepository extends JpaRepository<JobCardActivity, Long> {

        List<JobCardActivity> findAllByOrderByActivityIdAsc();
}
