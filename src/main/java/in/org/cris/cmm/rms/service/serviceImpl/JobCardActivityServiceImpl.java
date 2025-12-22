package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.JobCardActivityDTO;
import in.org.cris.cmm.rms.entity.*;
import in.org.cris.cmm.rms.repo.*;
import in.org.cris.cmm.rms.service.JobCardActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobCardActivityServiceImpl implements JobCardActivityService {

        private final JobCardActivityRepository repository;
        private final MaintenanceJobCardRepository jobCardRepo;
        private final MaintenanceDetailsRepository detailsRepo;
        private final MaintenanceTaskListRepository taskRepo;
        private final AuthenticationFacade authenticationFacade;

        @Override
        public JobCardActivity saveOrUpdate(JobCardActivityDTO dto) {

                JobCardActivity entity;

                if (dto.getActivityId() != null) {
                        entity = repository.findById(dto.getActivityId()).orElseThrow(() -> new RuntimeException("Activity not found: " + dto.getActivityId()));
                        entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setUpdatedAt(new Date());
                } else {
                        entity = new JobCardActivity();
                        entity.setCreatedAt(new Date());
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                }
                MaintenanceJobCard jobCard = jobCardRepo.findById(dto.getJobCardId()).orElseThrow(() -> new RuntimeException("Job card not found"));
                entity.setJobCard(jobCard);
                entity.setActivityType(dto.getActivityType());

                if (dto.getMaintenanceId() != null) {
                        MaintenanceDetails md = detailsRepo.findById(dto.getMaintenanceId()).orElseThrow(() -> new RuntimeException("Maintenance not found"));
                        entity.setMaintenanceDetails(md);
                }

                if (dto.getTaskMId() != null) {
                        MaintenanceTaskListMaster task = taskRepo.findById(dto.getTaskMId()).orElseThrow(() -> new RuntimeException("Task not found"));
                        entity.setTaskMaster(task);
                }

                entity.setSectionId(dto.getSectionId());
                entity.setAssetDk(dto.getAssetDk());
                entity.setAssemblyNumber(dto.getAssemblyNumber());
                entity.setActivityDesc(dto.getActivityDesc());
                entity.setAssignedTo(dto.getAssignedTo());
                entity.setStatus(dto.getStatus());
                entity.setCompletionRemarks(dto.getCompletionRemarks());

                try {
                        if (dto.getStartTime() != null) entity.setStartTime(dto.getStartTime());
                        if (dto.getEndTime() != null) entity.setEndTime(dto.getEndTime());
                } catch (Exception ex) {
                        throw new RuntimeException("Invalid date format.");
                }

                return repository.save(entity);
        }

        @Override
        public String delete(Long id) {
                repository.deleteById(id);
                return "Record deleted successfully";
        }

        @Override
        public List<JobCardActivity> getAllActivities() {
                return repository.findAllByOrderByActivityIdAsc();
        }
}
