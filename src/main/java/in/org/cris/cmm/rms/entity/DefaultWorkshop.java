package in.org.cris.cmm.rms.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(
        name = "default_workshops",
        schema = "lsin",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "default_workshops_org_code_rs_type_maintenance_id_workshop__key",
                        columnNames = {"org_code", "rs_type_maintenance_id", "workshop_org_code", "valid_flag"}
                )
        }
)
public class DefaultWorkshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "default_workshop_id")
    private Long defaultWorkshopId;

    @NotBlank(message = "Organization code is required")
    @Size(max = 20, message = "Organization code must not more than 20 characters")
    @Column(name = "org_code", length = 20, nullable = false)
    private String orgCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_type_maintenance_id", referencedColumnName = "rs_type_maintenance_id", foreignKey = @ForeignKey(name = "default_workshops_rs_type_maintenance_id_fkey"))
    private MasterRsTypeMaintenance masterRsTypeMaintenance;

    @NotBlank(message = "Workshop organization code is required")
    @Size(max = 20, message = "Workshop organization code must not more than 20 characters")
    @Column(name = "workshop_org_code", length = 20, nullable = false)
    private String workshopOrgCode;

    @NotNull(message = "Valid flag cannot be null")
    @Column(name = "valid_flag")
    private Boolean validFlag = true;

    @Size(max = 20, message = "Device type must not more than 20 characters")
    @Column(name = "device_type", length = 20)
    private String deviceType;

    @Size(max = 50, message = "Created by must not more than 50 characters")
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Size(max = 50, message = "Updated by must not more than 50 characters")
    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;
}
