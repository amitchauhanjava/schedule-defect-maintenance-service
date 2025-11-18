package in.org.cris.cmm.rms.controller;


import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MaintenanceParametersMaster;
import in.org.cris.cmm.rms.service.MaintenanceParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/maintenance-parameters")
public class MaintenanceParametersController {
        @Autowired
        private MaintenanceParametersService maintenanceParametersService;

        @GetMapping("/active")
        public ResponseEntity<ApiResponse<?>> getAllActiveParameters() {

                List<MaintenanceParametersMaster> list = maintenanceParametersService.getAllValidParameters();

                ApiResponse<List<MaintenanceParametersMaster>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "successful"
                        );
                return ResponseEntity.ok(response);
        }
}
