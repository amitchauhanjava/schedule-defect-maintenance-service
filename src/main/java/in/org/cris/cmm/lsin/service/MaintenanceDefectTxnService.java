package in.org.cris.cmm.lsin.service;

import in.org.cris.cmm.lsin.dto.MaintenanceDefectTxnDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceDefectTxn;

import java.util.List;

public interface MaintenanceDefectTxnService {

        MaintenanceDefectTxn saveOrUpdate(MaintenanceDefectTxnDTO dto);

        String delete(Long id);

        List<MaintenanceDefectTxn> getAllValidDefectTxns();
}
