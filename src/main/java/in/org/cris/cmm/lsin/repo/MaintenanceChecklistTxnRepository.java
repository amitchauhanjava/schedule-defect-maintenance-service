package in.org.cris.cmm.lsin.repo;

import in.org.cris.cmm.lsin.entity.MaintenanceChecklistTxn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceChecklistTxnRepository extends JpaRepository<MaintenanceChecklistTxn, Long> {

        List<MaintenanceChecklistTxn> findByValidFlagTrueOrderByChecklistTxnIdAsc();
}
