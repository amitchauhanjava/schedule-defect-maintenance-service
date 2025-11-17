package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.InfraLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfraLineRepository extends JpaRepository<InfraLine, Long> {

    @Query("SELECT il FROM InfraLine il " +
            "JOIN il.masterInfraLine ml " +
            "WHERE ml.orgCode = :orgCode AND il.validFlag = true " +
            "ORDER BY il.lineId ASC")
    List<InfraLine> findByOrgCodeAndValidFlagTrue(@Param("orgCode") String orgCode);

    List<InfraLine> findByLineIdAndValidFlagTrue(Long id);

    List<InfraLine> findByValidFlagTrue();
}
