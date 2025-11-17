package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MasterLineType;
import in.org.cris.cmm.rms.service.MasterLineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/master-line-type")
public class MasterLineTypeController {

    @Autowired
    private MasterLineTypeService masterLineTypeService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<MasterLineType>>> getList() {
        ApiResponse<List<MasterLineType>> response = masterLineTypeService.getList();
        HttpStatus status = response.getRecordCount() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

}
