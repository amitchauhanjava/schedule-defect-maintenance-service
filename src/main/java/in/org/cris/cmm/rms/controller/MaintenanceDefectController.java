package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MaintenanceDefectMaster;
import in.org.cris.cmm.rms.repo.MaintenanceDefectRepository;
import in.org.cris.cmm.rms.service.MaintenanceDefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/maintenance-defect")
public class MaintenanceDefectController {
        @Autowired
        private MaintenanceDefectService maintenanceDefectService;
        @GetMapping("/active")
        public ResponseEntity<ApiResponse<?>> getAllActiveDefects() {
                List<MaintenanceDefectMaster> list = maintenanceDefectService.getAllValidDefects();
                ApiResponse<List<MaintenanceDefectMaster>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "successful"
                        );
                return ResponseEntity.ok(response);
        }
}