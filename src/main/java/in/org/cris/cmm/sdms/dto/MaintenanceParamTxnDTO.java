package in.org.cris.cmm.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceParamTxnDTO {

        private Long paramTxnId;

        private Long maintenanceId;
        private Long parameterId;

        private Double preValue;
        private Double postValue;

        private String remarks;
}