package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.MaintenanceChecklistTxnDTO;
import in.org.cris.cmm.rms.dto.MaintenanceChecklistTxnRequestDTO;
import in.org.cris.cmm.rms.dto.MaintenanceChecklistTxnResponseDTO;
import in.org.cris.cmm.rms.entity.MaintenanceChecklistTxn;

import java.util.List;

public interface MaintenanceChecklistTxnService {

        MaintenanceChecklistTxn saveOrUpdate(MaintenanceChecklistTxnDTO dto);

        String delete(Long id);

        List<MaintenanceChecklistTxn> getAllValidChecklistTxns();

        List<MaintenanceChecklistTxnResponseDTO> saveOrUpdateChecklist(List<MaintenanceChecklistTxnRequestDTO> dtoList);

        List<MaintenanceChecklistTxn> getActiveChecklistTxn(
                Long checklistTxnId,
                Long maintenanceId,
                Long checklistId
        );
}
