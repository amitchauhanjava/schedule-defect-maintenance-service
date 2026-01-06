package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceMaterialsTxnDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceMaterialsTxn;

import java.util.List;

public interface MaintenanceMaterialsTxnService {

	List<MaintenanceMaterialsTxn> saveOrUpdate(List<MaintenanceMaterialsTxnDTO> dtoList);

	void delete(Long materialTxnId);

	List<MaintenanceMaterialsTxn> getActiveList(Long maintenanceId, Long defectTxnId);
}

