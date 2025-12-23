package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.MustChangeMaterialAssemblyDTO;
import in.org.cris.cmm.rms.entity.MustChangeMaterialAssemblyMaster;

import java.util.List;

public interface MustChangeMaterialAssemblyService {

    List<MustChangeMaterialAssemblyMaster> getAllValidMustChangeMaterials(Long rsTypeMaintId);

    MustChangeMaterialAssemblyMaster saveOrUpdate(MustChangeMaterialAssemblyDTO dto);

    String softDelete(Long id);
}
