package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.config.AuthenticationFacade;
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
	private final AuthenticationFacade authenticationFacade;

	@Override
	public List<MaintenanceManpowerTxnDTO> saveOrUpdate(List<MaintenanceManpowerTxnDTO> dtoList) {

		List<MaintenanceManpowerTxnDTO> result = new ArrayList<>();
		String username = authenticationFacade.getLoggedInUser().getUser_name();

		for (MaintenanceManpowerTxnDTO dto : dtoList) {

			MaintenanceManpowerTxn entity;

			if (dto.getManpowerTxnId() != null) {
				// UPDATE
				entity = repository.findById(dto.getManpowerTxnId())
						.orElseThrow(() -> new RuntimeException("Record not found"));

				if (dto.getCategory() != null) entity.setCategory(dto.getCategory());
				if (dto.getManpowerName() != null) entity.setManpowerName(dto.getManpowerName());
				if (dto.getDesignation() != null) entity.setDesignation(dto.getDesignation());
				if (dto.getHoursSpent() != null) entity.setHoursSpent(dto.getHoursSpent());
				if (dto.getManpowerCost() != null) entity.setManpowerCost(dto.getManpowerCost());

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

				entity.setUpdatedBy(username);
				entity.setUpdatedAt(new Date());

			} else {
				// CREATE
				entity = new MaintenanceManpowerTxn();

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

				entity.setCategory(dto.getCategory());
				entity.setManpowerName(dto.getManpowerName());
				entity.setDesignation(dto.getDesignation());
				entity.setHoursSpent(dto.getHoursSpent());
				entity.setManpowerCost(dto.getManpowerCost());

				entity.setValidFlag(true);
				entity.setCreatedBy(username);
				entity.setCreatedAt(new Date());
			}

			MaintenanceManpowerTxn saved = repository.save(entity);

			// 🔹 INLINE DTO CREATION (no separate method)
			MaintenanceManpowerTxnDTO responseDto = new MaintenanceManpowerTxnDTO();
			responseDto.setManpowerTxnId(saved.getManpowerTxnId());

			if (saved.getMaintenanceDetails() != null) {
				responseDto.setMaintenanceId(saved.getMaintenanceDetails().getMaintenanceId());
			}

			if (saved.getDefect() != null) {
				responseDto.setDefectTxnId(saved.getDefect().getDefectTxnId());
			}

			responseDto.setCategory(saved.getCategory());
			responseDto.setManpowerName(saved.getManpowerName());
			responseDto.setDesignation(saved.getDesignation());
			responseDto.setHoursSpent(saved.getHoursSpent());
			responseDto.setManpowerCost(saved.getManpowerCost());

			// user → createdBy for new, updatedBy for update
			responseDto.setUser(
					dto.getManpowerTxnId() == null ? saved.getCreatedBy() : saved.getUpdatedBy()
			);

			result.add(responseDto);
		}

		return result;
	}

	@Override
	public void softDelete(Long manpowerTxnId) {

		String username = authenticationFacade.getLoggedInUser().getUser_name();

		MaintenanceManpowerTxn entity = repository.findById(manpowerTxnId).orElseThrow(() -> new RuntimeException("Record not found"));

		entity.setValidFlag(false);
		entity.setUpdatedBy(username);
		entity.setUpdatedAt(new Date());

		repository.save(entity);
	}

	@Override
	public List<MaintenanceManpowerTxn> getActiveList(Long maintenanceId, Long defectTxnId) {

		return repository.findActiveList(maintenanceId, defectTxnId);
	}
}

