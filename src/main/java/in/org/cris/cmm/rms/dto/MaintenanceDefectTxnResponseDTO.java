package in.org.cris.cmm.rms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MaintenanceDefectTxnResponseDTO {

	private Long defectTxnId;
	private Long maintenanceId;
	private Long defectId;
	private Long actionId;
	private Long checklistTxnId;

	private Long materialDemandId;
	private String remarks;
	private String status;
	private Boolean validFlag;

	private String createdBy;
	private String updatedBy;
	private Date createdAt;
	private Date updatedAt;
}

