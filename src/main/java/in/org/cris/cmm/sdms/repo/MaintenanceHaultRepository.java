package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MaintenanceHault;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceHaultRepository extends JpaRepository<MaintenanceHault, Long> {

        List<MaintenanceHault> findByValidFlagTrueOrderByHaultIdAsc();
}
