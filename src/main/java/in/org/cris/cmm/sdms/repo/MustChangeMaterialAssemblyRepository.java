package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MustChangeMaterialAssemblyMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MustChangeMaterialAssemblyRepository extends JpaRepository<MustChangeMaterialAssemblyMaster, Long> {

        List<MustChangeMaterialAssemblyMaster> findByValidFlagTrueOrderByMustChangeIdAsc();
}
