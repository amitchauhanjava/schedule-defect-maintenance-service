package in.org.cris.cmm.sdms.controller;

import in.org.cris.cmm.sdms.dto.ApiResponse;
import in.org.cris.cmm.sdms.entity.MasterTable;
import in.org.cris.cmm.sdms.service.MasterTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/master-table")
@RequiredArgsConstructor
public class MasterTableController {

	private final MasterTableService service;

	@GetMapping("/by-type")
	public ResponseEntity<ApiResponse<List<MasterTable>>> getByType(@RequestParam String type) {

		List<MasterTable> data = service.getByType(type);

		String message;
		if (data == null || data.isEmpty()) {
			message = "No data found for given type";
		} else {
			message = "Data fetched successfully";
		}

		ApiResponse<List<MasterTable>> response =
				new ApiResponse<>(
						data == null ? 0 : data.size(),
						data,
						HttpStatus.OK.value(),
						message
				);

		return ResponseEntity.ok(response);
	}
}
