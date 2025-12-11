package in.org.cris.cmm.lsin.repo;

import in.org.cris.cmm.lsin.entity.MaintenanceParametersMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceParametersRepository extends JpaRepository<MaintenanceParametersMaster, Long> {

        List<MaintenanceParametersMaster> findByValidFlagTrueOrderByParameterIdAsc();
}
