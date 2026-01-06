package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.dto.MaintenanceMaterialsTxnDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceDefectTxn;
import in.org.cris.cmm.sdms.entity.MaintenanceDetails;
import in.org.cris.cmm.sdms.entity.MaintenanceMaterialsTxn;
import in.org.cris.cmm.sdms.entity.MustChangeMaterialAssemblyMaster;
import in.org.cris.cmm.sdms.repo.MaintenanceMaterialsTxnRepository;
import in.org.cris.cmm.sdms.service.MaintenanceMaterialsTxnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceMaterialsTxnServiceImpl implements MaintenanceMaterialsTxnService {

	private final MaintenanceMaterialsTxnRepository repository;

	@Override
	public List<MaintenanceMaterialsTxn> saveOrUpdate(
			List<MaintenanceMaterialsTxnDTO> dtoList) {

		List<MaintenanceMaterialsTxn> result = new ArrayList<>();

		for (MaintenanceMaterialsTxnDTO dto : dtoList) {

			MaintenanceMaterialsTxn entity;

			if (dto.getMaterialTxnId() != null) {
				// UPDATE
				entity = repository.findById(dto.getMaterialTxnId()).orElseThrow(() -> new RuntimeException("Record not found"));

				if (dto.getMaterialName() != null) entity.setMaterialName(dto.getMaterialName());

				if (dto.getDescription() != null) entity.setDescription(dto.getDescription());

				if (dto.getQuantityUsed() != null) entity.setQuantityUsed(dto.getQuantityUsed());

				if (dto.getMaterialCost() != null) entity.setMaterialCost(dto.getMaterialCost());

				if (dto.getMaintenanceId() != null) {
					MaintenanceDetails m = new MaintenanceDetails();
					m.setMaintenanceId(dto.getMaintenanceId());
					entity.setMaintenanceDetails(m);
				}

				if (dto.getDefectTxnId() != null) {
					MaintenanceDefectTxn d = new MaintenanceDefectTxn();
					d.setDefectTxnId(dto.getDefectTxnId());
					entity.setDefect(d);
				}

				if (dto.getMustChangeId() != null) {
					MustChangeMaterialAssemblyMaster mc = new MustChangeMaterialAssemblyMaster();
					mc.setMustChangeId(dto.getMustChangeId());
					entity.setMustChange(mc);
				}

				entity.setUpdatedBy(dto.getUser());
				entity.setUpdatedAt(new Date());

			} else {
				// CREATE
				entity = new MaintenanceMaterialsTxn();

				MaintenanceDetails m = new MaintenanceDetails();
				m.setMaintenanceId(dto.getMaintenanceId());
				entity.setMaintenanceDetails(m);

				if (dto.getDefectTxnId() != null) {
					MaintenanceDefectTxn d = new MaintenanceDefectTxn();
					d.setDefectTxnId(dto.getDefectTxnId());
					entity.setDefect(d);
				}

				if (dto.getMustChangeId() != null) {
					MustChangeMaterialAssemblyMaster mc = new MustChangeMaterialAssemblyMaster();
					mc.setMustChangeId(dto.getMustChangeId());
					entity.setMustChange(mc);
				}

				entity.setMaterialName(dto.getMaterialName());
				entity.setDescription(dto.getDescription());
				entity.setQuantityUsed(dto.getQuantityUsed());
				entity.setMaterialCost(dto.getMaterialCost());

				entity.setValidFlag(true);
				entity.setCreatedBy(dto.getUser());
				entity.setCreatedAt(new Date());
			}

			result.add(repository.save(entity));
		}

		return result;
	}

	@Override
	public void delete(Long materialTxnId) {

		MaintenanceMaterialsTxn entity = repository.findById(materialTxnId).orElseThrow(() -> new RuntimeException("Record not found"));

		entity.setValidFlag(false);
		entity.setUpdatedAt(new Date());

		repository.save(entity);
	}

	@Override
	public List<MaintenanceMaterialsTxn> getActiveList(Long maintenanceId, Long defectTxnId) {

		return repository.findActiveList(maintenanceId, defectTxnId);
	}
}

