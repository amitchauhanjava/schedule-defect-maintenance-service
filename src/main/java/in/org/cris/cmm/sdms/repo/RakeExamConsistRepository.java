package in.org.cris.cmm.sdms.repo;

import in.org.cris.cmm.sdms.entity.RakeExamConsist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RakeExamConsistRepository extends JpaRepository<RakeExamConsist, Long> {

}
