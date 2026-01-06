package in.org.cris.cmm.sdms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceActionResponseDTO {

	private Long action_id;
	private String action_code;
	private String action_description;
}