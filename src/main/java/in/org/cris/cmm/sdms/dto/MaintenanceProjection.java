package in.org.cris.cmm.sdms.dto;

import java.util.Date;

public interface MaintenanceProjection {

	Long getMaintenanceId();
	String getAssetNo();
	String getMaintenanceType();
	Date getStartDate();
}