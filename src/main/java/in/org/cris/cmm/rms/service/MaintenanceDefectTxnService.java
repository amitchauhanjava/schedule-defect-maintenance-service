package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.MaintenanceDefectTxnDTO;
import in.org.cris.cmm.rms.dto.MaintenanceDefectTxnRequestDTO;
import in.org.cris.cmm.rms.dto.MaintenanceDefectTxnResponseDTO;
import in.org.cris.cmm.rms.entity.MaintenanceDefectTxn;

import java.util.List;

public interface MaintenanceDefectTxnService {

        MaintenanceDefectTxn saveOrUpdate(MaintenanceDefectTxnDTO dto);

        String delete(Long id);

        List<MaintenanceDefectTxn> getAllValidDefectTxns();

        List<MaintenanceDefectTxn> getMaintenanceDefects(Long defectTxnId, Long maintenanceId, Long defectId, Long actionId, Long checklistTxnId);

        List<MaintenanceDefectTxnResponseDTO> saveOrUpdateDefects(List<MaintenanceDefectTxnRequestDTO> dtoList);

}
