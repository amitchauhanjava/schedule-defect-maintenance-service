package in.org.cris.cmm.sdms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "master_rs_type_maintenance", schema = "lsin")
public class MasterRsTypeMaintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rs_type_maintenance_id")
    private Long rsTypeMaintenanceId;

    // Foreign key maintenance_type_id → lsin.master_maintenance_types
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maintenance_type_id")
    private MasterMaintenanceTypes maintenanceType;

    // Foreign key rs_type_id → rolling_stock.asset_type_master.dk
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rs_type_id")
    private AssetTypeMaster rsType;

    @Column(name = "first_frequency_in_days")
    private Integer firstFrequencyInDays;

    @Column(name = "first_frequency_in_kms")
    private Integer firstFrequencyInKms;

    @Column(name = "subsequent_frequency_in_days")
    private Integer subsequentFrequencyInDays;

    @Column(name = "subsequent_frequency_in_kms")
    private Integer subsequentFrequencyInKms;

    @Column(name = "description")
    private String description;

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
