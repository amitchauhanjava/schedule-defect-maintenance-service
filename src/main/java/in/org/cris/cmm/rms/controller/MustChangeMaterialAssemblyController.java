package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MustChangeMaterialAssemblyMaster;
import in.org.cris.cmm.rms.service.MustChangeMaterialAssemblyService;
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

        @GetMapping("/active")
        public ResponseEntity<ApiResponse<?>> getAllActiveMustChangeMaterials() {

                List<MustChangeMaterialAssemblyMaster> list =
                        mustChangeMaterialAssemblyService.getAllValidMustChangeMaterials();

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
