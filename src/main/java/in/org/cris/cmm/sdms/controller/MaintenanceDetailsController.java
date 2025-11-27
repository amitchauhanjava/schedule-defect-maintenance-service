package in.org.cris.cmm.sdms.controller;

import in.org.cris.cmm.sdms.dto.APIsResponse;
import in.org.cris.cmm.sdms.dto.ApiResponse;
import in.org.cris.cmm.sdms.dto.MaintenanceDetailsDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceDetails;
import in.org.cris.cmm.sdms.service.MaintenanceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-details")
public class MaintenanceDetailsController {

        @Autowired
        private MaintenanceDetailsService service;

        @PostMapping("/saveOrUpdate")
        public ResponseEntity<?> saveOrUpdate(@RequestBody MaintenanceDetailsDTO dto) {

                MaintenanceDetails saved = service.saveOrUpdate(dto);

                int status = (dto.getMaintenanceId() == null) ? 201 : 200;

                return ResponseEntity.status(status)
                        .body(new APIsResponse<>(status,
                                (dto.getMaintenanceId() == null)
                                        ? "Record created successfully"
                                        : "Record updated successfully",
                                saved));
        }

        @PostMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable Long id) {

                String msg = service.delete(id);

                return ResponseEntity.ok(
                        new APIsResponse<>(200,
                                msg,
                                null
                        )
                );
        }

        @GetMapping("/active-list")
        public ResponseEntity<ApiResponse<?>> getAllActiveMaintenanceDetails() {

                List<MaintenanceDetails> list = service.getAllValidMaintenanceDetails();
                ApiResponse<List<MaintenanceDetails>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "Successful"
                        );

                return ResponseEntity.ok(response);
        }
}
