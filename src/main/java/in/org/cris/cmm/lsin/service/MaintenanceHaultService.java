package in.org.cris.cmm.lsin.service;

import in.org.cris.cmm.lsin.dto.MaintenanceHaultDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceHault;

import java.util.List;

public interface MaintenanceHaultService {

        MaintenanceHault saveOrUpdate(MaintenanceHaultDTO dto);

        String delete(Long id);

        List<MaintenanceHault> getAllValidHaults();
}
