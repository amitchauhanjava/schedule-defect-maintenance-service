package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.JobCardActivityDTO;
import in.org.cris.cmm.sdms.entity.JobCardActivity;

import java.util.List;

public interface JobCardActivityService {

        JobCardActivity saveOrUpdate(JobCardActivityDTO dto);

        String delete(Long id);

        List<JobCardActivity> getAllActivities();
}
