package in.org.cris.cmm.sdms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "master_maintenance_types", schema = "lsin")
public class MasterMaintenanceTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_type_id")
    private Long maintenanceTypeId;

    @Column(name = "maintenance_type_code")
    private String maintenanceTypeCode;

    @Column(name = "maintenance_type_name")
    private String maintenanceTypeName;

    @Column(name = "description")
    private String description;

    @Column(name = "valid_flag")
    private Boolean validFlag = true;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}
