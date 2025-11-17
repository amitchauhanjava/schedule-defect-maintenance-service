package in.org.cris.cmm.rms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "maintenance_units", schema = "lsin")
public class MaintenanceUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mu_id")
    private Long muId;

    @Column(name = "mu_code", unique = true)
    private String muCode;

    @Column(name = "mu_name")
    private String muName;

    @Column(name = "division_id")
    private Integer divisionId;

    @Column(name = "parent_mu_id")
    private Integer parentMuId;

    @Column(name = "location_city")
    private String locationCity;

    @Column(name = "location_state")
    private String locationState;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "commissioning_date")
    private Date commissioningDate;

    @Column(name = "designed_capacity")
    private Integer designedCapacity;

    @Column(name = "current_utilization")
    private Integer currentUtilization;

    @Column(name = "last_maintenance_date")
    private Date lastMaintenanceDate;

    @Column(name = "area_sq_meters")
    private Integer areaSqMeters;

    @Column(name = "nearest_station_code")
    private String nearestStationCode;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "status")
    private String status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
