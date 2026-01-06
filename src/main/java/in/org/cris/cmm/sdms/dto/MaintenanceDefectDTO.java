package in.org.cris.cmm.sdms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceDefectDTO {

    private Long defectId;
    private Long rsTypeMaintenanceId;
    private String defectCode;
    private String defectDescription;
    private String severity;
}
