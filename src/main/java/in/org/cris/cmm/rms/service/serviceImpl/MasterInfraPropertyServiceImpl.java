package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.entity.MasterInfraProperty;
import in.org.cris.cmm.rms.repo.MasterInfraPropertyRepository;
import in.org.cris.cmm.rms.service.MasterInfraPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MasterInfraPropertyServiceImpl implements MasterInfraPropertyService {

    private final MasterInfraPropertyRepository repository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public MasterInfraProperty saveOrUpdate(MasterInfraProperty property) {
        String username = authenticationFacade.getLoggedInUser().getUser_name();

        if (property.getPropertyId() == null) {
            // New record
            property.setCreatedBy(username);
            property.setCreatedAt(new Date());
            return repository.save(property);
        } else {
            // Update existing
            Optional<MasterInfraProperty> existingOpt = repository.findById(property.getPropertyId());
            if (existingOpt.isPresent()) {
                MasterInfraProperty existing = existingOpt.get();

                if (property.getPropertyCode() != null) existing.setPropertyCode(property.getPropertyCode());
                if (property.getPropertyName() != null) existing.setPropertyName(property.getPropertyName());
                if (property.getDescription() != null) existing.setDescription(property.getDescription());
                if (property.getUom() != null) existing.setUom(property.getUom());
                if (property.getValueType() != null) existing.setValueType(property.getValueType());
                if (property.getMinValue() != null) existing.setMinValue(property.getMinValue());
                if (property.getMaxValue() != null) existing.setMaxValue(property.getMaxValue());
                if (property.getDefaultValue() != null) existing.setDefaultValue(property.getDefaultValue());
                if (property.getListValues() != null) existing.setListValues(property.getListValues());
                if (property.getParamSeq() != null) existing.setParamSeq(property.getParamSeq());
                if (property.getValidFlag() != null) existing.setValidFlag(property.getValidFlag());

                existing.setUpdatedBy(username);
                existing.setUpdatedAt(new Date());

                return repository.save(existing);
            } else {
                throw new RuntimeException("MasterInfraProperty not found with ID: " + property.getPropertyId());
            }
        }
    }

    @Override
    public MasterInfraProperty getById(Long id) {
        Optional<MasterInfraProperty> optional = repository.findById(id);
        if (optional.isPresent() && Boolean.TRUE.equals(optional.get().getValidFlag())) {
            return optional.get();
        } else {
            throw new RuntimeException("MasterInfraProperty not found with ID: " + id);
        }
    }

    @Override
    public String deleteById(Long id) {
        Optional<MasterInfraProperty> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("MasterInfraProperty not found with ID: " + id);
        }

        MasterInfraProperty property = optional.get();
        property.setValidFlag(false);
        property.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
        property.setUpdatedAt(new Date());
        repository.save(property);

        return "Deleted successfully";
    }
}
