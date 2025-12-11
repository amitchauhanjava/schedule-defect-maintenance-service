package in.org.cris.cmm.lsin.service;

import in.org.cris.cmm.lsin.dto.MaintenanceChecklistTxnDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceChecklistTxn;

import java.util.List;

public interface MaintenanceChecklistTxnService {

        MaintenanceChecklistTxn saveOrUpdate(MaintenanceChecklistTxnDTO dto);

        String delete(Long id);

        List<MaintenanceChecklistTxn> getAllValidChecklistTxns();
}
