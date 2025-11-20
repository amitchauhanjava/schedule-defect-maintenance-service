package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
import in.org.cris.cmm.sdms.dto.MaintenanceParametersDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceParametersMaster;
import in.org.cris.cmm.sdms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.sdms.repo.MaintenanceParametersRepository;
import in.org.cris.cmm.sdms.repo.MasterRsTypeMaintenanceRepository;
import in.org.cris.cmm.sdms.service.MaintenanceParametersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceParametersServiceImpl implements MaintenanceParametersService {

        private final MaintenanceParametersRepository repository;
        private final AuthenticationFacade authenticationFacade;
        private final MasterRsTypeMaintenanceRepository masterRsTypeMaintenanceRepository;

        @Override
        public List<MaintenanceParametersMaster> getAllValidParameters() {
                return repository.findByValidFlagTrueOrderByParameterIdAsc();
        }

        @Override
        @Transactional
        public MaintenanceParametersMaster saveOrUpdate(MaintenanceParametersDTO dto) {

                String loggedUser = authenticationFacade.getLoggedInUser().getUser_name();
                Date now = new Date();

                MaintenanceParametersMaster entity;

                if (dto.getParameterId() != null) {
                        entity = repository.findById(dto.getParameterId()).orElseThrow(() -> new RuntimeException("Parameter not found: " + dto.getParameterId()));
                        entity.setUpdatedBy(loggedUser);
                        entity.setUpdatedAt(now);
                } else {
                        entity = new MaintenanceParametersMaster();

                        entity.setValidFlag(true);
                        entity.setCreatedBy(loggedUser);
                        entity.setCreatedAt(now);
                }

                if (dto.getRsTypeMaintenanceId() != null) {
                        MasterRsTypeMaintenance ref = masterRsTypeMaintenanceRepository.findById(dto.getRsTypeMaintenanceId()).orElseThrow(() -> new RuntimeException("RS Type not found"));
                        entity.setRsTypeMaintenance(ref);
                }

                if (dto.getParameterName() != null) entity.setParameterName(dto.getParameterName());
                if (dto.getUom() != null) entity.setUom(dto.getUom());
                if (dto.getMinValue() != null) entity.setMinValue(dto.getMinValue());
                if (dto.getMaxValue() != null) entity.setMaxValue(dto.getMaxValue());

                return repository.save(entity);
        }

        @Override
        @Transactional
        public String softDelete(Long parameterId) {

                MaintenanceParametersMaster entity = repository.findById(parameterId)
                        .orElseThrow(() -> new RuntimeException("Parameter not found: " + parameterId));

                entity.setValidFlag(false);
                entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                entity.setUpdatedAt(new Date());

                repository.save(entity);

                return "Parameter soft-deleted successfully";
        }
}
