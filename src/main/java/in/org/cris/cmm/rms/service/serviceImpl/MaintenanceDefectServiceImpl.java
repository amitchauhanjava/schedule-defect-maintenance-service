package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.entity.MaintenanceDefectMaster;
import in.org.cris.cmm.rms.repo.MaintenanceDefectRepository;
import in.org.cris.cmm.rms.service.MaintenanceDefectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MaintenanceDefectServiceImpl implements MaintenanceDefectService {

        private final MaintenanceDefectRepository maintenanceDefectRepository;

        @Override
        public List<MaintenanceDefectMaster> getAllValidDefects() {
                return maintenanceDefectRepository.findByValidFlagTrueOrderByDefectIdAsc();
        }
}
