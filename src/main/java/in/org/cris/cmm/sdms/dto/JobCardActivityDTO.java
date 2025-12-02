package in.org.cris.cmm.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobCardActivityDTO {

        private Long activityId;

        private Long jobCardId;
        private String activityType;

        private Long maintenanceId;
        private Long taskMId;
        private Long sectionId;
        private Long assetDk;

        private String assemblyNumber;
        private String activityDesc;
        private String assignedTo;

        private String startTime;
        private String endTime;

        private String status;
        private String completionRemarks;
}
