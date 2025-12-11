package in.org.cris.cmm.lsin.repo;

import in.org.cris.cmm.lsin.entity.MaintenanceDefectTxn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceDefectTxnRepository extends JpaRepository<MaintenanceDefectTxn, Long> {

        List<MaintenanceDefectTxn> findByValidFlagTrueOrderByDefectTxnIdAsc();
}
