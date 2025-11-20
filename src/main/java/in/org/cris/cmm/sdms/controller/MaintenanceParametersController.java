package in.org.cris.cmm.sdms.controller;


import in.org.cris.cmm.sdms.dto.APIsResponse;
import in.org.cris.cmm.sdms.dto.ApiResponse;
import in.org.cris.cmm.sdms.dto.MaintenanceParametersDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceParametersMaster;
import in.org.cris.cmm.sdms.service.MaintenanceParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-parameters")
public class MaintenanceParametersController {

        @Autowired
        private MaintenanceParametersService maintenanceParametersService;

        @PostMapping("/saveOrUpdate")
        public ResponseEntity<?> saveOrUpdate(@RequestBody MaintenanceParametersDTO dto) {

                MaintenanceParametersMaster saved = maintenanceParametersService.saveOrUpdate(dto);

                int status = (dto.getParameterId() == null) ? 201 : 200;

                return ResponseEntity.status(status)
                        .body(new APIsResponse<>(status,
                                (dto.getParameterId() == null) ?
                                        "Record created successfully" :
                                        "Record updated successfully",
                                saved));
        }

        @PostMapping("/delete/{id}")
        public ResponseEntity<?> softDelete(@PathVariable Long id) {

                String message = maintenanceParametersService.softDelete(id);

                return ResponseEntity.status(200)
                        .body(new APIsResponse<>(200, message, null));
        }

        @GetMapping("/active-list")
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
