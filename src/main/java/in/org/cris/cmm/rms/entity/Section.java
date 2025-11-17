package in.org.cris.cmm.rms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(
        name = "sections",
        schema = "lsin",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"org_code", "section_code"}),
                @UniqueConstraint(columnNames = {"section_code"})
        }
)
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long sectionId;

    @NotBlank(message = "Section code is mandatory")
    @Size(max = 10, message = "Section code cannot more than 10 characters")
    @Column(name = "section_code", nullable = false, length = 10)
    private String sectionCode;

    @NotBlank(message = "Section name is mandatory")
    @Size(max = 25, message = "Section name cannot more than 25 characters")
    @Column(name = "section_name", nullable = false, length = 25)
    private String sectionName;

    @NotBlank(message = "Org code is mandatory")
    @Size(max = 20, message = "Org code cannot more than 20 characters")
    @Column(name = "org_code", nullable = false, length = 20)
    private String orgCode;

    @Size(max = 50, message = "Description cannot more than 50 characters")
    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "commissioning_date")
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

    @Column(name = "created_at", updatable = false)
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt = new Date();

    @Size(max = 20, message = "Device type cannot more than 20 characters")
    @Column(name = "device_type", length = 20)
    private String deviceType;
}

