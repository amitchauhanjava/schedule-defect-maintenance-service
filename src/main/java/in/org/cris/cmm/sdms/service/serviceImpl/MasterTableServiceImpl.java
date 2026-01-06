package in.org.cris.cmm.sdms.service.serviceImpl;

import in.org.cris.cmm.sdms.entity.MasterTable;
import in.org.cris.cmm.sdms.repo.MasterTableRepository;
import in.org.cris.cmm.sdms.service.MasterTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterTableServiceImpl implements MasterTableService {

	private final MasterTableRepository repository;

	@Override
	public List<MasterTable> getByType(String type) {
		return repository.findByTypeAndValidFlagTrue(type);
	}
}

