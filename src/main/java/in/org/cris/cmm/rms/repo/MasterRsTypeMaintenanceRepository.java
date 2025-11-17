package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MasterRsTypeMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterRsTypeMaintenanceRepository extends JpaRepository<MasterRsTypeMaintenance, Long> {

    List<MasterRsTypeMaintenance> findByMaintenanceTypeAndValidFlagTrue(Long maintenanceTypeId);

    List<MasterRsTypeMaintenance> findByRsTypeAndValidFlagTrue(Long rsTypeId);

    List<MasterRsTypeMaintenance> findByValidFlagTrue();

}
