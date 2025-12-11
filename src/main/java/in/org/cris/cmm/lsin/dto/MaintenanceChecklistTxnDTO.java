package in.org.cris.cmm.lsin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceChecklistTxnDTO {

        private Long checklistTxnId;

        private Long maintenanceId;
        private Long checklistId;

        private String preValue;
        private String postValue;
        private String remarks;
}
