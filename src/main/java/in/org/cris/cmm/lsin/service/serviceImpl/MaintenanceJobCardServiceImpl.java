package in.org.cris.cmm.lsin.service.serviceImpl;

import in.org.cris.cmm.lsin.config.AuthenticationFacade;
import in.org.cris.cmm.lsin.dto.MaintenanceJobCardDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceJobCard;
import in.org.cris.cmm.lsin.repo.MaintenanceJobCardRepository;
import in.org.cris.cmm.lsin.service.MaintenanceJobCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceJobCardServiceImpl implements MaintenanceJobCardService {

        private final MaintenanceJobCardRepository repository;
        private final AuthenticationFacade authenticationFacade;

        @Override
        public MaintenanceJobCard saveOrUpdate(MaintenanceJobCardDTO dto) {

                MaintenanceJobCard entity;

                if (dto.getJobCardId() != null) {
                        entity = repository.findById(dto.getJobCardId())
                                .orElseThrow(() -> new RuntimeException("Job Card not found: " + dto.getJobCardId()));

                        entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setUpdatedAt(new Date());

                } else {
                        entity = new MaintenanceJobCard();
                        entity.setValidFlag(true);
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setCreatedAt(new Date());
                }

                entity.setOrgCode(authenticationFacade.getLoggedInUser().getDepot());
                entity.setJobNo(dto.getJobNo());
                entity.setRakeId(dto.getRakeId());
                entity.setStatus(dto.getStatus());

                try {
                        if (dto.getStartTime() != null) entity.setStartTime(dto.getStartTime());
                        if (dto.getEndTime() != null) entity.setEndTime(dto.getEndTime());
                } catch (Exception e) {
                        throw new RuntimeException("Invalid date format.");
                }

                return repository.save(entity);
        }

        @Override
        public String delete(Long id) {

                MaintenanceJobCard entity = repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Job Card not found: " + id));

                entity.setValidFlag(false);
                entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                entity.setUpdatedAt(new Date());

                repository.save(entity);

                return "Record deleted successfully";
        }

        @Override
        public List<MaintenanceJobCard> getAllValidJobCards() {
                return repository.findByValidFlagTrueOrderByJobCardIdAsc();
        }
}
