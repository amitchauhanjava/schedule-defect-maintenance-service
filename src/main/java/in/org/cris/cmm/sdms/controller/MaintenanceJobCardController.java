package in.org.cris.cmm.sdms.controller;

import in.org.cris.cmm.sdms.dto.APIsResponse;
import in.org.cris.cmm.sdms.dto.ApiResponse;
import in.org.cris.cmm.sdms.dto.MaintenanceJobCardDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceJobCard;
import in.org.cris.cmm.sdms.service.MaintenanceJobCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-job-card")
public class MaintenanceJobCardController {

        @Autowired
        private MaintenanceJobCardService service;

        @PostMapping("/saveOrUpdate")
        public ResponseEntity<?> saveOrUpdate(@RequestBody MaintenanceJobCardDTO dto) {

                MaintenanceJobCard saved = service.saveOrUpdate(dto);

                int status = (dto.getJobCardId() == null) ? 201 : 200;

                return ResponseEntity.status(status)
                        .body(new APIsResponse<>(status,
                                (dto.getJobCardId() == null)
                                        ? "Record created successfully"
                                        : "Record updated successfully",
                                saved));
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

                List<MaintenanceJobCard> list = service.getAllValidJobCards();

                ApiResponse<List<MaintenanceJobCard>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "Successful"
                        );

                return ResponseEntity.ok(response);
        }
}
