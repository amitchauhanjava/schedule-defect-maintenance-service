package in.org.cris.cmm.rms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "maintenance_defect_m", schema = "sdms")
public class MaintenanceDefectMaster {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "defect_id")
        private Long defectId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "rs_type_maintenance_id")
        private MasterRsTypeMaintenance rsTypeMaintenance;

        @Column(name = "defect_code")
        private String defectCode;

        @Column(name = "defect_description")
        private String defectDescription;

        @Column(name = "severity")
        private String severity;

        @Column(name = "valid_flag")
        private Boolean validFlag = true;

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
}
