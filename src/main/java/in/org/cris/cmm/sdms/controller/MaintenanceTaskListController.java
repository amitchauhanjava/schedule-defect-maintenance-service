package in.org.cris.cmm.sdms.controller;

import in.org.cris.cmm.sdms.dto.APIsResponse;
import in.org.cris.cmm.sdms.dto.ApiResponse;
import in.org.cris.cmm.sdms.dto.MaintenanceTaskListDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceTaskListMaster;
import in.org.cris.cmm.sdms.service.MaintenanceTaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance-task")
public class MaintenanceTaskListController {

    @Autowired
    private MaintenanceTaskListService service;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody MaintenanceTaskListDTO dto) {

        MaintenanceTaskListMaster saved = service.saveOrUpdate(dto);

        int status = (dto.getTaskId() == null) ? 201 : 200;

        return ResponseEntity.status(status)
                .body(new APIsResponse<>(status,
                        (dto.getTaskId() == null) ? "Task created successfully" : "Task updated successfully",
                        saved));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> softDelete(@PathVariable Long id) {

        service.softDelete(id);
        return ResponseEntity.status(200)
                .body(new APIsResponse<>(200, "Task soft-deleted successfully", null));
    }

    @GetMapping("/active-list")
    public ResponseEntity<ApiResponse<?>> getAllActiveTasks() {
        List<MaintenanceTaskListMaster> list = service.getAllValidTasks();

        ApiResponse<List<MaintenanceTaskListMaster>> response =
                new ApiResponse<>(
                        list.size(),                      // recordCount
                        list,                             // data
                        HttpStatus.OK.value(),                             // status
                        "Maintenance tasks fetched successfully"  // message
                );

        return ResponseEntity.ok(response);
    }
}

