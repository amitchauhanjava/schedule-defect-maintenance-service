package in.org.cris.cmm.rms.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "master_line_types", schema = "lsin")
public class MasterLineType {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "line_type_id")
        private Long lineTypeId;

        @Column(name = "line_type_code")
        private String lineTypeCode;

        @Column(name = "line_type_name")
        private String lineTypeName;

        @Column(name = "description")
        private String description;

        @Column(name = "valid_flag")
        private Boolean validFlag;

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
