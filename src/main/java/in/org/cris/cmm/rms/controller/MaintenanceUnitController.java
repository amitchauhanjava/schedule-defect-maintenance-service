package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MaintenanceUnit;
import in.org.cris.cmm.rms.service.MaintenanceUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mu")
public class MaintenanceUnitController {

    @Autowired
    private MaintenanceUnitService service;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<ApiResponse<MaintenanceUnit>> saveOrUpdate(@RequestBody MaintenanceUnit unit) {
        MaintenanceUnit saved = service.saveOrUpdate(unit);
        String message = (unit.getMuId() == null)
                ? "Maintenance Unit created successfully."
                : "Maintenance Unit updated successfully.";

        return new ResponseEntity<>(
                new ApiResponse<>( 0,saved, HttpStatus.OK.value(),message),
                HttpStatus.OK
        );
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MaintenanceUnit>> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(unit -> new ResponseEntity<>(
                        new ApiResponse<>(0, unit, HttpStatus.OK.value(), "Record fetched successfully."),
                        HttpStatus.OK
                ))
                .orElseGet(() -> new ResponseEntity<>(
                        new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Record not found with id: " + id, 0, null),
                        HttpStatus.NOT_FOUND
                ));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<MaintenanceUnit>>> getAll() {
        List<MaintenanceUnit> list = service.getAll();
        return new ResponseEntity<>(
                new ApiResponse<>(HttpStatus.OK.value(), "Records fetched successfully.",0, list),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(
                new ApiResponse<>(HttpStatus.OK.value(), "Deleted successfully with id: " + id, 0,null),
                HttpStatus.OK
        );
    }*/
}
