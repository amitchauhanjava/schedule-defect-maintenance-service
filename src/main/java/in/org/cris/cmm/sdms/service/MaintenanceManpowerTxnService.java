package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceManpowerTxnDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceManpowerTxn;

import java.util.List;

public interface MaintenanceManpowerTxnService {

	public List<MaintenanceManpowerTxn> saveOrUpdate(List<MaintenanceManpowerTxnDTO> dtoList);

	void softDelete(Long manpowerTxnId);

	List<MaintenanceManpowerTxn> getActiveList(Long maintenanceId, Long defectTxnId);
}

