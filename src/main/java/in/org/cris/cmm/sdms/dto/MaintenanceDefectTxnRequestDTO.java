package in.org.cris.cmm.sdms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceDefectTxnRequestDTO {

	private Long defectTxnId;          // optional
	private Long maintenanceId;
	private Long defectId;
	private Long actionId;
	private Long checklistTxnId;

	private Long materialDemandId;
	private String remarks;
	private String status;
	private Boolean validFlag;
}

