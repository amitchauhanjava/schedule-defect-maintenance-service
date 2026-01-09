package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
import in.org.cris.cmm.sdms.dto.MaintenanceJobCardDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceJobCard;
import in.org.cris.cmm.sdms.repo.MaintenanceJobCardRepository;
import in.org.cris.cmm.sdms.service.MaintenanceJobCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

                String depot = authenticationFacade.getLoggedInUser().getDepot();

                MaintenanceJobCard entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Job Card not found: " + id));

                entity.setValidFlag(false);
                entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                entity.setUpdatedAt(new Date());

                repository.save(entity);

                return "Record deleted successfully";
        }

        @Override
        public List<MaintenanceJobCard> getAllValidJobCards(String fromDate, String toDate) {

                String orgCode = authenticationFacade.getLoggedInUser().getDepot();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);

                Date startDate;
                Date endDate;

                try {
                        if (fromDate != null && toDate != null) {

                                startDate = sdf.parse(fromDate);
                                endDate = sdf.parse(toDate);

                                // set end date to end of day
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(endDate);
                                cal.set(Calendar.HOUR_OF_DAY, 23);
                                cal.set(Calendar.MINUTE, 59);
                                cal.set(Calendar.SECOND, 59);
                                endDate = cal.getTime();

                        } else {
                                // Default: last 1 month
                                Calendar cal = Calendar.getInstance();
                                endDate = new Date();
                                cal.setTime(endDate);
                                cal.add(Calendar.MONTH, -1);
                                startDate = cal.getTime();
                        }

                } catch (ParseException e) {
                        throw new IllegalArgumentException("Invalid date format. Expected dd/MM/yyyy");
                }

                return repository.findByStartDateRange(orgCode, startDate, endDate);
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
