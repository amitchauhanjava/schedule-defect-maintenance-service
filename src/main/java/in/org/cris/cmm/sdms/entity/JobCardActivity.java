package in.org.cris.cmm.sdms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "job_card_activity", schema = "sdms")
public class JobCardActivity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "activity_id")
        private Long activityId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "job_card_id", nullable = false)
        private MaintenanceJobCard jobCard;

        @Column(name = "activity_type", nullable = false)
        private String activityType;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "maintenance_id")
        private MaintenanceDetails maintenanceDetails;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "task_m_id")
        private MaintenanceTaskListMaster taskMaster;

        @Column(name = "section_id")
        private Long sectionId;

        @Column(name = "asset_dk")
        private Long assetDk;

        @Column(name = "assembly_number")
        private String assemblyNumber;

        @Column(name = "activity_desc")
        private String activityDesc;

        @Column(name = "assigned_to")
        private String assignedTo;

        @Column(name = "start_time")
        private Date startTime;

        @Column(name = "end_time")
        private Date endTime;

        @Column(name = "status")
        private String status = "PENDING";

        @Column(name = "completion_remarks")
        private String completionRemarks;

        @Column(name = "created_by")
        private String createdBy;

        @Column(name = "updated_by")
        private String updatedBy;

        @Column(name = "created_at")
        private Date createdAt;

        @Column(name = "updated_at")
        private Date updatedAt;
}
