package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.InfraLineDTO;
import in.org.cris.cmm.rms.entity.InfraLine;

import java.util.List;

public interface InfraLineService {

    List<InfraLine> saveOrUpdate(List<InfraLineDTO> infraLines);

    ApiResponse<List<InfraLine>> getList();

    ApiResponse<List<InfraLine>> getDetailsById(Long id);

    String deleteById(Long id);
}
