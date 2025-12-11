package in.org.cris.cmm.lsin.service;

import in.org.cris.cmm.lsin.dto.MaintenanceDetailsDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceDetails;

import java.util.List;

public interface MaintenanceDetailsService {

        MaintenanceDetails saveOrUpdate(MaintenanceDetailsDTO dto);

        String delete(Long id);

        List<MaintenanceDetails> getAllValidMaintenanceDetails();
}
