package in.org.cris.cmm.sdms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "asset_type_master", schema = "rolling_stock")
public class AssetTypeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dk")
    private Long dk;

    @Column(name = "asset_category")
    private String assetCategory;

    @Column(name = "asset_type")
    private String assetType;

    @Column(name = "design_type")
    private String designType;

    @Column(name = "asset_sub_type")
    private String assetSubType;

    @Column(name = "traction")
    private String traction;

    @Column(name = "gauge_type")
    private String gaugeType;

    @Column(name = "codal_life_months")
    private Integer codalLifeMonths;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "valid_flag")
    private Boolean validFlag = true;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "asset_type_description")
    private String assetTypeDescription;
}
