package in.org.cris.cmm.sdms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "maintenance_details_hault", schema = "sdms")
public class MaintenanceHault {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "hault_id")
        private Long haultId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "maintenance_id")
        private MaintenanceDetails maintenanceDetails;

        @Column(name = "hault_start")
        @Temporal(TemporalType.TIMESTAMP)
        private Date haultStart;

        @Column(name = "hault_resume")
        @Temporal(TemporalType.TIMESTAMP)
        private Date haultResume;

        @Column(name = "remarks")
        private String remarks;

        @Column(name = "valid_flag")
        private Boolean validFlag = true;

        @Column(name = "created_by")
        private String createdBy;

        @Column(name = "updated_by")
        private String updatedBy;

        @Column(name = "created_at")
        private Date createdAt;

        @Column(name = "updated_at")
        private Date updatedAt;
}
