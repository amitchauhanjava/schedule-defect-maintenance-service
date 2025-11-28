package in.org.cris.cmm.sdms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "maintenance_details", schema = "sdms")
public class MaintenanceDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "maintenance_id")
        private Long maintenanceId;

        @Column(name = "asset_id")
        private Long assetId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "rs_type_maintenance_id")
        private MasterRsTypeMaintenance rsTypeMaintenance;

        @Column(name = "maintenance_type")
        private String maintenanceType;

        @Column(name = "start_date")
        @Temporal(TemporalType.TIMESTAMP)
        private Date startDate;

        @Column(name = "end_date")
        @Temporal(TemporalType.TIMESTAMP)
        private Date endDate;

        @Column(name = "location_id")
        private Long locationId;

        @Column(name = "work_order_no")
        private String workOrderNo;

        @Column(name = "status")
        private String status;

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
