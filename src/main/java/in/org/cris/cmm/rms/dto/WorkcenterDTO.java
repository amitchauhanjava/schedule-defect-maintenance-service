package in.org.cris.cmm.rms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WorkcenterDTO {

    private Long workcenterId;
    private String workcenterCode;
    private String workcenterName;
    private String orgCode;
    private Long sectionId;
    private String description;
    private Date commissioningDate;
    private String remarks;
    private Boolean validFlag;
    private String createdBy;
    private String updatedBy;
    private Date createdAt;
    private Date updatedAt;
    private String deviceType;
}
