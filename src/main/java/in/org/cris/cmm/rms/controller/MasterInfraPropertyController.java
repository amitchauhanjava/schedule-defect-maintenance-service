package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.entity.MasterInfraProperty;
import in.org.cris.cmm.rms.service.MasterInfraPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/masterInfraProperties")
@RequiredArgsConstructor
public class MasterInfraPropertyController {

    private final MasterInfraPropertyService service;

    @PostMapping
    public ResponseEntity<MasterInfraProperty> saveOrUpdate(@Valid @RequestBody MasterInfraProperty property) {
        MasterInfraProperty saved = service.saveOrUpdate(property);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterInfraProperty> getById(@PathVariable Long id) {
        MasterInfraProperty property = service.getById(id);
        return ResponseEntity.ok(property);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String message = service.deleteById(id);
        return ResponseEntity.ok(message);
    }
}
