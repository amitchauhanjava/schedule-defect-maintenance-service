package in.org.cris.cmm.sdms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "maintenance_parameters_m", schema = "sdms")
public class MaintenanceParametersMaster {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "parameter_id")
        private Long parameterId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "rs_type_maintenance_id")
        private MasterRsTypeMaintenance rsTypeMaintenance;

        @Column(name = "parameter_name")
        private String parameterName;

        @Column(name = "uom")
        private String uom;

        @Column(name = "min_value")
        private Double minValue;

        @Column(name = "max_value")
        private Double maxValue;

        @Column(name = "valid_flag")
        private Boolean validFlag = true;

        @Column(name = "created_by")
        private String createdBy;

        @Column(name = "updated_by")
        private String updatedBy;

        @Column(name = "created_at")
        @Temporal(TemporalType.TIMESTAMP)
        private Date createdAt;

        @Column(name = "updated_at")
        @Temporal(TemporalType.TIMESTAMP)
        private Date updatedAt;
}
