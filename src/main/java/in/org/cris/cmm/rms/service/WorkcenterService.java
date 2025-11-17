package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.WorkcenterDTO;
import in.org.cris.cmm.rms.entity.Workcenter;

import java.util.List;

public interface WorkcenterService {

    Workcenter saveOrUpdate(WorkcenterDTO dto);
    ApiResponse<List<Workcenter>> getDetailsByOrgCode();

    ApiResponse<List<Workcenter>> getDetailsById(Long id);

    boolean deleteById(Long id);
}
