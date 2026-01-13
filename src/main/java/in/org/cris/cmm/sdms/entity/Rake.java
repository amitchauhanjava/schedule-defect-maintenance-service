package in.org.cris.cmm.sdms.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rakes", schema = "rms")
@Getter
@Setter
public class Rake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rake_name")
    private String rakeName;

    @Column(name = "org_code")
    private String orgCode;

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_type_id", referencedColumnName = "id")
    private UnitType unitType;*/

    private String remarks;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    private String status;

    @Column(name = "length")
    private Integer length;

    // Relation to rake_consists
   /* @OneToMany(mappedBy = "rake", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RakeConsist> rakeConsists;*/

}
