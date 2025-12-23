package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.APIsResponse;
import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.MustChangeMaterialAssemblyDTO;
import in.org.cris.cmm.rms.entity.MustChangeMaterialAssemblyMaster;
import in.org.cris.cmm.rms.service.MustChangeMaterialAssemblyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/must-change-material")
public class MustChangeMaterialAssemblyController {

        @Autowired
        private MustChangeMaterialAssemblyService mustChangeMaterialAssemblyService;

        @PostMapping("/saveOrUpdate")
        public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody MustChangeMaterialAssemblyDTO dto) {

                MustChangeMaterialAssemblyMaster result = mustChangeMaterialAssemblyService.saveOrUpdate(dto);
                int status = (dto.getMustChangeId() == null) ? 201 : 200;

                return ResponseEntity.status(status)
                        .body(new APIsResponse<>(status,
                                (dto.getMustChangeId() == null) ?
                                        "Record created successfully" :
                                        "Record updated successfully",
                                result));
        }

        @PostMapping("/delete/{id}")
        public ResponseEntity<?> softDelete(@PathVariable Long id) {

                String message = mustChangeMaterialAssemblyService.softDelete(id);
                return ResponseEntity.status(200).body(new APIsResponse<>(200, message, null));
        }


        @GetMapping("/active-list")
        public ResponseEntity<ApiResponse<?>> getAllActiveMustChangeMaterials(@RequestParam Long rsTypeMaintId) {
                List<MustChangeMaterialAssemblyMaster> list = mustChangeMaterialAssemblyService.getAllValidMustChangeMaterials(rsTypeMaintId);
                ApiResponse<List<MustChangeMaterialAssemblyMaster>> response =
                        new ApiResponse<>(
                                list.size(),
                                list,
                                HttpStatus.OK.value(),
                                "successful"
                        );
                return ResponseEntity.ok(response);
        }
}
