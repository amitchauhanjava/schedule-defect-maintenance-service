package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MasterMaintenanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterMaintenanceTypeRepository extends JpaRepository<MasterMaintenanceType, Integer> {

    List<MasterMaintenanceType> findByValidFlagTrueOrderByMaintenanceTypeIdAsc();
}
