package in.org.cris.cmm.rms.controller;

import javax.validation.Valid;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MasterInfraLine;
import in.org.cris.cmm.rms.service.MasterInfraLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/master-infra-line")
@RequiredArgsConstructor
public class MasterInfraLineController {

    private final MasterInfraLineService service;

    @PostMapping("/save-update")
    public ResponseEntity<MasterInfraLine> saveOrUpdate(@Valid @RequestBody MasterInfraLine line) {
        MasterInfraLine saved = service.saveOrUpdate(line);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<MasterInfraLine>>> getList() {
        ApiResponse<List<MasterInfraLine>> response = service.getList();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<MasterInfraLine>>> getById(@PathVariable Long id) {
        ApiResponse<List<MasterInfraLine>> response = service.getDetailsById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String message = service.deleteById(id);
        return ResponseEntity.ok(message);
    }
}
