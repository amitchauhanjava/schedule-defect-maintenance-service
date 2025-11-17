package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MasterInfraLine;
import in.org.cris.cmm.rms.repo.MasterInfraLineRepository;
import in.org.cris.cmm.rms.service.MasterInfraLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MasterInfraLineServiceImpl implements MasterInfraLineService {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private MasterInfraLineRepository repository;


    @Override
    public MasterInfraLine saveOrUpdate(MasterInfraLine line) {

        String orgCode = authenticationFacade.getLoggedInUser().getDepot();
        String username = authenticationFacade.getLoggedInUser().getUser_name();

        if (line.getMasterLineId() == null) {
            // New record
            line.setOrgCode(orgCode);
            line.setCreatedBy(username);
            line.setCreatedAt(new Date());
            return repository.save(line);
        } else {
            // Update existing record
            Optional<MasterInfraLine> existingOpt = repository.findById(line.getMasterLineId());
            if (existingOpt.isPresent()) {
                MasterInfraLine existing = existingOpt.get();

                if (line.getMasterLineName() != null) existing.setMasterLineName(line.getMasterLineName());
                if (line.getMasterLineCode() != null) existing.setMasterLineCode(line.getMasterLineCode());
                if (line.getStationCode() != null) existing.setStationCode(line.getStationCode());
                if (line.getLooplineCode() != null) existing.setLooplineCode(line.getLooplineCode());
                if (line.getSectionId() != null) existing.setSectionId(line.getSectionId());
                if (line.getWorkcenterId() != null) existing.setWorkcenterId(line.getWorkcenterId());
                if (line.getLengthMeters() != null) existing.setLengthMeters(line.getLengthMeters());
                if (line.getCapacityUnits() != null) existing.setCapacityUnits(line.getCapacityUnits());
                if (line.getDesignedCapacity() != null) existing.setDesignedCapacity(line.getDesignedCapacity());
                if (line.getCurrentUtilization() != null) existing.setCurrentUtilization(line.getCurrentUtilization());
                if (line.getCanExtend() != null) existing.setCanExtend(line.getCanExtend());
                if (line.getLastMaintenanceDate() != null) existing.setLastMaintenanceDate(line.getLastMaintenanceDate());
                if (line.getCommissioningDate() != null) existing.setCommissioningDate(line.getCommissioningDate());
                if (line.getElectrified() != null) existing.setElectrified(line.getElectrified());
                if (line.getRemarks() != null) existing.setRemarks(line.getRemarks());
                if (line.getValidFlag() != null) existing.setValidFlag(line.getValidFlag());
                if (line.getDeviceType() != null) existing.setDeviceType(line.getDeviceType());

                existing.setUpdatedBy(username);
                existing.setUpdatedAt(new Date());

                return repository.save(existing);
            } else {
                throw new RuntimeException("MasterInfraLine not found with ID: " + line.getMasterLineId());
            }
        }
    }

    @Override
    public ApiResponse<List<MasterInfraLine>> getList() {
        String orgCode = authenticationFacade.getLoggedInUser().getDepot();
        List<MasterInfraLine> list = repository.findByOrgCodeAndValidFlagTrueOrderByMasterLineIdAsc(orgCode);

        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Record Fetched Successfully." : "No Record Found";

        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    public ApiResponse<List<MasterInfraLine>> getDetailsById(Long id) {
        List<MasterInfraLine> list = repository.findByMasterLineIdAndValidFlagTrue(id);

        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Record Fetched Successfully." : "No Record Found";

        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    public String deleteById(Long id) {
        String username = authenticationFacade.getLoggedInUser().getUser_name();
        Optional<MasterInfraLine> optional = repository.findById(id);

        if (!optional.isPresent()) {
            return "MasterInfraLine not found for id: " + id;
        }

        MasterInfraLine line = optional.get();
        line.setValidFlag(false);
        line.setUpdatedBy(username);
        line.setUpdatedAt(new Date());
        repository.save(line);

        return "Deleted successfully";
    }

}
