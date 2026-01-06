package in.org.cris.cmm.sdms.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "maintenance_manpower_txn", schema = "sdms")
@Getter
@Setter
public class MaintenanceManpowerTxn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manpower_txn_id")
	private Long manpowerTxnId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maintenance_id")
	private MaintenanceDetails maintenanceDetails;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "defect_txn_id")
	private MaintenanceDefectTxn defect;

	@Column(name = "category")
	private String category;

	@Column(name = "manpower_name")
	private String manpowerName;

	@Column(name = "designation")
	private String designation;

	@Column(name = "hours_spent")
	private String hoursSpent;

	@Column(name = "manpower_cost")
	private Double manpowerCost;

	@Column(name = "valid_flag")
	private Boolean validFlag;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;
}

