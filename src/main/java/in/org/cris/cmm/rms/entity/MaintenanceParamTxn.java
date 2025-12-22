package in.org.cris.cmm.rms.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "maintenance_params", schema = "sdms")
public class MaintenanceParamTxn {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "param_txn_id")
        private Long paramTxnId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "maintenance_id")
        private MaintenanceDetails maintenanceDetails;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "parameter_id")
        private MaintenanceParametersMaster parameterMaster;

        @Column(name = "pre_value")
        private Double preValue;

        @Column(name = "post_value")
        private Double postValue;

        @Column(name = "remarks")
        private String remarks;

        @Column(name = "valid_flag")
        private Boolean validFlag = true;

        @Column(name = "created_by")
        private String createdBy;

        @Column(name = "updated_by")
        private String updatedBy;

        @Column(name = "created_at")
        private Date createdAt;

        @Column(name = "updated_at")
        private Date updatedAt;
}
