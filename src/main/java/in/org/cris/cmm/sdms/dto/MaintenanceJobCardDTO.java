package in.org.cris.cmm.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceJobCardDTO {

        private Long jobCardId;

        private String orgCode;
        private String jobNo;

        private Long rakeId;

        private String startTime;
        private String endTime;

        private String status;
}
