package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceHaultDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceHault;

import java.util.List;

public interface MaintenanceHaultService {

        MaintenanceHault saveOrUpdate(MaintenanceHaultDTO dto);

        String delete(Long id);

        List<MaintenanceHault> getAllValidHaults();
}
