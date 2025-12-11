package in.org.cris.cmm.lsin.controller;

import in.org.cris.cmm.lsin.dto.APIsResponse;
import in.org.cris.cmm.lsin.dto.ApiResponse;
import in.org.cris.cmm.lsin.dto.MaintenanceDefectTxnDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceDefectTxn;
import in.org.cris.cmm.lsin.service.MaintenanceDefectTxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-defect-txn")
public class MaintenanceDefectTxnController {

        @Autowired
        private MaintenanceDefectTxnService service;

        @PostMapping("/saveOrUpdate")
        public ResponseEntity<?> saveOrUpdate(@RequestBody MaintenanceDefectTxnDTO dto) {

                MaintenanceDefectTxn saved = service.saveOrUpdate(dto);

                int status = (dto.getDefectTxnId() == null) ? 201 : 200;

                return ResponseEntity.status(status)
                        .body(new APIsResponse<>(status,
                                (dto.getDefectTxnId() == null)
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

                List<MaintenanceDefectTxn> list = service.getAllValidDefectTxns();

                ApiResponse<List<MaintenanceDefectTxn>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "Successful"
                        );

                return ResponseEntity.ok(response);
        }
}
