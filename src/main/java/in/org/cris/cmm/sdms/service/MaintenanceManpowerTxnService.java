package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceManpowerTxnDTO;

import java.util.List;

public interface MaintenanceManpowerTxnService {

	public List<MaintenanceManpowerTxnDTO> saveOrUpdate(List<MaintenanceManpowerTxnDTO> dtoList);

	void softDelete(Long manpowerTxnId);

	List<MaintenanceManpowerTxnDTO> getActiveList(Long maintenanceId, Long defectTxnId);
}

