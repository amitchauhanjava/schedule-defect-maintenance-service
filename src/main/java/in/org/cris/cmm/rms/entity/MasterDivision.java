package in.org.cris.cmm.rms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "master_divisions", schema = "lsin")
@Getter
@Setter
public class MasterDivision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id")
    private Long divisionId;

    @Column(name = "division_code", unique = true)
    private String divisionCode;

    @Column(name = "division_name")
    private String divisionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", referencedColumnName = "zone_id")
    private MasterZone zone;

    @Column(name = "hq_city")
    private String hqCity;

    @Column(name = "hq_state")
    private String hqState;

    @Column(name = "established_date")
    private Date establishedDate;

    @Column(name = "region_covered")
    private String regionCovered;

    @Column(name = "total_stations")
    private Integer totalStations;

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
