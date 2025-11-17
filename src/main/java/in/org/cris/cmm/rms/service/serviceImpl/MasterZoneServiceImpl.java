package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.entity.MasterZone;
import in.org.cris.cmm.rms.repo.MasterZoneRepository;
import in.org.cris.cmm.rms.service.MasterZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterZoneServiceImpl implements MasterZoneService {

    @Autowired
    private MasterZoneRepository zoneRepository;

    @Override
    public List<MasterZone> getListMasterZone() {
        List<MasterZone> zoneList = zoneRepository.findAll();

        return zoneList.stream().sorted(Comparator.comparing(MasterZone::getZoneId).reversed()).collect(Collectors.toList());
    }
}
