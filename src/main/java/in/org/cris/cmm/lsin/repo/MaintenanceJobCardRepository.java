package in.org.cris.cmm.lsin.repo;

import in.org.cris.cmm.lsin.entity.MaintenanceJobCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceJobCardRepository extends JpaRepository<MaintenanceJobCard, Long> {

        List<MaintenanceJobCard> findByValidFlagTrueOrderByJobCardIdAsc();
}