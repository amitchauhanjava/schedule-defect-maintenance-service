package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MasterZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterZoneRepository extends JpaRepository<MasterZone, Long> {

}
