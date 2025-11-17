package in.org.cris.cmm.rms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "asset_type_master", schema = "rolling_stock")
@Getter
@Setter
public class AssetTypeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dk;

    @Column(name = "asset_category")
    private String assetCategory;

    @Column(name = "asset_type")
    private String assetType;

    @Column(name = "design_type")
    private String designType;

    @Column(name = "asset_sub_type")
    private String assetSubType;

    private String traction;

    @Column(name = "gauge_type", length = 2)
    private String gaugeType;

    @Column(name = "codal_life_months")
    private Short codalLifeMonths;

    private String remarks;

    @Column(name = "valid_flag")
    private Boolean validFlag;

    private Date createdAt;
    private Date updatedAt;
    private String createdBy;
    private String updatedBy;
}
