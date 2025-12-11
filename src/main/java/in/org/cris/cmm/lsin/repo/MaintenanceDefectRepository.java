package in.org.cris.cmm.lsin.repo;

import in.org.cris.cmm.lsin.entity.MaintenanceDefectMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceDefectRepository extends JpaRepository<MaintenanceDefectMaster, Long> {

        List<MaintenanceDefectMaster> findByValidFlagTrueOrderByDefectIdAsc();
}
