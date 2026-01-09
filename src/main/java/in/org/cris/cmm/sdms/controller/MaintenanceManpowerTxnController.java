package in.org.cris.cmm.sdms.controller;

import in.org.cris.cmm.sdms.dto.ApiResponse;
import in.org.cris.cmm.sdms.dto.MaintenanceManpowerTxnDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceManpowerTxn;
import in.org.cris.cmm.sdms.service.MaintenanceManpowerTxnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-manpower")
public class MaintenanceManpowerTxnController {

	private final MaintenanceManpowerTxnService service;

	public MaintenanceManpowerTxnController(
			MaintenanceManpowerTxnService service) {
		this.service = service;
	}

	@PostMapping("/save-or-update")
	public ResponseEntity<ApiResponse<List<MaintenanceManpowerTxnDTO>>> saveOrUpdate(@RequestBody List<MaintenanceManpowerTxnDTO> dtoList) {

		List<MaintenanceManpowerTxnDTO> data = service.saveOrUpdate(dtoList);

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
	public ResponseEntity<String> softDelete(@PathVariable Long id) {

		service.softDelete(id);
		return ResponseEntity.ok("Record deleted successfully");
	}


	@GetMapping("/active-list")
	public ResponseEntity<ApiResponse<List<MaintenanceManpowerTxn>>> activeList(@RequestParam(required = false) Long maintenanceId, @RequestParam(required = false) Long defectTxnId) {

		List<MaintenanceManpowerTxn> data = service.getActiveList(maintenanceId, defectTxnId);

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

