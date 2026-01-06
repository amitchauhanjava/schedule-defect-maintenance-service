package in.org.cris.cmm.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDetailsDTO {

        private Long maintenanceId;
        private Long assetId;
        private Long rsTypeMaintenanceId;

        private String maintenanceType;
        private Date startDate;
        private Date endDate;

        private Long locationId;
        private String workOrderNo;
        private String status;
        private String remarks;
}
