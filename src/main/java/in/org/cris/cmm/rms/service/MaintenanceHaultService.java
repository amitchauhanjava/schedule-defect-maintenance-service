package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.MaintenanceHaultDTO;
import in.org.cris.cmm.rms.entity.MaintenanceHault;

import java.util.List;

public interface MaintenanceHaultService {

        MaintenanceHault saveOrUpdate(MaintenanceHaultDTO dto);

        String delete(Long id);

        List<MaintenanceHault> getAllValidHaults();
}
