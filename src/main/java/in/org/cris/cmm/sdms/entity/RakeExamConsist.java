package in.org.cris.cmm.sdms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "rake_exam_consist", schema = "rms")
//@Where(clause = "condition_status NOT IN ('SICK','DETACHED')")
public class RakeExamConsist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_consist_id")
    private Long examConsistId;

    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "asset_dk")
    private Long assetDk;

    @NotNull(message = "Rake ID is required")
    @Column(name = "rake_id")
    private Integer rakeId;

    @Column(name = "unit_id")
    private Integer unitId;

    @Column(name = "asset_no")
    private String assetNo;

    @NotNull(message = "Asset Type is mandatory")
    @Column(name = "asset_type_dk")
    private Long assetTypeDk;

    @Column(name = "owner_dk")
    private Long ownerDk;

    @Column(name = "rs_type_maintenance_id")
    private Long rsTypeMaintenanceId;

    @Column(name = "rs_type_maintenance_date")
    private Date rsTypeMaintenanceDate;

    @Column(name = "position")
    private Integer position;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "condition_status")
    private String conditionStatus;

    @Column(name = "condition_status_last_updated")
    private Date conditionStatusLastUpdated;

    @Column(name = "maintenance_id")
    private Long maintenanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "rs_type_maintenance_id",
            referencedColumnName = "rs_type_maintenance_id",
            insertable = false,
            updatable = false
    )
    private MasterRsTypeMaintenance rsTypeMaintenance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "maintenance_id",
            referencedColumnName = "maintenance_id",
            insertable = false,
            updatable = false
    )
    private MaintenanceDetails maintenance;

    @Column(name = "valid_flag")
    private Boolean validFlag;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

}
