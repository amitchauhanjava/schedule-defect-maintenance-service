package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.dto.MustChangeMaterialAssemblyDTO;
import in.org.cris.cmm.sdms.entity.MustChangeMaterialAssemblyMaster;

import java.util.List;

public interface MustChangeMaterialAssemblyService {

    List<MustChangeMaterialAssemblyMaster> getAllValidMustChangeMaterials();

    MustChangeMaterialAssemblyMaster saveOrUpdate(MustChangeMaterialAssemblyDTO dto);

    String softDelete(Long id);
}
