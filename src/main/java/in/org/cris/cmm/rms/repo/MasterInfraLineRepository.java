package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MasterInfraLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterInfraLineRepository extends JpaRepository<MasterInfraLine, Long> {

    List<MasterInfraLine> findByOrgCodeAndValidFlagTrueOrderByMasterLineIdAsc(String orgCode);

    List<MasterInfraLine> findByMasterLineIdAndValidFlagTrue(Long id);
}
