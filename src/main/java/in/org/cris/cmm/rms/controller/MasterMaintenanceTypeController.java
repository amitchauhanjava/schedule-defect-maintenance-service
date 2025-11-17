package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MasterMaintenanceType;
import in.org.cris.cmm.rms.service.MasterMaintenanceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-types")
public class MasterMaintenanceTypeController {

    @Autowired
    private MasterMaintenanceTypeService service;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<MasterMaintenanceType>>> getAllMaintenanceTypes() {
        ApiResponse<List<MasterMaintenanceType>> response = service.getAllMaintenanceTypes();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
