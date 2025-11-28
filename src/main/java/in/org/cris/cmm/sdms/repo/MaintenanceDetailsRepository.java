package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MaintenanceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceDetailsRepository extends JpaRepository<MaintenanceDetails, Long> {

        List<MaintenanceDetails> findByValidFlagTrueOrderByMaintenanceIdAsc();
}
