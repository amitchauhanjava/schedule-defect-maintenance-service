package in.org.cris.cmm.rms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "master_zones", schema = "lsin")
@Getter
@Setter
public class MasterZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_id")
    private Integer zoneId;

    @Column(name = "zone_code", unique = true)
    private String zoneCode;

    @Column(name = "zone_name")
    private String zoneName;

    @Column(name = "hq_city")
    private String hqCity;

    @Column(name = "hq_state")
    private String hqState;

    @Column(name = "established_date")
    @Temporal(TemporalType.DATE)
    private Date establishedDate;

    @Column(name = "region_covered")
    private String regionCovered;

    @Column(name = "total_divisions")
    private Integer totalDivisions;

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
