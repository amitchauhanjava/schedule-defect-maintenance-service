package in.org.cris.cmm.rms.controller;

import javax.validation.Valid;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.InfraLineDTO;
import in.org.cris.cmm.rms.entity.InfraLine;
import in.org.cris.cmm.rms.service.InfraLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/infra-lines")
@RequiredArgsConstructor
public class InfraLineController {

    private final InfraLineService service;

    @PostMapping("/save-update")
    public ResponseEntity<List<InfraLine>> saveOrUpdate(@Valid @RequestBody List<InfraLineDTO> lines) {
        List<InfraLine> saved = service.saveOrUpdate(lines);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<InfraLine>>> getList() {
        ApiResponse<List<InfraLine>> response = service.getList();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<InfraLine>>> getById(@PathVariable Long id) {
        ApiResponse<List<InfraLine>> response = service.getDetailsById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String message = service.deleteById(id);
        return ResponseEntity.ok(message);
    }
}