package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
import in.org.cris.cmm.sdms.dto.MaintenanceHaultDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceDetails;
import in.org.cris.cmm.sdms.entity.MaintenanceHault;
import in.org.cris.cmm.sdms.repo.MaintenanceDetailsRepository;
import in.org.cris.cmm.sdms.repo.MaintenanceHaultRepository;
import in.org.cris.cmm.sdms.service.MaintenanceHaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceHaultServiceImpl implements MaintenanceHaultService {

        private final MaintenanceHaultRepository repository;
        private final MaintenanceDetailsRepository detailsRepository;
        private final AuthenticationFacade authenticationFacade;

        private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        public MaintenanceHault saveOrUpdate(MaintenanceHaultDTO dto) {

                MaintenanceHault entity;

                if (dto.getHaultId() != null) {
                        entity = repository.findById(dto.getHaultId())
                                .orElseThrow(() -> new RuntimeException("Hault not found: " + dto.getHaultId()));

                        entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setUpdatedAt(new Date());

                } else {
                        entity = new MaintenanceHault();
                        entity.setValidFlag(true);
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setCreatedAt(new Date());
                }

                if (dto.getMaintenanceId() != null) {
                        MaintenanceDetails md = detailsRepository.findById(dto.getMaintenanceId())
                                .orElseThrow(() -> new RuntimeException("Maintenance details not found"));
                        entity.setMaintenanceDetails(md);
                }

                try {
                        if (dto.getHaultStart() != null) {
                                entity.setHaultStart(sdf.parse(dto.getHaultStart()));
                        }
                        if (dto.getHaultResume() != null) {
                                entity.setHaultResume(sdf.parse(dto.getHaultResume()));
                        }
                } catch (Exception e) {
                        throw new RuntimeException("Invalid date format.");
                }

                entity.setRemarks(dto.getRemarks());

                return repository.save(entity);
        }

        @Override
        public String delete(Long id) {

                MaintenanceHault entity = repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Hault record not found: " + id));

                entity.setValidFlag(false);
                entity.setUpdatedAt(new Date());
                entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());

                repository.save(entity);

                return "Record deleted successfully";
        }

        @Override
        public List<MaintenanceHault> getAllValidHaults() {
                return repository.findByValidFlagTrueOrderByHaultIdAsc();
        }
}
