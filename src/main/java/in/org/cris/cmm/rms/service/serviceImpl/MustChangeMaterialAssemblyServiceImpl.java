package in.org.cris.cmm.rms.service.serviceImpl;

import in.org.cris.cmm.rms.config.AuthenticationFacade;
import in.org.cris.cmm.rms.dto.MustChangeMaterialAssemblyDTO;
import in.org.cris.cmm.rms.entity.MasterRsTypeMaintenance;
import in.org.cris.cmm.rms.entity.MustChangeMaterialAssemblyMaster;
import in.org.cris.cmm.rms.repo.MasterRsTypeMaintenanceRepository;
import in.org.cris.cmm.rms.repo.MustChangeMaterialAssemblyRepository;
import in.org.cris.cmm.rms.service.MustChangeMaterialAssemblyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MustChangeMaterialAssemblyServiceImpl implements MustChangeMaterialAssemblyService {

        private final MustChangeMaterialAssemblyRepository mustChangeMaterialAssemblyRepository;

        private final AuthenticationFacade authenticationFacade;

        private final MasterRsTypeMaintenanceRepository masterRsTypeMaintenanceRepository;

        @Override
        public List<MustChangeMaterialAssemblyMaster> getAllValidMustChangeMaterials(Long rsTypeMaintId) {
                return mustChangeMaterialAssemblyRepository.findActiveByOptionalRsType(rsTypeMaintId);
        }

        @Override
        @Transactional
        public MustChangeMaterialAssemblyMaster saveOrUpdate(MustChangeMaterialAssemblyDTO dto) {

                String loggedUser = authenticationFacade.getLoggedInUser().getUser_name();
                Date now = new Date();
                MustChangeMaterialAssemblyMaster entity;

                if (dto.getMustChangeId() != null) {
                        // Update
                        entity = mustChangeMaterialAssemblyRepository.findById(dto.getMustChangeId()).orElseThrow(() -> new RuntimeException("Must Change Material not found: " + dto.getMustChangeId()));

                        entity.setUpdatedBy(loggedUser);
                        entity.setUpdatedAt(now);

                } else {
                        // Create
                        entity = new MustChangeMaterialAssemblyMaster();

                        entity.setValidFlag(true);
                        entity.setCreatedBy(loggedUser);
                        entity.setCreatedAt(now);
                }
                // Reference table ID
                if (dto.getRsTypeMaintenanceId() != null) {
                        MasterRsTypeMaintenance ref = masterRsTypeMaintenanceRepository.findById(dto.getRsTypeMaintenanceId()).orElseThrow(() -> new RuntimeException("RS Type not found"));
                        entity.setRsTypeMaintenance(ref);
                }
                // Non-null updates
                if (dto.getAssemblyName() != null) entity.setAssemblyName(dto.getAssemblyName());
                if (dto.getPartNo() != null) entity.setPartNo(dto.getPartNo());
                if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
                if (dto.getFrequencyDays() != null) entity.setFrequencyDays(dto.getFrequencyDays());
                if (dto.getFrequencyKms() != null) entity.setFrequencyKms(dto.getFrequencyKms());

                return mustChangeMaterialAssemblyRepository.save(entity);
        }

        @Override
        @Transactional
        public String softDelete(Long id) {
                MustChangeMaterialAssemblyMaster entity = mustChangeMaterialAssemblyRepository.findById(id).orElseThrow(() -> new RuntimeException("Must Change Material not found: " + id));

                entity.setValidFlag(false);
                entity.setUpdatedBy(authenticationFacade.getLoggedInUser().getUser_name());
                entity.setUpdatedAt(new Date());

                mustChangeMaterialAssemblyRepository.save(entity);
                return "Record deleted successfully";
        }
}
