package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.OrgTargetsDTO;
import in.org.cris.cmm.rms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.rms.entity.OrgTargets;
import in.org.cris.cmm.rms.repo.MasterRsTypeMaintenanceRepository;
import in.org.cris.cmm.rms.repo.OrgTargetsRepository;
import in.org.cris.cmm.rms.service.OrgTargetsService;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class OrgTargetsServiceImpl implements OrgTargetsService {

    @Autowired
    private OrgTargetsRepository repository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private MasterRsTypeMaintenanceRepository masterRepo;

    @Override
    public List<OrgTargets> saveOrUpdate(List<OrgTargetsDTO> dtoList) {
        String username = authenticationFacade.getLoggedInUser().getUser_name();
        String orgCode = authenticationFacade.getLoggedInUser().getDepot();

        List<OrgTargets> savedList = new ArrayList<>();

        for (OrgTargetsDTO dto : dtoList) {
            OrgTargets target;

            if (dto.getTargetId() == null) {

                target = new OrgTargets();
                target.setOrgCode(orgCode);
                target.setCreatedBy(username);
                target.setValidFlag(true);
                target.setCreatedAt(new Date());
            } else {
                target = repository.findById(dto.getTargetId()).orElseThrow(() -> new RuntimeException("OrgTarget not found with ID: " + dto.getTargetId()));
            }

            // Map fields from DTO
            if (dto.getTargetType() != null) target.setTargetType(dto.getTargetType());
            if (dto.getTargetValue() != null) target.setTargetValue(dto.getTargetValue());
            if (dto.getTargetMonth() != null) target.setTargetMonth(dto.getTargetMonth());
            if (dto.getTargetYear() != null) target.setTargetYear(dto.getTargetYear());
            if (dto.getAchievedValue() != null) target.setAchievedValue(dto.getAchievedValue());
            if (dto.getRemarks() != null) target.setRemarks(dto.getRemarks());
            if (dto.getDeviceType() != null) target.setDeviceType(dto.getDeviceType());

            if (dto.getRsTypeMaintenanceId() != null) {
                MasterRsTypeMaintenance master = masterRepo.findById(dto.getRsTypeMaintenanceId()).orElseThrow(() -> new RuntimeException("Invalid RS Type Maintenance ID: " + dto.getRsTypeMaintenanceId()));
                target.setMasterRsTypeMaintenance(master);
            }

            target.setUpdatedBy(username);
            target.setUpdatedAt(new Date());
            savedList.add(repository.save(target));
        }
        return savedList;
    }

    @Override
    public ApiResponse<List<OrgTargets>> getByOrgCode() {
        String orgCode = authenticationFacade.getLoggedInUser().getDepot();
        List<OrgTargets> list = repository.findByOrgCodeAndValidFlagTrueOrderByTargetIdAsc(orgCode);

        long count = (list != null) ? list.size() : 0;
        String message = count > 0 ? "Record fetched successfully." : "No record found";
        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    public Optional<OrgTargets> getById(Long targetId) {
        return repository.findByTargetIdAndValidFlagTrue(targetId);
    }

    @Override
    public String deleteById(Long targetId) {
        String username = authenticationFacade.getLoggedInUser().getUser_name();

        Optional<OrgTargets> optional = repository.findById(targetId);
        if (!optional.isPresent()) {
            throw new RuntimeException("OrgTarget not found with ID: " + targetId);
        }

        OrgTargets target = optional.get();
        target.setValidFlag(false);
        target.setUpdatedBy(username);
        target.setUpdatedAt(new Date());
        repository.save(target);

        return "Deleted successfully";
    }
}
