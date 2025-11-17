package in.org.cris.cmm.rms.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(
        name = "master_infra_lines",
        schema = "lsin",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"master_line_code"}),
                @UniqueConstraint(columnNames = {"station_code", "loopline_code"})
        }
)
public class MasterInfraLine {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "master_line_id")
        private Long masterLineId;

        @NotNull(message = "Master line name is mandatory")
        @Size(max = 25)
        @Column(name = "master_line_name", nullable = false, length = 25)
        private String masterLineName;

        @NotNull
        @Size(max = 20)
        @Column(name = "org_code", nullable = false, length = 20)
        private String orgCode;

        @Size(max = 20)
        @Column(name = "station_code", length = 20)
        private String stationCode;

        @Size(max = 20)
        @Column(name = "loopline_code", length = 20)
        private String looplineCode;

        @Column(name = "section_id")
        private Long sectionId;

        @Column(name = "workcenter_id")
        private Long workcenterId;

        @Column(name = "length_meters")
        private Integer lengthMeters;

        @Column(name = "capacity_units")
        private Integer capacityUnits;

        @Column(name = "designed_capacity")
        private Integer designedCapacity;

        @Column(name = "current_utilization")
        private Integer currentUtilization;

        @Column(name = "can_extend")
        private Boolean canExtend = false;

        @Temporal(TemporalType.DATE)
        @Column(name = "last_maintenance_date")
        private Date lastMaintenanceDate;

        @Temporal(TemporalType.DATE)
        @Column(name = "commissioning_date")
        private Date commissioningDate;

        @Column(name = "electrified")
        private Boolean electrified = false;

        @Size(max = 250)
        @Column(name = "remarks", length = 250)
        private String remarks;

        @Column(name = "valid_flag")
        private Boolean validFlag = true;

        @Size(max = 50)
        @Column(name = "created_by", length = 50)
        private String createdBy;

        @Size(max = 50)
        @Column(name = "updated_by", length = 50)
        private String updatedBy;

        @Column(name = "created_at")
        private Date createdAt;

        @Column(name = "updated_at")
        private Date updatedAt;

        @Size(max = 20)
        @Column(name = "device_type", length = 20)
        private String deviceType;

        @NotNull
        @Size(max = 10)
        @Column(name = "master_line_code", nullable = false, length = 10)
        private String masterLineCode;

        @OneToMany(mappedBy = "masterInfraLine", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JsonManagedReference("master-infra-line")
        private List<InfraLine> infraLines = new ArrayList<>();
}
