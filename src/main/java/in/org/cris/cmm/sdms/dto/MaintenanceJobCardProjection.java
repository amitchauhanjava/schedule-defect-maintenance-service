package in.org.cris.cmm.sdms.dto;

import java.util.Date;

public interface MaintenanceJobCardProjection {
	Long getJobCardId();
	String getOrgCode();
	String getJobNo();
	Long getRakeId();
	Date getStartTime();
	Date getEndTime();
	String getStatus();
	Boolean getValidFlag();
	String getCreatedBy();
	String getUpdatedBy();
	Date getCreatedAt();
	Date getUpdatedAt();
	Long getTotalActivity();
	Long getPendingActivity();
}

