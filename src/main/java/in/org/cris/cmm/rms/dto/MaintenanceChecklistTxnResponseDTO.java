package in.org.cris.cmm.rms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MaintenanceChecklistTxnResponseDTO {

	private Long checklistTxnId;
	private Long maintenanceId;
	private Long checklistId;

	private String preValue;
	private String postValue;
	private String remarks;
	private Boolean validFlag;

	private String createdBy;
	private String updatedBy;
	private Date createdAt;
	private Date updatedAt;
}

