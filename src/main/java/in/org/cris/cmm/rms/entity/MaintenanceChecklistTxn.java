package in.org.cris.cmm.rms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "maintenance_checklist", schema = "sdms")
public class MaintenanceChecklistTxn {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "checklist_txn_id")
        private Long checklistTxnId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "maintenance_id")
        private MaintenanceDetails maintenanceDetails;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "checklist_id")
        private MaintenanceChecklistMaster checklistMaster;

        @Column(name = "pre_value")
        private String preValue;

        @Column(name = "post_value")
        private String postValue;

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
