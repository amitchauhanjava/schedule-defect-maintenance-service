package in.org.cris.cmm.rms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfraLinePropertiesDTO {

    private Long id;
    private Long lineId;
    private Long propertyId;
    private String propertyValue;
    private String deviceType;
    private Boolean validFlag;
}
