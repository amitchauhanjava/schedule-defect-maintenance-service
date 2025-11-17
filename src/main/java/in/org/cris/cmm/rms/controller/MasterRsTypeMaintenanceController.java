package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.MasterRsTypeMaintenanceDTO;
import in.org.cris.cmm.rms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.rms.service.MasterRsTypeMaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mrt-maintenance")
@RequiredArgsConstructor
public class MasterRsTypeMaintenanceController {

    private final MasterRsTypeMaintenanceService service;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<List<MasterRsTypeMaintenance>> saveOrUpdate(@RequestBody List<MasterRsTypeMaintenanceDTO> list) {
        List<MasterRsTypeMaintenance> result = service.saveOrUpdate(list);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<MasterRsTypeMaintenance>>> getList() {
        ApiResponse<List<MasterRsTypeMaintenance>> response = service.getList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        String message = service.deleteById(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/by-maintenance-type/{maintenanceTypeId}")
    public ResponseEntity<ApiResponse<List<MasterRsTypeMaintenance>>> getByMaintenanceTypeId(
            @PathVariable Long maintenanceTypeId) {
        ApiResponse<List<MasterRsTypeMaintenance>> response = service.getByMaintenanceTypeId(maintenanceTypeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-rs-type/{rsTypeId}")
    public ResponseEntity<ApiResponse<List<MasterRsTypeMaintenance>>> getByRsTypeId(
            @PathVariable Long rsTypeId) {
        ApiResponse<List<MasterRsTypeMaintenance>> response = service.getByRsTypeId(rsTypeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
