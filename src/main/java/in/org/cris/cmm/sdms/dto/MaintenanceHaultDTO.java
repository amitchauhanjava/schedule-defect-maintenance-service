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
public class MaintenanceHaultDTO {

        private Long haultId;

        private Long maintenanceId;

        private Date haultStart;
        private Date haultResume;

        private String remarks;
}
