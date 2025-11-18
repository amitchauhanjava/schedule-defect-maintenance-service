package in.org.cris.cmm.rms.service;

import in.org.cris.cmm.rms.entity.MaintenanceTaskListMaster;
import in.org.cris.cmm.rms.repo.MaintenanceTaskListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceTaskListServiceImpl implements MaintenanceTaskListService {

    //Field inject
/*    @Autowired
    private MaintenanceTaskListRepository maintenanceTaskListRepository;*/

    private final MaintenanceTaskListRepository maintenanceTaskListRepository;

    @Override
    public List<MaintenanceTaskListMaster> getAllValidTasks() {
        return maintenanceTaskListRepository.findByValidFlagTrueOrderByTaskIdAsc();
    }
}
