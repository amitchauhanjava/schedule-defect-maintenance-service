package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.APIsResponse;
import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.MaintenanceParamTxnDTO;
import in.org.cris.cmm.rms.entity.MaintenanceParamTxn;
import in.org.cris.cmm.rms.service.MaintenanceParamTxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-param-txn")
public class MaintenanceParamTxnController {

        @Autowired
        private MaintenanceParamTxnService service;

        @PostMapping("/saveOrUpdate")
        public ResponseEntity<?> saveOrUpdate(@RequestBody MaintenanceParamTxnDTO dto) {

                MaintenanceParamTxn saved = service.saveOrUpdate(dto);

                int status = (dto.getParamTxnId() == null) ? 201 : 200;

                return ResponseEntity.status(status)
                        .body(new APIsResponse<>(status,
                                (dto.getParamTxnId() == null)
                                        ? "Record created successfully"
                                        : "Record updated successfully",
                                saved));
        }

        @PostMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable Long id) {

                String msg = service.delete(id);

                return ResponseEntity.ok(
                        new APIsResponse<>(200,
                                msg,
                                null)
                );
        }

        @GetMapping("/active-list")
        public ResponseEntity<ApiResponse<?>> getAllActive() {

                List<MaintenanceParamTxn> list = service.getAllValidParamTxns();

                ApiResponse<List<MaintenanceParamTxn>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "Successful"
                        );

                return ResponseEntity.ok(response);
        }
}
