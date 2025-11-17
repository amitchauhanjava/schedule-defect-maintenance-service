package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.entity.MasterDivision;
import in.org.cris.cmm.rms.repo.MasterDivisionRepository;
import in.org.cris.cmm.rms.service.MasterDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterDivisionServiceImpl implements MasterDivisionService {

    @Autowired
    private MasterDivisionRepository divisionRepository;

    @Override
    public List<MasterDivision> getMasterDivisionList() {

        List<MasterDivision> divisionList = divisionRepository.findAll();
        return divisionList.stream().sorted(Comparator.comparing(MasterDivision::getDivisionId).reversed()).collect(Collectors.toList());
    }
}
