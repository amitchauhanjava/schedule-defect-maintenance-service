package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
import in.org.cris.cmm.sdms.dto.MaintenanceJobCardDTO;
import in.org.cris.cmm.sdms.dto.MaintenanceJobCardDashboardDTO;
import in.org.cris.cmm.sdms.dto.MaintenanceJobCardProjection;
import in.org.cris.cmm.sdms.entity.MaintenanceJobCard;
import in.org.cris.cmm.sdms.entity.Rake;
import in.org.cris.cmm.sdms.repo.JobCardActivityRepository;
import in.org.cris.cmm.sdms.repo.MaintenanceJobCardRepository;
import in.org.cris.cmm.sdms.repo.RakeRepository;
import in.org.cris.cmm.sdms.service.MaintenanceJobCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaintenanceJobCardServiceImpl implements MaintenanceJobCardService {

        private final MaintenanceJobCardRepository repository;
        private final AuthenticationFacade authenticationFacade;
        private final RakeRepository rakeRepository;
        private final JobCardActivityRepository jobCardActivityRepository;

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

                if (dto.getRakeId() != null) {
                        Rake rake = new Rake();
                        rake.setId(dto.getRakeId());
                        entity.setRake(rake);
                }

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

                List<MaintenanceJobCard> response = repository.findByStartDateRange(orgCode, startDate, endDate);

                for (MaintenanceJobCard jobCard : response) {

                        Long total = jobCardActivityRepository.countByJobCard_JobCardId(jobCard.getJobCardId());
                        Long pending = jobCardActivityRepository.countByJobCard_JobCardIdAndStatus(jobCard.getJobCardId(),"PENDING");

                        jobCard.setTotalActivity(total != null ? total : 0L);
                        jobCard.setPendingActivity(pending != null ? pending : 0L);

                        List<Long> sectionIds = jobCardActivityRepository.findDistinctSectionIdsByJobCardId(jobCard.getJobCardId());
                        jobCard.setSection(sectionIds);
                }

                return response;
        }

       /* @Override
        public Page<MaintenanceJobCardDTO> getJobCardWithFilters(String loginLevel, String loginCode, String search, int page, int size, String sortBy, String direction) {
                Sort sort = direction.equalsIgnoreCase("asc")
                        ? Sort.by(sortBy).ascending()
                        : Sort.by(sortBy).descending();

                Pageable pageable = PageRequest.of(page, size, sort);

                if (search == null || search.trim().isEmpty()) {
                        search = "";
                }

                return repository.findJobCardWithFilters(loginLevel, loginCode, search, pageable);
        }*/

        @Override
        public Page<MaintenanceJobCardDashboardDTO> getJobCardWithFilters(
                String loginLevel, String loginCode, String search,
                int page, int size, String sortBy, String direction) {

                Sort sort = direction.equalsIgnoreCase("asc")
                        ? Sort.by(sortBy).ascending()
                        : Sort.by(sortBy).descending();
                Pageable pageable = PageRequest.of(page, size, sort);

                if (search == null || search.trim().isEmpty()) search = "";

                Page<MaintenanceJobCardProjection> projections = repository.findJobCardWithFilters(
                        loginLevel, loginCode, search, pageable);

                List<Long> rakeIds = projections.stream()
                        .map(MaintenanceJobCardProjection::getRakeId)
                        .filter(id -> id != null)
                        .distinct()
                        .toList();

                final Map<Long, Rake> rakeMap = !rakeIds.isEmpty()
                        ? rakeRepository.findAllById(rakeIds)
                        .stream()
                        .collect(Collectors.toMap(Rake::getId, r -> r))
                        : Collections.emptyMap();  // effectively final

                // 5️⃣ Map projections to DTO
                List<MaintenanceJobCardDashboardDTO> dtos = projections.stream().map(p -> {
                        MaintenanceJobCardDashboardDTO dto = new MaintenanceJobCardDashboardDTO();
                        dto.setJobCardId(p.getJobCardId());
                        dto.setOrgCode(p.getOrgCode());
                        dto.setJobNo(p.getJobNo());
                        dto.setRake(p.getRakeId() != null ? rakeMap.get(p.getRakeId()) : null); // nested Rake
                        dto.setStartTime(p.getStartTime());
                        dto.setEndTime(p.getEndTime());
                        dto.setStatus(p.getStatus());
                        dto.setValidFlag(p.getValidFlag());
                        dto.setCreatedBy(p.getCreatedBy());
                        dto.setUpdatedBy(p.getUpdatedBy());
                        dto.setCreatedAt(p.getCreatedAt());
                        dto.setUpdatedAt(p.getUpdatedAt());
                        dto.setTotalActivity(p.getTotalActivity());
                        dto.setPendingActivity(p.getPendingActivity());
                        return dto;
                }).toList();

                return new PageImpl<>(dtos, pageable, projections.getTotalElements());
        }






}
