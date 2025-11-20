package in.org.cris.cmm.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceChecklistDTO {

    private Long checklistId;
    private Long rsTypeMaintenanceId;

    private Boolean safetyFlag;
    private Integer itemNo;
    private String checklistItem;
    private String method;
}

