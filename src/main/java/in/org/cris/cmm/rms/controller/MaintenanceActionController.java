package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MaintenanceActionMaster;
import in.org.cris.cmm.rms.service.MaintenanceActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-action")
public class MaintenanceActionController {
        @Autowired
        private MaintenanceActionService maintenanceActionService;
        @GetMapping("/active")
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
}
