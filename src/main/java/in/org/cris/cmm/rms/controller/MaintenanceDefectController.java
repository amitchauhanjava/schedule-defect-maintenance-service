package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.APIsResponse;
import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.MaintenanceDefectDTO;
import in.org.cris.cmm.rms.dto.MaintenanceDefectResponseDTO;
import in.org.cris.cmm.rms.entity.MaintenanceDefectMaster;
import in.org.cris.cmm.rms.service.MaintenanceDefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-defect")
public class MaintenanceDefectController {

        @Autowired
        private MaintenanceDefectService defectService;

        @PostMapping("/saveOrUpdate")
        public ResponseEntity<?> saveOrUpdate(@RequestBody MaintenanceDefectDTO dto) {

                MaintenanceDefectMaster saved = defectService.saveOrUpdate(dto);

                int status = (dto.getDefectId() == null) ? 201 : 200;

                return ResponseEntity.status(status)
                        .body(new APIsResponse<>(
                                status,
                                (dto.getDefectId() == null) ?
                                        "Record created successfully" :
                                        "Record updated successfully",
                                saved
                        ));
        }

        @PostMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable Long id) {

                String msg = defectService.delete(id);

                return ResponseEntity.ok(
                        new APIsResponse<>(
                                200,           // fixed success status
                                msg,           // message from service
                                null           // no data for delete operation
                        )
                );
        }

        @GetMapping("/active-list")
        public ResponseEntity<ApiResponse<?>> getAllActiveDefects() {

                List<MaintenanceDefectMaster> list = defectService.getAllValidDefects();
                ApiResponse<List<MaintenanceDefectMaster>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "successful"
                        );
                return ResponseEntity.ok(response);
        }

        @GetMapping("/filter-list")
        public ResponseEntity<ApiResponse<?>> getAllActiveDefects(@RequestParam(required = false) Long rsTypeMaintenanceId) {

                List<MaintenanceDefectResponseDTO> list = defectService.getDefects(rsTypeMaintenanceId);
                ApiResponse<List<MaintenanceDefectResponseDTO>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "successful"
                        );

                return ResponseEntity.ok(response);
        }

}