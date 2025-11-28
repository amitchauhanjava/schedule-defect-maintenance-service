package in.org.cris.cmm.sdms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "maintenance_defects", schema = "sdms")
public class MaintenanceDefectTxn {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "defect_txn_id")
        private Long defectTxnId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "maintenance_id")
        private MaintenanceDetails maintenanceDetails;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "defect_id")
        private MaintenanceDefectMaster defect;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "action_id")
        private MaintenanceActionMaster action;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "checklist_txn_id")
        private MaintenanceChecklistTxn checklistTxn;

        @Column(name = "material_demand_id")
        private Long materialDemandId;

        @Column(name = "remarks")
        private String remarks;

        @Column(name = "status")
        private String status;

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
