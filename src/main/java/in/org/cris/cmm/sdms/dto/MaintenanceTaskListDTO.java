package in.org.cris.cmm.sdms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceTaskListDTO {

    private Long taskId;
    private Long rsTypeMaintenanceId;

    private String taskCode;
    private String taskDescription;
    private String skillRequired;
    private Integer estimatedTimeMinutes;
}

