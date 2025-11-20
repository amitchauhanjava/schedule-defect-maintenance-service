package in.org.cris.cmm.sdms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "maintenance_task_list_m", schema = "sdms")
public class MaintenanceTaskListMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    // Fetch object instead of ID
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rs_type_maintenance_id")
    private MasterRsTypeMaintenance rsTypeMaintenance;

    @Column(name = "task_code")
    private String taskCode;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "skill_required")
    private String skillRequired;

    @Column(name = "estimated_time_minutes")
    private Integer estimatedTimeMinutes;

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
