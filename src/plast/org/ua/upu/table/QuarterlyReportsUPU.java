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
@Table(name="quarterlyreportsupu")
public class QuarterlyReportsUPU {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "datebegin")
	@Temporal(value = TemporalType.DATE)
	private Date datebegin;
	
	@Column(name = "dateend")
	@Temporal(value = TemporalType.DATE)	
	private Date dateend;
	
	@Column(name = "dateapprkurin")
	@Temporal(value = TemporalType.DATE)
	private Date dateApproveKurin;
	
	@Column(name = "dateapprzvyaz")
	@Temporal(value = TemporalType.DATE)
	private Date dateApproveZvyazkovyy;
	
	@Column(name = "approved", length = 1, columnDefinition = "int default 0", nullable = false)
	private Integer approved;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="zvyazkovyyid")
	private Zvyazkovyy zvyazkovyy;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="kurinupuid")
	private KurinUPU kurinUPU;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="samhurtokupuid")
	private SamHurtokUPU samHurtokUPU;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="vporyadnyksamhurtid")
	private Vporyadnyk vporyadnyksamhurtid;
	
	@Column(name = "need", length = 1000)
	private String need;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatebegin() {
		return datebegin;
	}

	public void setDatebegin(Date datebegin) {
		this.datebegin = datebegin;
	}

	public Date getDateend() {
		return dateend;
	}

	public void setDateend(Date dateend) {
		this.dateend = dateend;
	}

	public Date getDateApproveKurin() {
		return dateApproveKurin;
	}

	public void setDateApproveKurin(Date dateApproveKurin) {
		this.dateApproveKurin = dateApproveKurin;
	}

	public Date getDateApproveZvyazkovyy() {
		return dateApproveZvyazkovyy;
	}

	public void setDateApproveZvyazkovyy(Date dateApproveZvyazkovyy) {
		this.dateApproveZvyazkovyy = dateApproveZvyazkovyy;
	}

	public Integer getApproved() {
		return approved;
	}

	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	public Zvyazkovyy getZvyazkovyy() {
		return zvyazkovyy;
	}

	public void setZvyazkovyy(Zvyazkovyy zvyazkovyy) {
		this.zvyazkovyy = zvyazkovyy;
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

	public Vporyadnyk getVporyadnyksamhurtid() {
		return vporyadnyksamhurtid;
	}

	public void setVporyadnyksamhurtid(Vporyadnyk vporyadnyksamhurtid) {
		this.vporyadnyksamhurtid = vporyadnyksamhurtid;
	}


	public String getNeed() {
		return need;
	}

	public void setNeed(String need) {
		this.need = need;
	}

	public QuarterlyReportsUPU(Date datebegin, Date dateend, Date dateApproveKurin, Date dateApproveZvyazkovyy,
			Integer approved, Zvyazkovyy zvyazkovyy, KurinUPU kurinUPU, SamHurtokUPU samHurtokUPU,
			Vporyadnyk vporyadnyksamhurtid, String need) {
		super();
		this.datebegin = datebegin;
		this.dateend = dateend;
		this.dateApproveKurin = dateApproveKurin;
		this.dateApproveZvyazkovyy = dateApproveZvyazkovyy;
		this.approved = approved;
		this.zvyazkovyy = zvyazkovyy;
		this.kurinUPU = kurinUPU;
		this.samHurtokUPU = samHurtokUPU;
		this.vporyadnyksamhurtid = vporyadnyksamhurtid;
		this.need = need;
	}

	public QuarterlyReportsUPU(Long id, Date datebegin, Date dateend, Date dateApproveKurin, Date dateApproveZvyazkovyy,
			Integer approved, Zvyazkovyy zvyazkovyy, KurinUPU kurinUPU, SamHurtokUPU samHurtokUPU,
			Vporyadnyk vporyadnyksamhurtid, String need) {
		super();
		this.id = id;
		this.datebegin = datebegin;
		this.dateend = dateend;
		this.dateApproveKurin = dateApproveKurin;
		this.dateApproveZvyazkovyy = dateApproveZvyazkovyy;
		this.approved = approved;
		this.zvyazkovyy = zvyazkovyy;
		this.kurinUPU = kurinUPU;
		this.samHurtokUPU = samHurtokUPU;
		this.vporyadnyksamhurtid = vporyadnyksamhurtid;
		this.need = need;
	}

	public QuarterlyReportsUPU() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
