package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.dto.InfraLinePropertiesDTO;
import in.org.cris.cmm.rms.entity.InfraLineProperties;
import java.util.Optional;

public interface InfraLinePropertiesService {

    InfraLineProperties saveOrUpdate(InfraLinePropertiesDTO entity);

    Optional<InfraLineProperties> getById(Long id);

    boolean deleteById(Long id);
}
