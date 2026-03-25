package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
import in.org.cris.cmm.sdms.dto.MaintenanceDetailsDTO;
import in.org.cris.cmm.sdms.dto.MaintenanceProjection;
import in.org.cris.cmm.sdms.entity.MaintenanceDetails;
import in.org.cris.cmm.sdms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.sdms.entity.RakeExamConsist;
import in.org.cris.cmm.sdms.repo.MaintenanceDetailsRepository;
import in.org.cris.cmm.sdms.repo.MasterRsTypeMaintenanceRepository;
import in.org.cris.cmm.sdms.repo.RakeExamConsistRepository;
import in.org.cris.cmm.sdms.service.MaintenanceDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceDetailsServiceImpl implements MaintenanceDetailsService {

        private final MaintenanceDetailsRepository repository;
        private final MasterRsTypeMaintenanceRepository rsTypeRepo;
        private final AuthenticationFacade authenticationFacade;
        private final RakeExamConsistRepository rakeExamConsistRepo;

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

       /* @Override
        public String save(List<MaintenanceDetailsDTO> dtos) {

                List<MaintenanceDetails> list = new ArrayList<>();

                for (MaintenanceDetailsDTO dto : dtos) {

                        MaintenanceDetails entity = new MaintenanceDetails();

                        if (dto.getAssetId() != null) entity.setAssetId(dto.getAssetId());
                        if (dto.getMaintenanceType() != null) entity.setMaintenanceType(dto.getMaintenanceType());
                        if (dto.getLocationId() != null) entity.setLocationId(dto.getLocationId());
                        if (dto.getStatus() != null) entity.setStatus(dto.getStatus());
                        if (dto.getRemarks() != null) entity.setRemarks(dto.getRemarks());
                        if (dto.getStartDate() != null) entity.setStartDate(dto.getStartDate());
                        if (dto.getEndDate() != null) entity.setEndDate(dto.getEndDate());
                        if (dto.getRsTypeMaintenanceId() != null) {
                                MasterRsTypeMaintenance ref = rsTypeRepo.findById(dto.getRsTypeMaintenanceId())
                                        .orElseThrow(() -> new RuntimeException("RS type not found"));
                                entity.setRsTypeMaintenance(ref);
                        }

                        entity.setValidFlag(true);
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setCreatedAt(new Date());

                        list.add(entity);
                }

                repository.saveAll(list);

                return "Records Save Successfully.";
        }*/

        @Override
        public String save(List<MaintenanceDetailsDTO> dtos) {

                List<MaintenanceDetails> list = new ArrayList<>();

                for (MaintenanceDetailsDTO dto : dtos) {

                        MaintenanceDetails entity = new MaintenanceDetails();

                        if (dto.getAssetId() != null) entity.setAssetId(dto.getAssetId());
                        if (dto.getMaintenanceType() != null) entity.setMaintenanceType(dto.getMaintenanceType());
                        if (dto.getLocationId() != null) entity.setLocationId(dto.getLocationId());
                        if (dto.getStatus() != null) entity.setStatus(dto.getStatus());
                        if (dto.getRemarks() != null) entity.setRemarks(dto.getRemarks());
                        if (dto.getStartDate() != null) entity.setStartDate(dto.getStartDate());
                        if (dto.getEndDate() != null) entity.setEndDate(dto.getEndDate());

                        if (dto.getRsTypeMaintenanceId() != null) {
                                MasterRsTypeMaintenance ref = rsTypeRepo.findById(dto.getRsTypeMaintenanceId())
                                        .orElseThrow(() -> new RuntimeException("RS type not found"));
                                entity.setRsTypeMaintenance(ref);
                        }

                        entity.setValidFlag(true);
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setCreatedAt(new Date());

                        list.add(entity);
                }

                // Save Maintenance
                List<MaintenanceDetails> savedList = repository.saveAll(list);

                for (int i = 0; i < savedList.size(); i++) {

                        MaintenanceDetails saved = savedList.get(i);
                        MaintenanceDetailsDTO dto = dtos.get(i);

                        if (dto.getExamConsistId() != null) {

                                RakeExamConsist consist = rakeExamConsistRepo.findById(dto.getExamConsistId()).orElseThrow(() -> new RuntimeException("Exam Consist not found"));

                                if (dto.getStatus() != null) {
                                        consist.setConditionStatus("In Progress");
                                }

                                consist.setMaintenanceId(saved.getMaintenanceId());

                                if (dto.getStartDate() != null) {
                                        consist.setRsTypeMaintenanceDate(dto.getStartDate());
                                }

                                if (dto.getRsTypeMaintenanceId() != null) {
                                        consist.setRsTypeMaintenanceId(dto.getRsTypeMaintenanceId());
                                }

                                consist.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                                consist.setUpdatedAt(new Date());

                                rakeExamConsistRepo.save(consist);
                        }
                }

                return "Records Save Successfully.";
        }

        @Override
        public List<MaintenanceProjection> getMaintenanceData() {

                String depot = authenticationFacade.getLoggedInUser().getDepot();
                return repository.getMaintenanceData(depot);
        }
}
