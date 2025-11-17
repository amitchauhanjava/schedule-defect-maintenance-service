package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.OrgTargetsDTO;
import in.org.cris.cmm.rms.entity.OrgTargets;
import in.org.cris.cmm.rms.service.OrgTargetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/org-targets")
@RequiredArgsConstructor
public class OrgTargetsController {

    private final OrgTargetsService orgTargetsService;

    @PostMapping("/save-update")
    public ResponseEntity<List<OrgTargets>> saveOrUpdate(@RequestBody List<OrgTargetsDTO> target) {
        List<OrgTargets> saved = orgTargetsService.saveOrUpdate(target);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/orgCode")
    public ResponseEntity<ApiResponse<List<OrgTargets>>> getByOrgCode() {
        ApiResponse<List<OrgTargets>> response = orgTargetsService.getByOrgCode();

        HttpStatus status = response.getRecordCount() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/{targetId}")
    public ResponseEntity<OrgTargets> getById(@PathVariable Long targetId) {
        Optional<OrgTargets> opt = orgTargetsService.getById(targetId);
        return opt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String message = orgTargetsService.deleteById(id);
        return ResponseEntity.ok(message);
    }
}
