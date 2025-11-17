package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.MasterRsTypeMaintenanceDTO;
import in.org.cris.cmm.rms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.rms.repo.AssetTypeMasterRepository;
import in.org.cris.cmm.rms.repo.MasterMaintenanceTypeRepository;
import in.org.cris.cmm.rms.repo.MasterRsTypeMaintenanceRepository;
import in.org.cris.cmm.rms.service.MasterRsTypeMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MasterRsTypeMaintenanceServiceImpl implements MasterRsTypeMaintenanceService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private MasterRsTypeMaintenanceRepository repository;

    @Autowired
    private MasterMaintenanceTypeRepository masterMaintenanceTypeRepository;

    @Autowired
    private AssetTypeMasterRepository assetTypeMasterRepository;

    @Override
    public List<MasterRsTypeMaintenance> saveOrUpdate(List<MasterRsTypeMaintenanceDTO> list) {

        String username = authenticationFacade.getLoggedInUser().getUser_name();
        Date now = new Date();
        List<MasterRsTypeMaintenance> savedList = new ArrayList<>();

        for (MasterRsTypeMaintenanceDTO dto : list) {

            if (dto.getRsTypeMaintenanceId() == null) {

                MasterRsTypeMaintenance entity = new MasterRsTypeMaintenance();
                entity.setMaintenanceType(masterMaintenanceTypeRepository.findById(Math.toIntExact(dto.getMaintenanceTypeId())).orElseThrow(() -> new RuntimeException("Invalid Maintenance Type ID")));
                entity.setRsType(assetTypeMasterRepository.findById(dto.getRsTypeId()).orElseThrow(() -> new RuntimeException("Invalid RS Type ID")));

                entity.setFirstFrequencyInDays(dto.getFirstFrequencyInDays());
                entity.setFirstFrequencyInKms(dto.getFirstFrequencyInKms());
                entity.setSubsequentFrequencyInDays(dto.getSubsequentFrequencyInDays());
                entity.setSubsequentFrequencyInKms(dto.getSubsequentFrequencyInKms());
                entity.setDescription(dto.getDescription());
                entity.setValidFlag(true);

                entity.setCreatedBy(username);
                entity.setCreatedAt(now);

                savedList.add(repository.save(entity));
            } else {
                MasterRsTypeMaintenance existing = repository.findById(dto.getRsTypeMaintenanceId()).orElseThrow(() -> new RuntimeException("Record not found: " + dto.getRsTypeMaintenanceId()));

                if (dto.getMaintenanceTypeId() != null) {
                    existing.setMaintenanceType(masterMaintenanceTypeRepository.findById(Math.toIntExact(dto.getMaintenanceTypeId())).orElseThrow(() -> new RuntimeException("Invalid Maintenance Type ID")));
                }

                if (dto.getRsTypeId() != null) {
                    existing.setRsType(assetTypeMasterRepository.findById(dto.getRsTypeId()).orElseThrow(() -> new RuntimeException("Invalid RS Type ID")));
                }

                if (dto.getFirstFrequencyInDays() != null) existing.setFirstFrequencyInDays(dto.getFirstFrequencyInDays());
                if (dto.getFirstFrequencyInKms() != null) existing.setFirstFrequencyInKms(dto.getFirstFrequencyInKms());
                if (dto.getSubsequentFrequencyInDays() != null) existing.setSubsequentFrequencyInDays(dto.getSubsequentFrequencyInDays());
                if (dto.getSubsequentFrequencyInKms() != null) existing.setSubsequentFrequencyInKms(dto.getSubsequentFrequencyInKms());
                if (dto.getDescription() != null) existing.setDescription(dto.getDescription());

                existing.setUpdatedBy(username);
                existing.setUpdatedAt(new Date());

                savedList.add(repository.save(existing));
            }
        }

        return savedList;
    }

    @Override
    @Transactional
    public String deleteById(Long id) {

        String username = authenticationFacade.getLoggedInUser().getUser_name();

        Optional<MasterRsTypeMaintenance> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return "Record not found for id: " + id;
        }

        MasterRsTypeMaintenance existing = optional.get();
        existing.setValidFlag(false);
        existing.setUpdatedBy(username);
        existing.setUpdatedAt(new Date());
        repository.save(existing);

        return "Record deleted successfully for id: " + id;
    }

    @Override
    public ApiResponse<List<MasterRsTypeMaintenance>> getList() {
        List<MasterRsTypeMaintenance> list = repository.findByValidFlagTrue();
        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Records fetched successfully" : "No records found";
        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    public ApiResponse<List<MasterRsTypeMaintenance>> getByMaintenanceTypeId(Long maintenanceTypeId) {
        List<MasterRsTypeMaintenance> list = repository.findByMaintenanceTypeAndValidFlagTrue(maintenanceTypeId);
        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Records fetched successfully" : "No records found";
        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    public ApiResponse<List<MasterRsTypeMaintenance>> getByRsTypeId(Long rsTypeId) {
        List<MasterRsTypeMaintenance> list = repository.findByRsTypeAndValidFlagTrue(rsTypeId);
        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Records fetched successfully" : "No records found";
        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

}
