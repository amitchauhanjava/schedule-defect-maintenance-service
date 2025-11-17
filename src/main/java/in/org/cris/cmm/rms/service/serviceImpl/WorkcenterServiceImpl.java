package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.WorkcenterDTO;
import in.org.cris.cmm.rms.entity.Workcenter;
import in.org.cris.cmm.rms.repo.WorkcenterRepository;
import in.org.cris.cmm.rms.service.WorkcenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WorkcenterServiceImpl implements WorkcenterService {

    @Autowired
    private WorkcenterRepository workcenterRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Override
    public Workcenter saveOrUpdate(WorkcenterDTO dto) {

        String username = authenticationFacade.getLoggedInUser().getUser_name();
        String orgCode = authenticationFacade.getLoggedInUser().getDepot();

        List<Workcenter> savedList = new ArrayList<>();


        if (dto.getWorkcenterId() == null) {
            Workcenter wc = new Workcenter();

            // New record
            wc.setWorkcenterCode(dto.getWorkcenterCode());
            wc.setWorkcenterName(dto.getWorkcenterName());
            wc.setSectionId(dto.getSectionId());
            wc.setDescription(dto.getDescription());
            wc.setCommissioningDate(dto.getCommissioningDate());
            wc.setRemarks(dto.getRemarks());
            wc.setValidFlag(true);
            wc.setDeviceType(dto.getDeviceType());

            wc.setOrgCode(orgCode);
            wc.setCreatedBy(username);
            wc.setCreatedAt(new Date());
            return workcenterRepository.save(wc);
        } else {
            // Update existing
            Workcenter existing = workcenterRepository.findById(dto.getWorkcenterId()).orElseThrow(() -> new RuntimeException("Workcenter not found with ID: " + dto.getWorkcenterId()));

            if (dto.getWorkcenterCode() != null) existing.setWorkcenterCode(dto.getWorkcenterCode());
            if (dto.getWorkcenterName() != null) existing.setWorkcenterName(dto.getWorkcenterName());
            if (dto.getOrgCode() != null) existing.setOrgCode(dto.getOrgCode());
            if (dto.getSectionId() != null) existing.setSectionId(dto.getSectionId());
            if (dto.getDescription() != null) existing.setDescription(dto.getDescription());
            if (dto.getCommissioningDate() != null) existing.setCommissioningDate(dto.getCommissioningDate());
            if (dto.getRemarks() != null) existing.setRemarks(dto.getRemarks());
            if (dto.getValidFlag() != null) existing.setValidFlag(dto.getValidFlag());
            if (dto.getDeviceType() != null) existing.setDeviceType(dto.getDeviceType());

            existing.setUpdatedBy(username);
            existing.setUpdatedAt(new Date());
            return workcenterRepository.save(existing);
        }
    }

    @Override
    public ApiResponse<List<Workcenter>> getDetailsByOrgCode() {
        String orgCode = authenticationFacade.getLoggedInUser().getDepot();
        List<Workcenter> list = workcenterRepository.findByOrgCodeAndValidFlagTrueOrderByWorkcenterIdAsc(orgCode);

        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Records fetched successfully" : "No records found";
        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    public ApiResponse<List<Workcenter>> getDetailsById(Long id) {
        String orgCode = authenticationFacade.getLoggedInUser().getDepot();
        List<Workcenter> list = workcenterRepository.findByWorkcenterIdAndValidFlagTrue(id);

        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Records fetched successfully" : "No records found";
        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Workcenter> optional = workcenterRepository.findById(id);
        if (!optional.isPresent()) {
            return false;
        }
        Workcenter workcenter = optional.get();
        workcenter.setValidFlag(false);
        workcenter.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
        workcenter.setUpdatedAt(new Date());
        workcenterRepository.save(workcenter);
        return true;
    }
}
