package in.org.cris.cmm.rms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceChecklistTxnRequestDTO {

	private Long checklistTxnId;
	private Long maintenanceId;
	private Long checklistId;
	private String preValue;
	private String postValue;
	private String remarks;
}

