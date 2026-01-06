package in.org.cris.cmm.sdms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceMaterialsTxnDTO {

	private Long materialTxnId;

	private Long maintenanceId;
	private Long defectTxnId;
	private Long mustChangeId;

	private String materialName;
	private String description;
	private Double quantityUsed;
	private Double materialCost;

	private String user;
}

