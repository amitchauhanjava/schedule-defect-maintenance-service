package in.org.cris.cmm.lsin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceJobCardDTO {

        private Long jobCardId;

        private String orgCode;
        private String jobNo;

        private Long rakeId;

        private Date startTime;
        private Date endTime;

        private String status;
}
