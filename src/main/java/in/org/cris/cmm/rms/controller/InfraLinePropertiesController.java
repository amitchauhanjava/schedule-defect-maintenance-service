package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.APIsResponse;
import in.org.cris.cmm.rms.dto.InfraLinePropertiesDTO;
import in.org.cris.cmm.rms.entity.InfraLineProperties;
import in.org.cris.cmm.rms.service.InfraLinePropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/infraLine-properties")
public class InfraLinePropertiesController {

    @Autowired
    private InfraLinePropertiesService service;


    @PostMapping("/saveOrUpdate")
    public ResponseEntity<APIsResponse<InfraLineProperties>> saveOrUpdate(@Valid @RequestBody InfraLinePropertiesDTO dto) {

        InfraLineProperties saved = service.saveOrUpdate(dto);

        String message = (dto.getId() == null) ? "Record created successfully with ID: " + saved.getId() : "Record updated successfully for ID: " + saved.getId();
        APIsResponse<InfraLineProperties> response = new APIsResponse<>(HttpStatus.OK.value(), message, saved);
        return ResponseEntity.ok(response);
    }






    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<InfraLineProperties> entity = service.getById(id);
        return entity.<ResponseEntity<?>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found for ID: " + id));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> softDelete(@PathVariable Long id) {
        boolean deleted = service.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok("Record Deleted Successfully for ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found for ID: " + id);
        }
    }
}
