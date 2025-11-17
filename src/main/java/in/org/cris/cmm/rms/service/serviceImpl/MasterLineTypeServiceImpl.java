package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.dto.ApiResponse;
import in.org.cris.cmm.rms.entity.MasterLineType;
import in.org.cris.cmm.rms.repo.MasterLineTypeRepository;
import in.org.cris.cmm.rms.service.MasterLineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterLineTypeServiceImpl implements MasterLineTypeService {

    @Autowired
    private MasterLineTypeRepository masterLineTypeRepository;

    @Override
    public ApiResponse<List<MasterLineType>> getList() {

        List<MasterLineType> list = masterLineTypeRepository.getByValidFlagTrueOrderByLineTypeIdAsc();

        long count = list != null? list.size(): 0;
        String message = count>0? "Record Fetched Successfully.":"No Record Found";
        return new ApiResponse<>(count, list, HttpStatus.OK.value(), message);
    }
}
