package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MaintenanceJobCardDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceJobCard;

import java.util.List;

public interface MaintenanceJobCardService {

        MaintenanceJobCard saveOrUpdate(MaintenanceJobCardDTO dto);

        String delete(Long id);

        List<MaintenanceJobCard> getAllValidJobCards();
}
