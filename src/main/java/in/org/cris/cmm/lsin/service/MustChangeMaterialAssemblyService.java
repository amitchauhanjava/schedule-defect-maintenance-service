package in.org.cris.cmm.lsin.service;

import in.org.cris.cmm.lsin.dto.MustChangeMaterialAssemblyDTO;
import in.org.cris.cmm.lsin.entity.MustChangeMaterialAssemblyMaster;

import java.util.List;

public interface MustChangeMaterialAssemblyService {

    List<MustChangeMaterialAssemblyMaster> getAllValidMustChangeMaterials();

    MustChangeMaterialAssemblyMaster saveOrUpdate(MustChangeMaterialAssemblyDTO dto);

    String softDelete(Long id);
}
