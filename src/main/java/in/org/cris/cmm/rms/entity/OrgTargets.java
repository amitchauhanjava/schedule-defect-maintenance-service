package in.org.cris.cmm.rms.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(
        name = "org_targets",
        schema = "lsin",
        uniqueConstraints = @UniqueConstraint(columnNames = {"org_code", "rs_type_maintenance_id", "target_type"})
)
public class OrgTargets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "target_id")
    private Long targetId;

    @NotBlank(message = "Org code is required")
    @Size(max = 20, message = "Org code must not exceed 20 characters")
    @Column(name = "org_code", nullable = false, length = 20)
    private String orgCode;

    @NotBlank(message = "Target type is required")
    @Size(max = 50, message = "Target type must not exceed 50 characters")
    @Column(name = "target_type", nullable = false, length = 50)
    private String targetType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_type_maintenance_id", referencedColumnName = "rs_type_maintenance_id")
    private MasterRsTypeMaintenance masterRsTypeMaintenance;

    @NotNull(message = "Target value is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Target value must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Target value must be a valid decimal with up to 2 decimal places")
    @Column(name = "target_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal targetValue;

    @Column(name = "target_month", length = 7)
    private String targetMonth;

    @Min(value = 1900, message = "Target year must be greater than or equal to 1900")
    @Max(value = 2100, message = "Target year must be less than or equal to 2100")
    @Column(name = "target_year")
    private Integer targetYear;

    @DecimalMin(value = "0.0", inclusive = true, message = "Achieved value must be 0 or greater")
    @Digits(integer = 10, fraction = 2, message = "Achieved value must be a valid decimal with up to 2 decimal places")
    @Column(name = "achieved_value", precision = 10, scale = 2)
    private BigDecimal achievedValue;

    @Size(max = 1000, message = "Remarks must not exceed 1000 characters")
    @Column(name = "remarks", columnDefinition = "text")
    private String remarks;

    @Column(name = "valid_flag")
    private Boolean validFlag = true;

    @Size(max = 20, message = "Device type must not exceed 20 characters")
    @Column(name = "device_type", length = 20)
    private String deviceType;

    @Size(max = 50, message = "Created by must not exceed 50 characters")
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Size(max = 50, message = "Updated by must not exceed 50 characters")
    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}
