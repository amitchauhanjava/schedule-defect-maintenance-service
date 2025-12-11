package in.org.cris.cmm.lsin.service.serviceImpl;

import in.org.cris.cmm.lsin.config.AuthenticationFacade;
import in.org.cris.cmm.lsin.dto.MaintenanceParamTxnDTO;
import in.org.cris.cmm.lsin.entity.*;
import in.org.cris.cmm.lsin.repo.*;
import in.org.cris.cmm.lsin.service.MaintenanceParamTxnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceParamTxnServiceImpl implements MaintenanceParamTxnService {

        private final MaintenanceParamTxnRepository repository;
        private final MaintenanceDetailsRepository detailsRepo;
        private final MaintenanceParametersRepository parametersRepo;
        private final AuthenticationFacade authenticationFacade;

        @Override
        public MaintenanceParamTxn saveOrUpdate(MaintenanceParamTxnDTO dto) {

                MaintenanceParamTxn entity;

                if (dto.getParamTxnId() != null) {
                        entity = repository.findById(dto.getParamTxnId())
                                .orElseThrow(() -> new RuntimeException("Parameter Txn not found: " + dto.getParamTxnId()));

                        entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setUpdatedAt(new Date());

                } else {
                        entity = new MaintenanceParamTxn();
                        entity.setValidFlag(true);
                        entity.setCreatedAt(new Date());
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                }

                if (dto.getMaintenanceId() != null) {
                        MaintenanceDetails md = detailsRepo.findById(dto.getMaintenanceId())
                                .orElseThrow(() -> new RuntimeException("Maintenance details not found"));
                        entity.setMaintenanceDetails(md);
                }

                if (dto.getParameterId() != null) {
                        MaintenanceParametersMaster pm = parametersRepo.findById(dto.getParameterId())
                                .orElseThrow(() -> new RuntimeException("Parameter master not found"));
                        entity.setParameterMaster(pm);
                }

                entity.setPreValue(dto.getPreValue());
                entity.setPostValue(dto.getPostValue());
                entity.setRemarks(dto.getRemarks());

                return repository.save(entity);
        }

        @Override
        public String delete(Long id) {

                MaintenanceParamTxn entity = repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Param Txn not found: " + id));

                entity.setValidFlag(false);
                entity.setUpdatedAt(new Date());
                entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());

                repository.save(entity);

                return "Record deleted successfully";
        }

        @Override
        public List<MaintenanceParamTxn> getAllValidParamTxns() {
                return repository.findByValidFlagTrueOrderByParamTxnIdAsc();
        }
}
