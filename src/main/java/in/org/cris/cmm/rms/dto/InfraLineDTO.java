package in.org.cris.cmm.rms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfraLineDTO {

    private Long lineId;
    private Long lineTypeId;
    private Long masterLineId;
    private Long fromLengthMeters;
    private Long toLengthMeters;
    private Boolean validFlag;
    private String deviceType;
}

