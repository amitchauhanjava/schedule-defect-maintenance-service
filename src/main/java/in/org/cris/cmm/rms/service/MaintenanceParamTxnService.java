package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.MaintenanceParamTxnDTO;
import in.org.cris.cmm.rms.entity.MaintenanceParamTxn;

import java.util.List;

public interface MaintenanceParamTxnService {

        MaintenanceParamTxn saveOrUpdate(MaintenanceParamTxnDTO dto);

        String delete(Long id);

        List<MaintenanceParamTxn> getAllValidParamTxns();
}
