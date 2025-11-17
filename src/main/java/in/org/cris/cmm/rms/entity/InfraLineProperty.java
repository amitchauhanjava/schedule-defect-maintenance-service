package in.org.cris.cmm.rms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "infra_line_properties", schema = "lsin",
        uniqueConstraints = @UniqueConstraint(columnNames = {"line_id", "property_id"}))
public class InfraLineProperty {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "line_id", nullable = false)
        private Long lineId;

        @Column(name = "property_id", nullable = false)
        private Long propertyId;

        @Column(name = "property_value")
        private String propertyValue;

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

        @Column(name = "device_type")
        private String deviceType;

        @Column(name = "valid_flag")
        private Boolean validFlag;
}
