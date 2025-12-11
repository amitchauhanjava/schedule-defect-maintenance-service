package in.org.cris.cmm.lsin.service;

import in.org.cris.cmm.lsin.dto.JobCardActivityDTO;
import in.org.cris.cmm.lsin.entity.JobCardActivity;

import java.util.List;

public interface JobCardActivityService {

        JobCardActivity saveOrUpdate(JobCardActivityDTO dto);

        String delete(Long id);

        List<JobCardActivity> getAllActivities();
}
