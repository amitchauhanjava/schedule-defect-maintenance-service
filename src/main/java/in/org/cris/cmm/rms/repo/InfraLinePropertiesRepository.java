package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.InfraLineProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfraLinePropertiesRepository extends JpaRepository<InfraLineProperties, Long> {

    Optional<InfraLineProperties> findByIdAndValidFlagTrue(Long id);

    List<InfraLineProperties> findByValidFlagTrue();

    List<InfraLineProperties> findByInfraLine_LineIdAndValidFlagTrue(Long lineId);

    boolean existsByInfraLine_LineIdAndMasterInfraProperty_PropertyId(Long lineId, Long propertyId);
}
