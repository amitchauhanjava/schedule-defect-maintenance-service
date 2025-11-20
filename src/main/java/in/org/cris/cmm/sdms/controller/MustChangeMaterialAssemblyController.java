package in.org.cris.cmm.sdms.controller;

import in.org.cris.cmm.sdms.dto.APIsResponse;
import in.org.cris.cmm.sdms.dto.ApiResponse;
import in.org.cris.cmm.sdms.dto.MustChangeMaterialAssemblyDTO;
import in.org.cris.cmm.sdms.entity.MustChangeMaterialAssemblyMaster;
import in.org.cris.cmm.sdms.service.MustChangeMaterialAssemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        public ResponseEntity<ApiResponse<?>> getAllActiveMustChangeMaterials() {
                List<MustChangeMaterialAssemblyMaster> list = mustChangeMaterialAssemblyService.getAllValidMustChangeMaterials();
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
