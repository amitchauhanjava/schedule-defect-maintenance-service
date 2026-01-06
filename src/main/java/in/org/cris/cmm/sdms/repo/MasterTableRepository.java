package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.MasterTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterTableRepository extends JpaRepository<MasterTable, Long> {

	List<MasterTable> findByTypeAndValidFlagTrue(String type);
}
