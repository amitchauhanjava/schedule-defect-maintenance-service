package in.org.cris.cmm.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceHaultDTO {

        private Long haultId;

        private Long maintenanceId;

        private String haultStart;
        private String haultResume;

        private String remarks;
}
