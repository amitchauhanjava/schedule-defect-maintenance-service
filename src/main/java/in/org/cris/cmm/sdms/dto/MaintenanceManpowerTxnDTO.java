package in.org.cris.cmm.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceManpowerTxnDTO {

	private Long manpowerTxnId;

	private Long maintenanceId;
	private Long defectTxnId;

	private String category;
	private String manpowerName;
	private String designation;
	private String hoursSpent;
	private Double manpowerCost;

}

