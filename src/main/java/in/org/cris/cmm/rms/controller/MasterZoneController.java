package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.service.MasterZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zone")
public class MasterZoneController {

    @Autowired
    private MasterZoneService masterZoneService;

    /*@GetMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getMasterZone() {

        List<MasterZone> zoneList = masterZoneService.getListMasterZone();

        if (zoneList == null || zoneList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No records found", 0,null));
        } else {
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("totalRecords", zoneList.size());
            responseData.put("records", zoneList);
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Records fetched successfully.", 0, responseData));
        }
    }*/
}
