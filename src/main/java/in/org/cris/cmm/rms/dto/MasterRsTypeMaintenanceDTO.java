package in.org.cris.cmm.rms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterRsTypeMaintenanceDTO {

    private Long rsTypeMaintenanceId;
    private Long maintenanceTypeId;
    private Long rsTypeId;

    private Integer firstFrequencyInDays;
    private Integer firstFrequencyInKms;
    private Integer subsequentFrequencyInDays;
    private Integer subsequentFrequencyInKms;
    private String description;
    private Boolean validFlag;
}
