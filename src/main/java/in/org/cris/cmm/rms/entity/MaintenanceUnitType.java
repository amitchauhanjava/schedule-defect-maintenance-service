package in.org.cris.cmm.rms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "maintenance_unit_types", schema = "lsin")
public class MaintenanceUnitType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "org_code", nullable = false)
    private String orgCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rs_type_maintenance_id", nullable = false)
    private MasterRsTypeMaintenance masterRsTypeMaintenance;

    @Column(name = "description")
    private String description;

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

    @Column(name = "valid_flag")
    private Boolean validFlag = true;
}