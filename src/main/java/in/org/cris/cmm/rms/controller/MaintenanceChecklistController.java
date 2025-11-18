package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MaintenanceChecklistMaster;
import in.org.cris.cmm.rms.service.MaintenanceChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-checklist")
public class MaintenanceChecklistController {
        @Autowired
        private MaintenanceChecklistService maintenanceChecklistService;

        @GetMapping("/active")
        public ResponseEntity<ApiResponse<?>> getAllActiveChecklistItems() {
                List<MaintenanceChecklistMaster> list = maintenanceChecklistService.getAllValidChecklistItems();
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
