package in.org.cris.cmm.sdms.controller;

import in.org.cris.cmm.sdms.dto.APIsResponse;
import in.org.cris.cmm.sdms.dto.ApiResponse;
import in.org.cris.cmm.sdms.dto.MaintenanceChecklistDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceChecklistMaster;
import in.org.cris.cmm.sdms.service.MaintenanceChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/maintenance-checklist")
public class MaintenanceChecklistController {
        @Autowired
        private MaintenanceChecklistService checklistService;

        @PostMapping("/saveOrUpdate")
        public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody MaintenanceChecklistDTO dto) {

                MaintenanceChecklistMaster saved = checklistService.saveOrUpdate(dto);
                int status = (dto.getChecklistId() == null) ? 201 : 200;
                return ResponseEntity.status(status)
                        .body(new APIsResponse<>(status, (dto.getChecklistId() == null) ? "Record created successfully" : "Record updated successfully", saved));
        }

        @PostMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable Long id) {

                String msg = checklistService.delete(id);
                Map<String, Object> response = new HashMap<>();
                response.put("status", "SUCCESS");
                response.put("message", msg);

                return ResponseEntity.ok(response);
        }

        @GetMapping("/active-list")
        public ResponseEntity<ApiResponse<?>> getAllActiveChecklistItems() {
                List<MaintenanceChecklistMaster> list = checklistService.getAllValidChecklistItems();
                ApiResponse<List<MaintenanceChecklistMaster>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "successful"
                        );
                return ResponseEntity.ok(response);
        }
}
