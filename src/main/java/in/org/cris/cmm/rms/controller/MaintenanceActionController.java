package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.APIsResponse;
import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.MaintenanceActionDTO;
import in.org.cris.cmm.rms.dto.MaintenanceActionResponseDTO;
import in.org.cris.cmm.rms.entity.MaintenanceActionMaster;
import in.org.cris.cmm.rms.service.MaintenanceActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/maintenance-action")
public class MaintenanceActionController {

        @Autowired
        private MaintenanceActionService maintenanceActionService;

        @PostMapping("/saveOrUpdate")
        public ResponseEntity<?> saveOrUpdate(@RequestBody MaintenanceActionDTO dto) {

                MaintenanceActionMaster saved = maintenanceActionService.saveOrUpdate(dto);
                int status = (dto.getActionId() == null) ? 201 : 200;
                return ResponseEntity.status(status)
                        .body(new APIsResponse<>(status, (dto.getActionId() == null) ? "Record created successfully" : "Record updated successfully", saved ));
        }

        @PostMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable Long id) {

                String msg = maintenanceActionService.delete(id);
                Map<String, Object> response = new HashMap<>();
                response.put("status", "SUCCESS");
                response.put("message", msg);
                return ResponseEntity.ok(response);
        }

        @GetMapping("/active-list")
        public ResponseEntity<ApiResponse<?>> getAllActiveActions() {
                List<MaintenanceActionMaster> list = maintenanceActionService.getAllValidActions();
                ApiResponse<List<MaintenanceActionMaster>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "successful     "
                        );
                return ResponseEntity.ok(response);
        }

        @GetMapping("/filter-list")
        public ResponseEntity<ApiResponse<?>> getAllActiveActions(@RequestParam(required = false) Long rsTypeMaintenanceId) {

                List<MaintenanceActionResponseDTO> list = maintenanceActionService.getAllValidActions(rsTypeMaintenanceId);
                ApiResponse<List<MaintenanceActionResponseDTO>> response = new ApiResponse<>(list.size(), list, HttpStatus.OK.value(), "successful");
                return ResponseEntity.ok(response);
        }
}
