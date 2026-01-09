package in.org.cris.cmm.sdms.controller;

import in.org.cris.cmm.sdms.dto.APIsResponse;
import in.org.cris.cmm.sdms.dto.ApiResponse;
import in.org.cris.cmm.sdms.dto.MaintenanceJobCardDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceJobCard;
import in.org.cris.cmm.sdms.service.MaintenanceJobCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        public ResponseEntity<ApiResponse<?>> getAllActive(@RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate) {
                List<MaintenanceJobCard> list = service.getAllValidJobCards(fromDate, toDate);

                return ResponseEntity.ok(
                        new ApiResponse<>(list.size(), list, HttpStatus.OK.value(), "Successful")
                );
        }


        @GetMapping("/pending")
        public ResponseEntity<Map<String, Object>> getPendingJobCards(
                @RequestParam String loginLevel,
                @RequestParam String loginCode,
                @RequestParam(required = false, defaultValue = "") String search,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                @RequestParam(defaultValue = "id") String sortBy,
                @RequestParam(defaultValue = "desc") String direction
        ) {

                Page<MaintenanceJobCard> result = service.getJobCardWithFilters(loginLevel, loginCode, search, page, size, sortBy, direction);

                Map<String, Object> response = new HashMap<>();
                response.put("status", "SUCCESS");
                response.put("message", "Pending Job Cards fetched successfully");
                response.put("currentPage", result.getNumber());
                response.put("totalPages", result.getTotalPages());
                response.put("totalItems", result.getTotalElements());
                response.put("items", result.getContent());

                return ResponseEntity.ok(response);
        }
}
