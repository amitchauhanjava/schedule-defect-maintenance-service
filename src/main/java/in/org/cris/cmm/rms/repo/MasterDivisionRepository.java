package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MasterDivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterDivisionRepository extends JpaRepository<MasterDivision, Long> {
}
