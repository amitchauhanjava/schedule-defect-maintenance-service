package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MaintenanceUnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceUnitTypeRepository extends JpaRepository<MaintenanceUnitType, Long> {

//    @Query("SELECT m FROM MaintenanceUnitType m " +
//            "WHERE m.validFlag = true " +
//            "AND ((?2 IS NOT NULL AND m.id = ?2) " +
//            "OR (?2 IS NULL AND ?1 IS NOT NULL AND m.orgCode = ?1)) " +
//            "ORDER BY m.id ASC")
//    List<MaintenanceUnitType> findByOrgCodeOrId(String orgCode, Long id);


    List<MaintenanceUnitType> findByOrgCodeAndValidFlagTrueOrderByIdAsc(String orgCode);

    List<MaintenanceUnitType> findByIdAndValidFlagTrue(Long id);

}
