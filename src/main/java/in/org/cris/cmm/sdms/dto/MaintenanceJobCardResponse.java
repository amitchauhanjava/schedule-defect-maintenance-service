package in.org.cris.cmm.sdms.dto;

import in.org.cris.cmm.sdms.entity.Rake;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceJobCardResponse {

	private Long jobCardId;
	private String orgCode;
	private String jobNo;
	private Rake rake;
	private Date startTime;
	private Date endTime;
	private String status;
	private Boolean validFlag;
	private String createdBy;
	private String updatedBy;
	private Date createdAt;
	private Date updatedAt;

}
