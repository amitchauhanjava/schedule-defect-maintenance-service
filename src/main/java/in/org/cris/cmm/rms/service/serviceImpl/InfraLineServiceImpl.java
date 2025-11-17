package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.dto.InfraLineDTO;
import in.org.cris.cmm.rms.entity.InfraLine;
import in.org.cris.cmm.rms.entity.MasterInfraLine;
import in.org.cris.cmm.rms.entity.MasterLineType;
import in.org.cris.cmm.rms.repo.InfraLineRepository;
import in.org.cris.cmm.rms.service.InfraLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class InfraLineServiceImpl implements InfraLineService {

    private final InfraLineRepository repository;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public List<InfraLine> saveOrUpdate(List<InfraLineDTO> infraLines) {
        String username = authenticationFacade.getLoggedInUser().getUser_name();

        List<InfraLine> savedList = new ArrayList<>();

        for (InfraLineDTO dto : infraLines) {
            InfraLine entity;

            if (dto.getLineId() == null) {
                // New record
                entity = new InfraLine();
                entity.setCreatedBy(username);
                entity.setCreatedAt(new Date());
            } else {
                // Update existing record
                entity = repository.findById(dto.getLineId()).orElseThrow(() -> new RuntimeException("InfraLine not found with ID: " + dto.getLineId()));

                entity.setUpdatedBy(username);
                entity.setUpdatedAt(new Date());
            }

            if (dto.getLineTypeId() != null) {
                if (entity.getLineType() == null) {
                    entity.setLineType(new MasterLineType());
                }
                entity.getLineType().setLineTypeId(dto.getLineTypeId());
            }

            if (dto.getMasterLineId() != null) {
                if (entity.getMasterInfraLine() == null) {
                    entity.setMasterInfraLine(new MasterInfraLine());
                }
                entity.getMasterInfraLine().setMasterLineId(dto.getMasterLineId());
            }

            if (dto.getFromLengthMeters() != null) entity.setFromLengthMeters(dto.getFromLengthMeters());
            if (dto.getToLengthMeters() != null) entity.setToLengthMeters(dto.getToLengthMeters());
            if (dto.getDeviceType() != null) entity.setDeviceType(dto.getDeviceType());
            if (dto.getValidFlag() != null) entity.setValidFlag(dto.getValidFlag());

            savedList.add(repository.save(entity));
        }

        return savedList;
    }


    @Override
    public ApiResponse<List<InfraLine>> getList() {
//        String orgCode = authenticationFacade.getLoggedInUser().getDepot();
        List<InfraLine> list = repository.findByValidFlagTrue();

        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Record Fetched Successfully." : "No Record Found";

        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    public ApiResponse<List<InfraLine>> getDetailsById(Long id) {
        List<InfraLine> list = repository.findByLineIdAndValidFlagTrue(id);

        long count = list != null ? list.size() : 0;
        String message = count > 0 ? "Record Fetched Successfully." : "No Record Found";

        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }

    @Override
    public String deleteById(Long id) {
        String username = authenticationFacade.getLoggedInUser().getUser_name();

        Optional<InfraLine> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("InfraLine not found with ID: " + id);
        }

        InfraLine line = optional.get();
        line.setValidFlag(false);
        line.setUpdatedBy(username);
        line.setUpdatedAt(new Date());
        repository.save(line);

        return "Deleted successfully";
    }
}