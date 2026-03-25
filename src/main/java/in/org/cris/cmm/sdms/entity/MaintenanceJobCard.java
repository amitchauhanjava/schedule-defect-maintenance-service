package in.org.cris.cmm.sdms.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "maintenance_job_card", schema = "sdms")
public class MaintenanceJobCard {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "job_card_id")
        private Long jobCardId;

        @Column(name = "org_code")
        private String orgCode;

        @Column(name = "job_no")
        private String jobNo;

        /*@Column(name = "rake_id")
        private Long rakeId;*/

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "rake_id", referencedColumnName = "id")
        private Rake rake;

        @Column(name = "start_time")
        @Temporal(TemporalType.TIMESTAMP)
        private Date startTime;

        @Column(name = "end_time")
        private Date endTime;

        @Column(name = "status")
        private String status;

        @Column(name = "valid_flag")
        private Boolean validFlag = true;

        @Transient
        private Long totalActivity;

        @Transient
        private Long pendingActivity;

        @Transient
        private List<Long> section;

        @Column(name = "created_by")
        private String createdBy;

        @Column(name = "updated_by")
        private String updatedBy;

        @Column(name = "created_at")
        private Date createdAt;

        @Column(name = "updated_at")
        private Date updatedAt;
}
