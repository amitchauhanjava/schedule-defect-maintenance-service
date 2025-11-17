package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.Section;

import java.util.List;

public interface SectionService {

    Section saveOrUpdate(Section section);
    ApiResponse<List<Section>> getByOrgCode(String orgCode);

    ApiResponse<List<Section>> getById(Long id);

    String deleteById(Long id);
}
