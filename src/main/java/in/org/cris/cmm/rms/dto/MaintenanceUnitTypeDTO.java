package in.org.cris.cmm.rms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MaintenanceUnitTypeDTO {

    private Long id;
    private String orgCode;
    private Long masterRsTypeMaintenanceId;
    private String description;
    private String createdBy;
    private String updatedBy;
    private Date createdAt;
    private Date updatedAt;
    private Boolean validFlag;
}
