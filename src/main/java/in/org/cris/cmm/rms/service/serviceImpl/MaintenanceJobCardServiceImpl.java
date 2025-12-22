package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.MaintenanceJobCardDTO;
import in.org.cris.cmm.rms.entity.MaintenanceJobCard;
import in.org.cris.cmm.rms.repo.MaintenanceJobCardRepository;
import in.org.cris.cmm.rms.service.MaintenanceJobCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        @Override
        public Page<MaintenanceJobCard> getJobCardWithFilters(String loginLevel, String loginCode, String search, int page, int size, String sortBy, String direction) {
                Sort sort = direction.equalsIgnoreCase("asc")
                        ? Sort.by(sortBy).ascending()
                        : Sort.by(sortBy).descending();

                Pageable pageable = PageRequest.of(page, size, sort);

                if (search == null || search.trim().isEmpty()) {
                        search = "";
                }

                return repository.findJobCardWithFilters(loginLevel, loginCode, search, pageable);
        }
}
