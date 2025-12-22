package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.MaintenanceDetailsDTO;
import in.org.cris.cmm.rms.entity.MaintenanceDetails;

import java.util.List;

public interface MaintenanceDetailsService {

        MaintenanceDetails saveOrUpdate(MaintenanceDetailsDTO dto);

        String delete(Long id);

        List<MaintenanceDetails> getAllValidMaintenanceDetails();
}
