package in.org.cris.cmm.lsin.service;

import in.org.cris.cmm.lsin.dto.MaintenanceJobCardDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceJobCard;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MaintenanceJobCardService {

        MaintenanceJobCard saveOrUpdate(MaintenanceJobCardDTO dto);

        String delete(Long id);

        List<MaintenanceJobCard> getAllValidJobCards();

        Page<MaintenanceJobCard> getJobCardWithFilters(String loginLevel, String loginCode, String search, int page, int size, String sortBy, String direction);
}
