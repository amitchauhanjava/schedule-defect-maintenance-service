package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.MaintenanceDetailsDTO;
import in.org.cris.cmm.rms.entity.MaintenanceDetails;
import in.org.cris.cmm.rms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.rms.repo.MaintenanceDetailsRepository;
import in.org.cris.cmm.rms.repo.MasterRsTypeMaintenanceRepository;
import in.org.cris.cmm.rms.service.MaintenanceDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceDetailsServiceImpl implements MaintenanceDetailsService {

        private final MaintenanceDetailsRepository repository;
        private final MasterRsTypeMaintenanceRepository rsTypeRepo;
        private final AuthenticationFacade authenticationFacade;

        @Override
        public List<MaintenanceDetails> getAllValidMaintenanceDetails() {
                return repository.findByValidFlagTrueOrderByMaintenanceIdAsc();
        }

        @Override
        public MaintenanceDetails saveOrUpdate(MaintenanceDetailsDTO dto) {

                MaintenanceDetails entity;

                if (dto.getMaintenanceId() != null) {
                        entity = repository.findById(dto.getMaintenanceId())
                                .orElseThrow(() -> new RuntimeException("Maintenance not found: " + dto.getMaintenanceId()));

                        entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setUpdatedAt(new Date());

                } else {
                        entity = new MaintenanceDetails();
                        entity.setValidFlag(true);
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setCreatedAt(new Date());
                }

                if (dto.getAssetId() != null) entity.setAssetId(dto.getAssetId());
                if (dto.getMaintenanceType() != null) entity.setMaintenanceType(dto.getMaintenanceType());
                if (dto.getLocationId() != null) entity.setLocationId(dto.getLocationId());
                if (dto.getWorkOrderNo() != null) entity.setWorkOrderNo(dto.getWorkOrderNo());
                if (dto.getStatus() != null) entity.setStatus(dto.getStatus());
                if (dto.getRemarks() != null) entity.setRemarks(dto.getRemarks());
                if (dto.getStartDate() != null) entity.setStartDate(dto.getStartDate());
                if (dto.getEndDate() != null) entity.setEndDate(dto.getEndDate());

                if (dto.getRsTypeMaintenanceId() != null) {
                        MasterRsTypeMaintenance ref = rsTypeRepo.findById(dto.getRsTypeMaintenanceId()).orElseThrow(() -> new RuntimeException("RS type not found"));
                        entity.setRsTypeMaintenance(ref);
                }

                return repository.save(entity);
        }

        @Override
        public String delete(Long id) {

                MaintenanceDetails entity = repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Maintenance not found: " + id));

                entity.setValidFlag(false);
                entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                entity.setUpdatedAt(new Date());

                repository.save(entity);
                return "Record deleted successfully";
        }
}
