package in.org.cris.cmm.rms.controller;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MaintenanceTaskListMaster;
import in.org.cris.cmm.rms.service.MaintenanceTaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/maintenance-task")
public class MaintenanceTaskListController {

    @Autowired
    private MaintenanceTaskListService service;

    @GetMapping("/active")
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

