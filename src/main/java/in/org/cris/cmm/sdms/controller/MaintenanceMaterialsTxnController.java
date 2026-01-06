package in.org.cris.cmm.sdms.controller;

import in.org.cris.cmm.sdms.dto.ApiResponse;
import in.org.cris.cmm.sdms.dto.MaintenanceMaterialsTxnDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceMaterialsTxn;
import in.org.cris.cmm.sdms.service.MaintenanceMaterialsTxnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-materials")
@RequiredArgsConstructor
public class MaintenanceMaterialsTxnController {

	private final MaintenanceMaterialsTxnService service;

	@PostMapping("/save-or-update")
	public ResponseEntity<ApiResponse<List<MaintenanceMaterialsTxn>>> saveOrUpdate(@RequestBody List<MaintenanceMaterialsTxnDTO> dtoList) {

		List<MaintenanceMaterialsTxn> data = service.saveOrUpdate(dtoList);
		return ResponseEntity.ok(
				new ApiResponse<>(
						data.size(),
						data,
						HttpStatus.OK.value(),
						"Records saved/updated successfully"
				)
		);
	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {

		service.delete(id);
		return ResponseEntity.ok("Record deleted successfully");
	}

	@GetMapping("/active-list")
	public ResponseEntity<ApiResponse<List<MaintenanceMaterialsTxn>>> activeList(
			@RequestParam(required = false) Long maintenanceId,
			@RequestParam(required = false) Long defectTxnId) {

		List<MaintenanceMaterialsTxn> data = service.getActiveList(maintenanceId, defectTxnId);

		String message = data.isEmpty() ? "No active records found" : "Active records fetched successfully";

		return ResponseEntity.ok(
				new ApiResponse<>(
						data.size(),
						data,
						HttpStatus.OK.value(),
						message
				)
		);
	}
}

