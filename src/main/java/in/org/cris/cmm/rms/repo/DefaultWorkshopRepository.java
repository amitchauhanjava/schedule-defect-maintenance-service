package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.DefaultWorkshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefaultWorkshopRepository extends JpaRepository<DefaultWorkshop, Long> {

    List<DefaultWorkshop> findByOrgCodeAndValidFlagTrue(String orgCode);

    DefaultWorkshop findByDefaultWorkshopIdAndValidFlagTrue(Long defaultWorkshopId);
}
