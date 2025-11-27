package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceDefectTxnDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceDefectTxn;

import java.util.List;

public interface MaintenanceDefectTxnService {

        MaintenanceDefectTxn saveOrUpdate(MaintenanceDefectTxnDTO dto);

        String delete(Long id);

        List<MaintenanceDefectTxn> getAllValidDefectTxns();
}
