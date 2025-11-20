package in.org.cris.cmm.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceParametersDTO {

    private Long parameterId;
    private Long rsTypeMaintenanceId;

    private String parameterName;
    private String uom;
    private Double minValue;
    private Double maxValue;

    private Boolean validFlag;
}
