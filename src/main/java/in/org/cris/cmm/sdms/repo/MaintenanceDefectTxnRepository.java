package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MaintenanceDefectTxn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceDefectTxnRepository extends JpaRepository<MaintenanceDefectTxn, Long> {

        List<MaintenanceDefectTxn> findByValidFlagTrueOrderByDefectTxnIdAsc();
}
