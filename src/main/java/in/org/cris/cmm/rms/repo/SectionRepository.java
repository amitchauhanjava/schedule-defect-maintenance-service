package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    List<Section> findByOrgCodeAndValidFlagTrueOrderBySectionIdAsc(String orgCode);

    List<Section> findBySectionIdAndValidFlagTrue(Long id);
}
