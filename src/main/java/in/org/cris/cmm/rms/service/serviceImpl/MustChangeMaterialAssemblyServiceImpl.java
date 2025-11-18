package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.entity.MustChangeMaterialAssemblyMaster;
import in.org.cris.cmm.rms.repo.MustChangeMaterialAssemblyRepository;
import in.org.cris.cmm.rms.service.MustChangeMaterialAssemblyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MustChangeMaterialAssemblyServiceImpl implements MustChangeMaterialAssemblyService {

        private final MustChangeMaterialAssemblyRepository mustChangeMaterialAssemblyRepository;

        @Override
        public List<MustChangeMaterialAssemblyMaster> getAllValidMustChangeMaterials() {
                return mustChangeMaterialAssemblyRepository.findByValidFlagTrueOrderByMustChangeIdAsc();
        }
}
