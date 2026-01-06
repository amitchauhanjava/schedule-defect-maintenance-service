package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceDetailsDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceDetails;

import java.util.List;

public interface MaintenanceDetailsService {

        MaintenanceDetails saveOrUpdate(MaintenanceDetailsDTO dto);

        String delete(Long id);

        List<MaintenanceDetails> getAllValidMaintenanceDetails();
}
