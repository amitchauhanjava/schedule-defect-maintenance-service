package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MasterInfraProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterInfraPropertyRepository extends JpaRepository<MasterInfraProperty, Long> {

}
