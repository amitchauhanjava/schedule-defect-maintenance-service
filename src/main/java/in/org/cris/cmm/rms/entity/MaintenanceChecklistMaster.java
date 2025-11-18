package in.org.cris.cmm.rms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "maintenance_checklist_m", schema = "sdms")
public class MaintenanceChecklistMaster {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "checklist_id")
        private Long checklistId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "rs_type_maintenance_id")
        private MasterRsTypeMaintenance rsTypeMaintenance;

        @Column(name = "safety_flag")
        private Boolean safetyFlag = false;

        @Column(name = "item_no")
        private Integer itemNo;

        @Column(name = "checklist_item")
        private String checklistItem;

        @Column(name = "method")
        private String method;

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
