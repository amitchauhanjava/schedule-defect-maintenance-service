package in.org.cris.cmm.sdms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MustChangeMaterialAssemblyDTO {

    private Long mustChangeId;
    private Long rsTypeMaintenanceId;

    private String assemblyName;
    private String partNo;
    private String description;

    private Integer frequencyDays;
    private Integer frequencyKms;

    private Boolean validFlag;
}

