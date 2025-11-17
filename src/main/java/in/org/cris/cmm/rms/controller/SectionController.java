package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.Section;
import in.org.cris.cmm.rms.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sections")
@Slf4j
public class SectionController {

    @Autowired
    private SectionService service;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<Section> saveOrUpdate(@Valid @RequestBody Section section) {
        Section savedSection = service.saveOrUpdate(section);

        if (section.getSectionId() == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSection);
        } else {
            return ResponseEntity.ok(savedSection);
        }
    }



    @GetMapping("/by-org/{orgCode}")
    public ResponseEntity<ApiResponse<List<Section>>> getSectionsByOrgCode(@PathVariable String orgCode) {
        ApiResponse<List<Section>> list = service.getByOrgCode(orgCode);

            return ResponseEntity.ok(list);
    }

    @GetMapping("/by-section-id/{id}")
    public ResponseEntity<ApiResponse<List<Section>>> getSectionsById(@PathVariable Long id) {
        ApiResponse<List<Section>> list = service.getById(id);

        return ResponseEntity.ok(list);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteSectionById(@PathVariable Long id) {
        String message = service.deleteById(id);

        if (message.startsWith("Section not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }
}

