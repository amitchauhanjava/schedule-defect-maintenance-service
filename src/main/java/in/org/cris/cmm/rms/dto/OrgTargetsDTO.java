package in.org.cris.cmm.rms.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrgTargetsDTO {
    private Long targetId;
    private String targetType;
    private Long rsTypeMaintenanceId;
    private BigDecimal targetValue;
    private String targetMonth;
    private Integer targetYear;
    private BigDecimal achievedValue;
    private String remarks;
    private Boolean validFlag;
    private String deviceType;
}
