package in.org.cris.cmm.sdms.controller;

import in.org.cris.cmm.sdms.dto.APIsResponse;
import in.org.cris.cmm.sdms.dto.ApiResponse;
import in.org.cris.cmm.sdms.dto.MaintenanceDefectTxnDTO;
import in.org.cris.cmm.sdms.dto.MaintenanceDefectTxnRequestDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceDefectTxn;
import in.org.cris.cmm.sdms.service.MaintenanceDefectTxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

        @GetMapping("/filter-list")
        public ResponseEntity<ApiResponse<?>> getMaintenanceDefects(
                @RequestParam(required = false) Long defectTxnId,
                @RequestParam(required = false) Long maintenanceId,
                @RequestParam(required = false) Long defectId,
                @RequestParam(required = false) Long actionId,
                @RequestParam(required = false) Long checklistTxnId) {

                List<MaintenanceDefectTxn> list = service.getMaintenanceDefects(defectTxnId, maintenanceId, defectId, actionId, checklistTxnId);
                ApiResponse<List<MaintenanceDefectTxn>> response = new ApiResponse<>(list.size(), list, HttpStatus.OK.value(), "Successful");

                return ResponseEntity.ok(response);
        }

        @PostMapping("/save-or-update")
        public ResponseEntity<?> saveOrUpdateDefects(
                @RequestBody List<MaintenanceDefectTxnRequestDTO> dtoList) {

                return ResponseEntity.ok(
                        Map.of(
                                "status", 200,
                                "message", "Defects saved/updated successfully",
                                "data", service.saveOrUpdateDefects(dtoList)
                        )
                );
        }

}
