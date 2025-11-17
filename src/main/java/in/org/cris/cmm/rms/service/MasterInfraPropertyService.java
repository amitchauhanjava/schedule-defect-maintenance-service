package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.entity.MasterInfraProperty;

public interface MasterInfraPropertyService {

    MasterInfraProperty saveOrUpdate(MasterInfraProperty property);

    MasterInfraProperty getById(Long id);

    String deleteById(Long id);
}
