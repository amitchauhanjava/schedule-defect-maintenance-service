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
        name = "workcenters",
        schema = "lsin",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"org_code", "workcenter_code"}),
                @UniqueConstraint(columnNames = {"workcenter_code"})
        }
)
public class Workcenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workcenter_id")
    private Long workcenterId;

    @NotNull(message = "Workcenter code is mandatory")
    @Size(max = 10, message = "Workcenter code cannot more than 10 characters")
    @Column(name = "workcenter_code", nullable = false, length = 10)
    private String workcenterCode;

    @NotNull(message = "Workcenter name is mandatory")
    @Size(max = 25, message = "Workcenter name cannot more than 25 characters")
    @Column(name = "workcenter_name", nullable = false, length = 25)
    private String workcenterName;

    @NotNull(message = "Org code is mandatory")
    @Size(max = 20, message = "Org code cannot more than 20 characters")
    @Column(name = "org_code", nullable = false, length = 20)
    private String orgCode;

    @Column(name = "section_id")
    private Long sectionId;

    @Size(max = 50, message = "Description cannot more than 50 characters")
    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "commissioning_date")
    @Temporal(TemporalType.DATE)
    private Date commissioningDate;

    @Size(max = 250, message = "Remarks cannot more than 250 characters")
    @Column(name = "remarks", length = 250)
    private String remarks;

    @Column(name = "valid_flag")
    private Boolean validFlag = true;

    @Size(max = 50, message = "Created by cannot more than 50 characters")
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Size(max = 50, message = "Updated by cannot more than 50 characters")
    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Size(max = 20, message = "Device type cannot more than 20 characters")
    @Column(name = "device_type", length = 20)
    private String deviceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", insertable = false, updatable = false)
    private Section section;
}
