package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MasterLineType;

import java.util.List;

public interface MasterLineTypeService {

    ApiResponse<List<MasterLineType>> getList();
}
