package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
import in.org.cris.cmm.sdms.dto.MaintenanceDetailsDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceDetails;
import in.org.cris.cmm.sdms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.sdms.repo.MaintenanceDetailsRepository;
import in.org.cris.cmm.sdms.repo.MasterRsTypeMaintenanceRepository;
import in.org.cris.cmm.sdms.service.MaintenanceDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceDetailsServiceImpl implements MaintenanceDetailsService {

        private final MaintenanceDetailsRepository repository;
        private final MasterRsTypeMaintenanceRepository rsTypeRepo;
        private final AuthenticationFacade authenticationFacade;

        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

                // Basic fields
                if (dto.getAssetId() != null) entity.setAssetId(dto.getAssetId());
                if (dto.getMaintenanceType() != null) entity.setMaintenanceType(dto.getMaintenanceType());
                if (dto.getLocationId() != null) entity.setLocationId(dto.getLocationId());
                if (dto.getWorkOrderNo() != null) entity.setWorkOrderNo(dto.getWorkOrderNo());
                if (dto.getStatus() != null) entity.setStatus(dto.getStatus());
                if (dto.getRemarks() != null) entity.setRemarks(dto.getRemarks());

                // Start and End Dates
                try {
                        if (dto.getStartDate() != null) {
                                entity.setStartDate(sdf.parse(dto.getStartDate()));
                        }
                        if (dto.getEndDate() != null) {
                                entity.setEndDate(sdf.parse(dto.getEndDate()));
                        }
                } catch (Exception e) {
                        throw new RuntimeException("Invalid date format.");
                }

                if (dto.getRsTypeMaintenanceId() != null) {
                        MasterRsTypeMaintenance ref = rsTypeRepo.findById(dto.getRsTypeMaintenanceId())
                                .orElseThrow(() -> new RuntimeException("RS type not found"));
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
