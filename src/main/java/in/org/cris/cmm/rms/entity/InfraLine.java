package in.org.cris.cmm.rms.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "infra_lines", schema = "lsin")
public class InfraLine {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "line_id")
        private Long lineId;

        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "line_type_id", nullable = false)
        private MasterLineType lineType;

        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "master_line_id", nullable = false)
        @JsonBackReference("master-infra-line")
        private MasterInfraLine masterInfraLine;

        @Column(name = "from_length_meters")
        private Long fromLengthMeters;

        @Column(name = "to_length_meters")
        private Long toLengthMeters;

        @Column(name = "valid_flag")
        private Boolean validFlag = true;

        @Size(max = 50)
        @Column(name = "created_by", length = 50)
        private String createdBy;

        @Size(max = 50)
        @Column(name = "updated_by", length = 50)
        private String updatedBy;

        @Column(name = "created_at")
        private Date createdAt = new Date();

        @Column(name = "updated_at")
        private Date updatedAt = new Date();

        @Size(max = 20)
        @Column(name = "device_type", length = 20)
        private String deviceType;

        @OneToMany(mappedBy = "infraLine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonManagedReference("infra-line-properties")
        private List<InfraLineProperties> infraLineProperties = new ArrayList<>();
}
