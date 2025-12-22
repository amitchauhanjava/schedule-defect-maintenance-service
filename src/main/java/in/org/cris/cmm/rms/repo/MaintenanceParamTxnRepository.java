package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MaintenanceParamTxn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceParamTxnRepository extends JpaRepository<MaintenanceParamTxn, Long> {

        List<MaintenanceParamTxn> findByValidFlagTrueOrderByParamTxnIdAsc();
}
