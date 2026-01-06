package in.org.cris.cmm.sdms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "maintenance_materials_txn", schema = "sdms")
@Getter
@Setter
public class MaintenanceMaterialsTxn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "material_txn_id")
	private Long materialTxnId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maintenance_id")
	private MaintenanceDetails maintenanceDetails;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "defect_txn_id")
	private MaintenanceDefectTxn defect;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "must_change_id")
	private MustChangeMaterialAssemblyMaster mustChange;

	@Column(name = "material_name")
	private String materialName;

	@Column(name = "description")
	private String description;

	@Column(name = "quantity_used")
	private Double quantityUsed;

	@Column(name = "material_cost")
	private Double materialCost;

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

