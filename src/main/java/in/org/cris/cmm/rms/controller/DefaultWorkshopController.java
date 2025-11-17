package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.DefaultWorkshopDTO;
import in.org.cris.cmm.rms.entity.DefaultWorkshop;
import in.org.cris.cmm.rms.service.DefaultWorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/default-workshop")
public class DefaultWorkshopController {

    @Autowired
    private DefaultWorkshopService defaultWorkshopService;

    @PostMapping("/save-update")
    public ResponseEntity<DefaultWorkshop> saveOrUpdate(@RequestBody DefaultWorkshopDTO workshop) {
        DefaultWorkshop savedWorkshop = defaultWorkshopService.saveOrUpdate(workshop);

        return ResponseEntity.ok().header("Message", workshop.getDefaultWorkshopId() == null ? "Default Workshop created successfully." : "Default Workshop updated successfully.").body(savedWorkshop);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> softDelete(@PathVariable("id") Long id) {

        boolean deleted = defaultWorkshopService.deleteById(id);

        if (deleted) {
            return ResponseEntity.ok("Record Deleted Successfully for ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Record not found for ID: " + id);
        }
    }

    @GetMapping("/listByOrg")
    public ResponseEntity<ApiResponse<List<DefaultWorkshop>>> getByOrgCode() {
        ApiResponse<List<DefaultWorkshop>> response = defaultWorkshopService.getByOrgCode();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        DefaultWorkshop workshop = defaultWorkshopService.getById(id);

        if (workshop != null) {
            return ResponseEntity.ok(workshop);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record found for the given ID: " + id);
        }
    }

}

