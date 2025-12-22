package in.org.cris.cmm.rms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDefectTxnDTO {

        private Long defectTxnId;

        private Long maintenanceId;
        private Long defectId;
        private Long actionId;
        private Long checklistTxnId;

        private Long materialDemandId;

        private String remarks;
        private String status;
}
