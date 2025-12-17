package in.org.cris.cmm.lsin.dto;

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
    private Integer slNo;
    private String assembly;
    private String subAssembly;
    private Integer itemNo;
    private String checklistItem;
    private String coachKind;
    private String utilityType;
    private Boolean mandCondition;
    private String method;
    private String assetType;
    private String maintenanceType;
}

