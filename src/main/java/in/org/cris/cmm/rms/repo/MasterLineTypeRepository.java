package in.org.cris.cmm.rms.repo;

import in.org.cris.cmm.rms.entity.MasterLineType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterLineTypeRepository extends JpaRepository<MasterLineType, Long> {

    List<MasterLineType> getByValidFlagTrueOrderByLineTypeIdAsc();
}
