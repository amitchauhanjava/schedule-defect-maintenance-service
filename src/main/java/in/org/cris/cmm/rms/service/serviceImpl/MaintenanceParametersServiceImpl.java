package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.entity.MaintenanceParametersMaster;
import in.org.cris.cmm.rms.repo.MaintenanceParametersRepository;
import in.org.cris.cmm.rms.service.MaintenanceParametersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceParametersServiceImpl implements MaintenanceParametersService {
        private final MaintenanceParametersRepository repository;
        @Override
        public List<MaintenanceParametersMaster> getAllValidParameters() {
                return repository.findByValidFlagTrueOrderByParameterIdAsc();
        }
}
