package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.Workcenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkcenterRepository extends JpaRepository<Workcenter, Long> {

    List<Workcenter> findByOrgCodeAndValidFlagTrueOrderByWorkcenterIdAsc(String orgCode);

    List<Workcenter> findByWorkcenterIdAndValidFlagTrue(Long id);
}
