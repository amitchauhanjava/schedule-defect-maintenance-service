package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MustChangeMaterialAssemblyMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MustChangeMaterialAssemblyRepository
        extends JpaRepository<MustChangeMaterialAssemblyMaster, Long> {
        List<MustChangeMaterialAssemblyMaster> findByValidFlagTrueOrderByMustChangeIdAsc();
}
