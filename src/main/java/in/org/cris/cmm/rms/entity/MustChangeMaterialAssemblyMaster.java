package in.org.cris.cmm.rms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "maintenance_must_change_material_assembly_m", schema = "mucare")
public class MustChangeMaterialAssemblyMaster {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "must_change_id")
        private Long mustChangeId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "rs_type_maintenance_id")
        private MasterRsTypeMaintenance rsTypeMaintenance;

        @Column(name = "assembly_name")
        private String assemblyName;

        @Column(name = "part_no")
        private String partNo;

        @Column(name = "description")
        private String description;

        @Column(name = "frequency_days")
        private Integer frequencyDays;

        @Column(name = "frequency_kms")
        private Integer frequencyKms;

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
