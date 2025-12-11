package in.org.cris.cmm.lsin.service;

import in.org.cris.cmm.lsin.dto.MaintenanceParamTxnDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceParamTxn;

import java.util.List;

public interface MaintenanceParamTxnService {

        MaintenanceParamTxn saveOrUpdate(MaintenanceParamTxnDTO dto);

        String delete(Long id);

        List<MaintenanceParamTxn> getAllValidParamTxns();
}
