package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceParamTxnDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceParamTxn;

import java.util.List;

public interface MaintenanceParamTxnService {

        MaintenanceParamTxn saveOrUpdate(MaintenanceParamTxnDTO dto);

        String delete(Long id);

        List<MaintenanceParamTxn> getAllValidParamTxns();
}
