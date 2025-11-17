package in.org.cris.cmm.rms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DefaultWorkshopDTO {

    private Long defaultWorkshopId;

    private String orgCode;

    private Long masterRsTypeMaintenanceId;

    private String workshopOrgCode;

    private Boolean validFlag = true;

    private String deviceType;

    private String createdBy;

    private Date createdAt;

    private String updatedBy;

    private Date updatedAt;
}
