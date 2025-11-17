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
        name = "master_infra_properties",
        schema = "lsin",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"property_code"})
        }
)
public class MasterInfraProperty {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "property_id")
        private Long propertyId;

        @NotNull(message = "Property code is mandatory")
        @Size(max = 10, message = "Property code cannot exceed 10 characters")
        @Column(name = "property_code", nullable = false, length = 10)
        private String propertyCode;

        @NotNull(message = "Property name is mandatory")
        @Size(max = 25, message = "Property name cannot exceed 25 characters")
        @Column(name = "property_name", nullable = false, length = 25)
        private String propertyName;

        @Size(max = 250, message = "Description cannot exceed 250 characters")
        @Column(name = "description", length = 250)
        private String description;

        @Size(max = 20, message = "UOM cannot exceed 20 characters")
        @Column(name = "uom", length = 20)
        private String uom;

        @Column(name = "value_type", length = 20)
        private String valueType;

        @Column(name = "min_value")
        private Double minValue;

        @Column(name = "max_value")
        private Double maxValue;

        @Size(max = 50, message = "Default value cannot exceed 50 characters")
        @Column(name = "default_value", length = 50)
        private String defaultValue;

        @Size(max = 500, message = "List values cannot exceed 500 characters")
        @Column(name = "list_values", length = 500)
        private String listValues;

        @Column(name = "param_seq")
        private Integer paramSeq;

        @Column(name = "valid_flag")
        private Boolean validFlag = true;

        @Size(max = 50, message = "Created by cannot exceed 50 characters")
        @Column(name = "created_by", length = 50)
        private String createdBy;

        @Size(max = 50, message = "Updated by cannot exceed 50 characters")
        @Column(name = "updated_by", length = 50)
        private String updatedBy;

        @Column(name = "created_at")
        private Date createdAt;

        @Column(name = "updated_at")
        private Date updatedAt;
}
