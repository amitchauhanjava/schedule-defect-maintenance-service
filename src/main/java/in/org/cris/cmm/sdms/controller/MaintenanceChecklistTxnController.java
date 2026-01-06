package in.org.cris.cmm.sdms.controller;

import in.org.cris.cmm.sdms.dto.APIsResponse;
import in.org.cris.cmm.sdms.dto.ApiResponse;
import in.org.cris.cmm.sdms.dto.MaintenanceChecklistTxnDTO;
import in.org.cris.cmm.sdms.dto.MaintenanceChecklistTxnRequestDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceChecklistTxn;
import in.org.cris.cmm.sdms.service.MaintenanceChecklistTxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/maintenance-checklist-txn")
public class MaintenanceChecklistTxnController {

        @Autowired
        private MaintenanceChecklistTxnService service;

        @PostMapping("/saveOrUpdate")
        public ResponseEntity<?> saveOrUpdate(@RequestBody MaintenanceChecklistTxnDTO dto) {

                MaintenanceChecklistTxn saved = service.saveOrUpdate(dto);
                int status = (dto.getChecklistTxnId() == null) ? 201 : 200;
                return ResponseEntity.status(status)
                        .body(new APIsResponse<>(status,
                                (dto.getChecklistTxnId() == null) ? "Record created successfully" : "Record updated successfully", saved));
        }

        @PostMapping("/delete/{id}")
        public ResponseEntity<?> delete(@PathVariable Long id) {

                String msg = service.delete(id);
                return ResponseEntity.ok(
                        new APIsResponse<>(200,
                                msg,
                                null
                        )
                );
        }

        @GetMapping("/active-list")
        public ResponseEntity<ApiResponse<?>> getAllActive() {

                List<MaintenanceChecklistTxn> list = service.getAllValidChecklistTxns();
                ApiResponse<List<MaintenanceChecklistTxn>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "Successful"
                        );

                return ResponseEntity.ok(response);
        }

        @PostMapping("/save-or-update")
        public ResponseEntity<?> saveOrUpdate(
                @RequestBody List<MaintenanceChecklistTxnRequestDTO> dtoList) {

                return ResponseEntity.ok(
                        Map.of(
                                "status", 200,
                                "message", "Checklist saved/updated successfully",
                                "data", service.saveOrUpdateChecklist(dtoList)
                        )
                );
        }

        @GetMapping("/filter-list")
        public ResponseEntity<ApiResponse<?>> getAllActive(
                @RequestParam(required = false) Long checklistTxnId,
                @RequestParam(required = false) Long maintenanceId,
                @RequestParam(required = false) Long checklistId) {

                List<MaintenanceChecklistTxn> list = service.getActiveChecklistTxn(checklistTxnId, maintenanceId, checklistId);

                ApiResponse<List<MaintenanceChecklistTxn>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "Successful"
                        );

                return ResponseEntity.ok(response);
        }

}