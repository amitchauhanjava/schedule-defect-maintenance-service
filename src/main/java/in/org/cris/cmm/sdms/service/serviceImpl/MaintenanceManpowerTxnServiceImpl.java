package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.dto.MaintenanceManpowerTxnDTO;
import in.org.cris.cmm.sdms.entity.MaintenanceDefectTxn;
import in.org.cris.cmm.sdms.entity.MaintenanceDetails;
import in.org.cris.cmm.sdms.entity.MaintenanceManpowerTxn;
import in.org.cris.cmm.sdms.repo.MaintenanceManpowerTxnRepository;
import in.org.cris.cmm.sdms.service.MaintenanceManpowerTxnService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceManpowerTxnServiceImpl implements MaintenanceManpowerTxnService {

	private final MaintenanceManpowerTxnRepository repository;

	@Override
	public List<MaintenanceManpowerTxn> saveOrUpdate(List<MaintenanceManpowerTxnDTO> dtoList) {

		List<MaintenanceManpowerTxn> result = new ArrayList<>();

		for (MaintenanceManpowerTxnDTO dto : dtoList) {

			MaintenanceManpowerTxn entity;

			if (dto.getManpowerTxnId() != null) {
				entity = repository.findById(dto.getManpowerTxnId()).orElseThrow(() -> new RuntimeException("Record not found"));

				if (dto.getCategory() != null) entity.setCategory(dto.getCategory());
				if (dto.getManpowerName() != null) entity.setManpowerName(dto.getManpowerName());
				if (dto.getDesignation() != null) entity.setDesignation(dto.getDesignation());
				if (dto.getHoursSpent() != null) entity.setHoursSpent(dto.getHoursSpent());
				if (dto.getManpowerCost() != null) entity.setManpowerCost(dto.getManpowerCost());

				if (dto.getMaintenanceId() != null) {
					MaintenanceDetails maintenance = new MaintenanceDetails();
					maintenance.setMaintenanceId(dto.getMaintenanceId());   // only ID
					entity.setMaintenanceDetails(maintenance);
				}

				if (dto.getDefectTxnId() != null) {
					MaintenanceDefectTxn defect = new MaintenanceDefectTxn();
					defect.setDefectTxnId(dto.getDefectTxnId());
					entity.setDefect(defect);
				}


				entity.setUpdatedBy(dto.getUser());
				entity.setUpdatedAt(new Date());

			} else {
				// CREATE
				entity = new MaintenanceManpowerTxn();

				if (dto.getMaintenanceId() != null) {
					MaintenanceDetails maintenance = new MaintenanceDetails();
					maintenance.setMaintenanceId(dto.getMaintenanceId());   // only ID
					entity.setMaintenanceDetails(maintenance);
				}

				if (dto.getDefectTxnId() != null) {
					MaintenanceDefectTxn defect = new MaintenanceDefectTxn();
					defect.setDefectTxnId(dto.getDefectTxnId());
					entity.setDefect(defect);
				}

				entity.setCategory(dto.getCategory());
				entity.setManpowerName(dto.getManpowerName());
				entity.setDesignation(dto.getDesignation());
				entity.setHoursSpent(dto.getHoursSpent());
				entity.setManpowerCost(dto.getManpowerCost());

				entity.setValidFlag(true);
				entity.setCreatedBy(dto.getUser());
				entity.setCreatedAt(new Date());
			}

			result.add(repository.save(entity));
		}

		return result;
	}

	@Override
	public void softDelete(Long manpowerTxnId) {

		MaintenanceManpowerTxn entity = repository.findById(manpowerTxnId)
				.orElseThrow(() -> new RuntimeException("Record not found"));

		entity.setValidFlag(false);
//		entity.setUpdatedBy(user);
		entity.setUpdatedAt(new Date());

		repository.save(entity);
	}

	@Override
	public List<MaintenanceManpowerTxn> getActiveList(
			Long maintenanceId,
			Long defectTxnId) {

		return repository.findActiveList(maintenanceId, defectTxnId);
	}
}

