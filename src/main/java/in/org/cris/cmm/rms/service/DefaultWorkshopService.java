package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.DefaultWorkshopDTO;
import in.org.cris.cmm.rms.entity.DefaultWorkshop;
import java.util.List;

public interface DefaultWorkshopService {
    DefaultWorkshop saveOrUpdate(DefaultWorkshopDTO workshop);
    boolean deleteById(Long id);
    ApiResponse<List<DefaultWorkshop>> getByOrgCode();
    DefaultWorkshop getById(Long id);
}
