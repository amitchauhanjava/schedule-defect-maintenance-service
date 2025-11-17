package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.InfraLinePropertiesDTO;
import in.org.cris.cmm.rms.entity.InfraLineProperties;
import in.org.cris.cmm.rms.repo.InfraLinePropertiesRepository;
import in.org.cris.cmm.rms.repo.InfraLineRepository;
import in.org.cris.cmm.rms.repo.MasterInfraPropertyRepository;
import in.org.cris.cmm.rms.service.InfraLinePropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class InfraLinePropertiesServiceImpl implements InfraLinePropertiesService {

    @Autowired
    private InfraLinePropertiesRepository repository;

    @Autowired
    private InfraLineRepository infraLineRepository;

    @Autowired
    private MasterInfraPropertyRepository masterInfraPropertyRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public InfraLineProperties saveOrUpdate(InfraLinePropertiesDTO dto) {

        String username = authenticationFacade.getLoggedInUser().getUser_name();
        Date now = new Date();

        InfraLineProperties entity;

        if (dto.getId() == null) {

            if (repository.existsByInfraLine_LineIdAndMasterInfraProperty_PropertyId(dto.getLineId(), dto.getPropertyId())) {
                throw new RuntimeException("This property already exists for line");
            }

            entity = new InfraLineProperties();
            entity.setInfraLine(infraLineRepository.findById(dto.getLineId()).orElseThrow(() -> new RuntimeException("Invalid Line ID")));

            entity.setMasterInfraProperty(masterInfraPropertyRepository.findById(dto.getPropertyId()).orElseThrow(() -> new RuntimeException("Invalid Property ID")));

            entity.setPropertyValue(dto.getPropertyValue());
            entity.setDeviceType(dto.getDeviceType());
            entity.setValidFlag(true);
            entity.setCreatedBy(username);
            entity.setCreatedAt(now);

            return repository.save(entity);
        } else {
            entity = repository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Record not found " + dto.getId()));

            if (dto.getLineId() != null) {
                entity.setInfraLine(infraLineRepository.findById(dto.getLineId()).orElseThrow(() -> new RuntimeException("Invalid Line ID")));
            }

            if (dto.getPropertyId() != null) {
                entity.setMasterInfraProperty(masterInfraPropertyRepository.findById(dto.getPropertyId()).orElseThrow(() -> new RuntimeException("Invalid Property ID")));
            }

            if (dto.getPropertyValue() != null) entity.setPropertyValue(dto.getPropertyValue());
            if (dto.getDeviceType() != null) entity.setDeviceType(dto.getDeviceType());

            entity.setUpdatedBy(username);
            entity.setUpdatedAt(new Date());

            return repository.save(entity);
        }
    }

    @Override
    public Optional<InfraLineProperties> getById(Long id) {
        return repository.findByIdAndValidFlagTrue(id);
    }


    @Override
    public boolean deleteById(Long id) {
        Optional<InfraLineProperties> existingOpt = repository.findById(id);
        if (existingOpt.isPresent()) {
            InfraLineProperties entity = existingOpt.get();
            entity.setValidFlag(false);
            entity.setUpdatedAt(new Date());
            repository.save(entity);
            return true;
        }
        return false;
    }
}
