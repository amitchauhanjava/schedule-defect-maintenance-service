package in.org.cris.cmm.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDetailsDTO {

        private Long maintenanceId;
        private Long assetId;
        private Long rsTypeMaintenanceId;

        private String maintenanceType;
        private String startDate;
        private String endDate;

        private Long locationId;
        private String workOrderNo;
        private String status;
        private String remarks;
}
