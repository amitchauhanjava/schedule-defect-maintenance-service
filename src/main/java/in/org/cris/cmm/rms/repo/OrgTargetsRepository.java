package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.OrgTargets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrgTargetsRepository extends JpaRepository<OrgTargets, Long> {

    List<OrgTargets> findByOrgCodeAndValidFlagTrueOrderByTargetIdAsc(String orgCode);

    Optional<OrgTargets> findByTargetIdAndValidFlagTrue(Long targetId);
}
