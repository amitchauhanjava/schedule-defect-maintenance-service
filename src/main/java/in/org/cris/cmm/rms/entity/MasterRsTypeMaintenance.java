package in.org.cris.cmm.rms.entity;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maintenance_type_id", referencedColumnName = "maintenance_type_id", nullable = false)
    private MasterMaintenanceType maintenanceType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rs_type_id", referencedColumnName = "dk", nullable = false)
    private AssetTypeMaster rsType;

    @Column(name = "first_frequency_in_days")
    private Integer firstFrequencyInDays;

    @Column(name = "first_frequency_in_kms")
    private Integer firstFrequencyInKms;

    @Column(name = "subsequent_frequency_in_days")
    private Integer subsequentFrequencyInDays;

    @Column(name = "subsequent_frequency_in_kms")
    private Integer subsequentFrequencyInKms;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "valid_flag")
    private Boolean validFlag = true;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
