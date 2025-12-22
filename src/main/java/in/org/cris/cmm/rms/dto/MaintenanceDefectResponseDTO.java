package in.org.cris.cmm.rms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceDefectResponseDTO {

	private Long defect_id;
	private String defect_code;
	private String defect_description;
	private String severity;
}
