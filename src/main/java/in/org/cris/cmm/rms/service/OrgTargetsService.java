package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.OrgTargetsDTO;
import in.org.cris.cmm.rms.entity.OrgTargets;

import java.util.List;
import java.util.Optional;

public interface OrgTargetsService {

    List<OrgTargets> saveOrUpdate(List<OrgTargetsDTO> dtoList);

    ApiResponse<List<OrgTargets>> getByOrgCode();

    Optional<OrgTargets> getById(Long targetId);

    String deleteById(Long targetId);
}
