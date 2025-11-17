package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.MaintenanceUnitTypeDTO;
import in.org.cris.cmm.rms.entity.MaintenanceUnitType;
import in.org.cris.cmm.rms.service.MaintenanceUnitTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mut")
public class MaintenanceUnitTypeController {

    @Autowired
    private MaintenanceUnitTypeService service;

    @PostMapping("/save-update")
    public ResponseEntity<List<MaintenanceUnitType>> saveOrUpdateAll(
            @RequestBody List<MaintenanceUnitTypeDTO> unitTypes) {

        List<MaintenanceUnitType> response = service.saveOrUpdateAll(unitTypes);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/by-id")
    public ResponseEntity<ApiResponse<List<MaintenanceUnitType>>> getById(@RequestParam Long id) {
        ApiResponse<List<MaintenanceUnitType>> response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-orgcode")
    public ResponseEntity<ApiResponse<List<MaintenanceUnitType>>> getByOrgCode() {
        ApiResponse<List<MaintenanceUnitType>> response = service.getByOrgCode();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deactivate(@PathVariable Long id) {
        ApiResponse<String> response = service.deleteById(id);
        HttpStatus status = response.getStatus() == 200 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }
}
