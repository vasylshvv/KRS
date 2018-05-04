package plast.org.ua.upu.table;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "reportreason")
public class ReportReason {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="kurinupuid")
	private KurinUPU kurinUPU;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="smhurtokid")
	private SamHurtokUPU samHurtokUPU;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="zvyazkovyyid")
	private Zvyazkovyy zvyazkovyy;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="vporyadnykid")
	private Vporyadnyk vporyadnyk;
	
	@Column(name = "description", length = 1000)
	private String description;
	
	@Column(name = "dateend")
	@Temporal(value = TemporalType.DATE)
	private Date datereason;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="reportsupuid")
	private QuarterlyReportsUPU reportsUPU;

	@Column(name = "approved", length = 1, columnDefinition = "int default 0", nullable = false)
	private Integer approved;
	
	public ReportReason() {	}

	public ReportReason(Long id, KurinUPU kurinUPU, SamHurtokUPU samHurtokUPU, Zvyazkovyy zvyazkovyy,
			Vporyadnyk vporyadnyk, String description, Date datereason, QuarterlyReportsUPU reportsUPU, Integer approved) {
		super();
		this.id = id;
		this.kurinUPU = kurinUPU;
		this.samHurtokUPU = samHurtokUPU;
		this.zvyazkovyy = zvyazkovyy;
		this.vporyadnyk = vporyadnyk;
		this.description = description;
		this.datereason = datereason;
		this.reportsUPU = reportsUPU;
		this.approved = approved;
	}

	public ReportReason(KurinUPU kurinUPU, SamHurtokUPU samHurtokUPU, Zvyazkovyy zvyazkovyy, Vporyadnyk vporyadnyk,
			String description, Date datereason, QuarterlyReportsUPU reportsUPU, Integer approved) {
		super();
		this.kurinUPU = kurinUPU;
		this.samHurtokUPU = samHurtokUPU;
		this.zvyazkovyy = zvyazkovyy;
		this.vporyadnyk = vporyadnyk;
		this.description = description;
		this.datereason = datereason;
		this.reportsUPU = reportsUPU;
		this.approved = approved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public KurinUPU getKurinUPU() {
		return kurinUPU;
	}

	public void setKurinUPU(KurinUPU kurinUPU) {
		this.kurinUPU = kurinUPU;
	}

	public SamHurtokUPU getSamHurtokUPU() {
		return samHurtokUPU;
	}

	public void setSamHurtokUPU(SamHurtokUPU samHurtokUPU) {
		this.samHurtokUPU = samHurtokUPU;
	}

	public Zvyazkovyy getZvyazkovyy() {
		return zvyazkovyy;
	}

	public void setZvyazkovyy(Zvyazkovyy zvyazkovyy) {
		this.zvyazkovyy = zvyazkovyy;
	}

	public Vporyadnyk getVporyadnyk() {
		return vporyadnyk;
	}

	public void setVporyadnyk(Vporyadnyk vporyadnyk) {
		this.vporyadnyk = vporyadnyk;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDatereason() {
		return datereason;
	}

	public void setDatereason(Date datereason) {
		this.datereason = datereason;
	}

	public QuarterlyReportsUPU getReportsUPU() {
		return reportsUPU;
	}

	public void setReportsUPU(QuarterlyReportsUPU reportsUPU) {
		this.reportsUPU = reportsUPU;
	}

	public Integer getApproved() {
		return approved;
	}

	public void setApproved(Integer approved) {
		this.approved = approved;
	}
	
	
}
