package in.org.cris.cmm.sdms.controller;

import in.org.cris.cmm.sdms.dto.APIsResponse;
import in.org.cris.cmm.sdms.dto.ApiResponse;
import in.org.cris.cmm.sdms.dto.JobCardActivityDTO;
import in.org.cris.cmm.sdms.entity.JobCardActivity;
import in.org.cris.cmm.sdms.service.JobCardActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job-card-activity")
public class JobCardActivityController {

        @Autowired
        private JobCardActivityService service;

        @PostMapping("/saveOrUpdate")
        public ResponseEntity<?> saveOrUpdate(@RequestBody JobCardActivityDTO dto) {

                JobCardActivity saved = service.saveOrUpdate(dto);

                int status = (dto.getActivityId() == null) ? 201 : 200;

                return ResponseEntity.status(status)
                        .body(new APIsResponse<>(
                                status,
                                (dto.getActivityId() == null)
                                        ? "Activity created successfully"
                                        : "Activity updated successfully",
                                saved
                        ));
        }

        @PostMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable Long id) {

                String msg = service.delete(id);

                return ResponseEntity.ok(
                        new APIsResponse<>(200, msg, null)
                );
        }

        @GetMapping("/active-list")
        public ResponseEntity<ApiResponse<?>> getAllActive() {

                List<JobCardActivity> list = service.getAllActivities();

                ApiResponse<List<JobCardActivity>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "Successful"
                        );

                return ResponseEntity.ok(response);
        }
}
