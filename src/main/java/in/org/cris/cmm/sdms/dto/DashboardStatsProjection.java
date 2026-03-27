package in.org.cris.cmm.sdms.dto;

public interface DashboardStatsProjection {
	Long getAssetsDetachedToday();
	Long getSickAssets();
	Long getFailuresToday();
}
