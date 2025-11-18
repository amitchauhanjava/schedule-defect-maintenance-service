package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.entity.MaintenanceActionMaster;
import in.org.cris.cmm.rms.repo.MaintenanceActionRepository;
import in.org.cris.cmm.rms.service.MaintenanceActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceActionServiceImpl implements MaintenanceActionService {

        private final MaintenanceActionRepository maintenanceActionRepository;

        @Override
        public List<MaintenanceActionMaster> getAllValidActions() {
                return maintenanceActionRepository.findByValidFlagTrueOrderByActionIdAsc();
        }
}
