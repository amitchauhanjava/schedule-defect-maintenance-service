package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceChecklistTxnDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceChecklistTxn;

import java.util.List;

public interface MaintenanceChecklistTxnService {

        MaintenanceChecklistTxn saveOrUpdate(MaintenanceChecklistTxnDTO dto);

        String delete(Long id);

        List<MaintenanceChecklistTxn> getAllValidChecklistTxns();
}
