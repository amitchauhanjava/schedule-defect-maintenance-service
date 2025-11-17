package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MasterInfraLine;

import java.util.List;

public interface MasterInfraLineService {

    MasterInfraLine saveOrUpdate(MasterInfraLine line);

    ApiResponse<List<MasterInfraLine>> getList();

    ApiResponse<List<MasterInfraLine>> getDetailsById(Long id);

    String deleteById(Long id);
}
