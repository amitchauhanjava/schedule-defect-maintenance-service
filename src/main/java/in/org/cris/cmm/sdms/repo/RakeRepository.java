package in.org.cris.cmm.sdms.repo;


import in.org.cris.cmm.sdms.entity.Rake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RakeRepository extends JpaRepository<Rake, Long> {

}
