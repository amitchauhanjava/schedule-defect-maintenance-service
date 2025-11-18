package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MaintenanceDefectMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceDefectRepository extends JpaRepository<MaintenanceDefectMaster, Long> {
        List<MaintenanceDefectMaster> findByValidFlagTrueOrderByDefectIdAsc();
}
