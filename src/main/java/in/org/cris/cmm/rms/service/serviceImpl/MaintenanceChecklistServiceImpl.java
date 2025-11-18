package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.entity.MaintenanceChecklistMaster;
import in.org.cris.cmm.rms.repo.MaintenanceChecklistRepository;
import in.org.cris.cmm.rms.service.MaintenanceChecklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceChecklistServiceImpl implements MaintenanceChecklistService {

        private final MaintenanceChecklistRepository maintenanceChecklistRepository;

        @Override
        public List<MaintenanceChecklistMaster> getAllValidChecklistItems() {
                return maintenanceChecklistRepository.findByValidFlagTrueOrderByChecklistIdAsc();
        }
}
