package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MaintenanceDefectMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceDefectRepository extends JpaRepository<MaintenanceDefectMaster, Long> {

        List<MaintenanceDefectMaster> findByValidFlagTrueOrderByDefectIdAsc();
}
