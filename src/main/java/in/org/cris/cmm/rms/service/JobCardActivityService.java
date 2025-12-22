package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.JobCardActivityDTO;
import in.org.cris.cmm.rms.entity.JobCardActivity;

import java.util.List;

public interface JobCardActivityService {

        JobCardActivity saveOrUpdate(JobCardActivityDTO dto);

        String delete(Long id);

        List<JobCardActivity> getAllActivities();
}
