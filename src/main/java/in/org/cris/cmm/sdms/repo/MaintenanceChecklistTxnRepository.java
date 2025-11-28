package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MaintenanceChecklistTxn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceChecklistTxnRepository extends JpaRepository<MaintenanceChecklistTxn, Long> {

        List<MaintenanceChecklistTxn> findByValidFlagTrueOrderByChecklistTxnIdAsc();
}
