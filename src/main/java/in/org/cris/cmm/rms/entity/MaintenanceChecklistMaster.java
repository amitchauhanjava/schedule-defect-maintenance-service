package in.org.cris.cmm.rms.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
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

        @Column(name = "sl_no")
        private Integer slNo;

        @Column(name = "assembly")
        private String assembly;

        @Column(name = "sub_assembly")
        private String subAssembly;

        @Column(name = "item_no")
        private Integer itemNo;

        @Column(name = "checklist_item")
        private String checklistItem;

        @Column(name = "coach_kind")
        private String coachKind;

        @Column(name = "utility_type")
        private String utilityType;

        @Column(name = "mand_condition")
        private Boolean mandCondition = true;

        @Column(name = "method")
        private String method;

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

