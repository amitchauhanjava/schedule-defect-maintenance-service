package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.WorkcenterDTO;
import in.org.cris.cmm.rms.entity.Workcenter;
import in.org.cris.cmm.rms.service.WorkcenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/workcenters")
public class WorkcenterController {

    @Autowired
    private WorkcenterService workcenterService;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<Workcenter> saveOrUPdate(@Valid @RequestBody WorkcenterDTO workcenter) {

        Workcenter save = workcenterService.saveOrUpdate(workcenter);
        if (workcenter.getWorkcenterId() == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        } else{
            return ResponseEntity.ok(save);
        }
    }

    @GetMapping("/by-org")
    public ResponseEntity<ApiResponse<List<Workcenter>>> getByOrgCode() {
        ApiResponse<List<Workcenter>> response = workcenterService.getDetailsByOrgCode();
        HttpStatus status = response.getRecordCount() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<List<Workcenter>>> getById(@PathVariable Long id) {
        ApiResponse<List<Workcenter>> response = workcenterService.getDetailsById(id);
        HttpStatus status = response.getRecordCount() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boolean deleted = workcenterService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok("Workcenter deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Workcenter not found for id: " + id);
        }
    }
}
