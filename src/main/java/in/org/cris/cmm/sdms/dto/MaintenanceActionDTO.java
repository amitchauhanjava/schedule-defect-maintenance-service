package in.org.cris.cmm.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceActionDTO {

    private Long actionId;
    private Long rsTypeMaintenanceId;

    private String actionCode;
    private String actionDescription;
}

