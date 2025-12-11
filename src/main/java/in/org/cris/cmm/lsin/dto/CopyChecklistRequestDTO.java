package in.org.cris.cmm.lsin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CopyChecklistRequestDTO {

    private Long oldRsTypeMaintenanceId;
    private List<Long> newRsTypeMaintenanceIds;

}

