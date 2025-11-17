package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.service.MasterDivisionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/division")
@Slf4j
public class MasterDivisionController {

    @Autowired
    private MasterDivisionService masterDivisionService;

    /*@GetMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getMasterDivisionList() {

        log.info("Fetching division list...");
        List<MasterDivision> divisionList = masterDivisionService.getMasterDivisionList();

        if (divisionList == null || divisionList.isEmpty()) {
            log.warn("No records found...");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No records found",0,null));
        } else {
            Map<String, Object> responseData = new LinkedHashMap<>();
            responseData.put("totalRecodrs", divisionList.size());
            responseData.put("records", divisionList);

//            log.info("total records: "+divisionList.size());

            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(),"Records fetched successfully.",0,  responseData));
        }
    }*/
}
