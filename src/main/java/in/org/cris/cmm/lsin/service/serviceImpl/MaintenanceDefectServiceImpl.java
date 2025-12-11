package in.org.cris.cmm.lsin.service.serviceImpl;

import in.org.cris.cmm.lsin.config.AuthenticationFacade;
import in.org.cris.cmm.lsin.dto.MaintenanceDefectDTO;
import in.org.cris.cmm.lsin.entity.MaintenanceDefectMaster;
import in.org.cris.cmm.lsin.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.lsin.repo.MaintenanceDefectRepository;
import in.org.cris.cmm.lsin.repo.MasterRsTypeMaintenanceRepository;
import in.org.cris.cmm.lsin.service.MaintenanceDefectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor

public class MaintenanceDefectServiceImpl implements MaintenanceDefectService {

        private final MaintenanceDefectRepository defectRepository;
        private final AuthenticationFacade authenticationFacade;
        private final MasterRsTypeMaintenanceRepository rsTypeMaintenanceRepository;

        @Override
        @Transactional
        public MaintenanceDefectMaster saveOrUpdate(MaintenanceDefectDTO dto) {

                MaintenanceDefectMaster entity;

                if (dto.getDefectId() != null) {
                        entity = defectRepository.findById(dto.getDefectId()).orElseThrow(() -> new RuntimeException("Defect not found: " + dto.getDefectId()));
                        entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setUpdatedAt(new Date());
                } else {
                        entity = new MaintenanceDefectMaster();
                        entity.setValidFlag(true);
                        entity.setCreatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                        entity.setCreatedAt(new Date());
                }

                if (dto.getRsTypeMaintenanceId() != null) {
                        MasterRsTypeMaintenance ref = rsTypeMaintenanceRepository.findById(dto.getRsTypeMaintenanceId()).orElseThrow(() -> new RuntimeException("RS Type Maintenance not found"));
                        entity.setRsTypeMaintenance(ref);
                }

                if (dto.getDefectCode() != null) entity.setDefectCode(dto.getDefectCode());
                if (dto.getDefectDescription() != null) entity.setDefectDescription(dto.getDefectDescription());
                if (dto.getSeverity() != null) entity.setSeverity(dto.getSeverity());

                return defectRepository.save(entity);
        }

        @Override
        @Transactional
        public String delete(Long defectId) {

                MaintenanceDefectMaster entity = defectRepository.findById(defectId)
                        .orElseThrow(() -> new RuntimeException("Defect not found: " + defectId));

                entity.setValidFlag(false);
                entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                entity.setUpdatedAt(new Date());

                defectRepository.save(entity);
                return "Defect deleted successfully";
        }

        @Override
        public List<MaintenanceDefectMaster> getAllValidDefects() {
                return defectRepository.findByValidFlagTrueOrderByDefectIdAsc();
        }
}
