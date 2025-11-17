package in.org.cris.cmm.rms.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(
        name = "infra_line_properties",
        schema = "lsin",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "infra_line_properties_line_id_property_id_key",
                        columnNames = {"line_id", "property_id"}
                )
        }
)
public class InfraLineProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Line details are required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "line_id", referencedColumnName = "line_id", nullable = false)
    @JsonBackReference("infra-line-properties")
    private InfraLine infraLine;

    @NotNull(message = "Property details are required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "property_id", referencedColumnName = "property_id", nullable = false)
    private MasterInfraProperty masterInfraProperty;

    @Size(max = 150)
    @Column(name = "property_value", length = 150)
    private String propertyValue;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Size(max = 50)
    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt = new Date();

    @Size(max = 20)
    @Column(name = "device_type", length = 20)
    private String deviceType;

    @NotNull
    @Column(name = "valid_flag")
    private Boolean validFlag = true;
}
