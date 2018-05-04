package plast.org.ua.upu.table;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "hurtokupu")
public class HurtokUPU {
	private Long id;
	private String nameHurtok;
	private Date dateRegister;
	private Date dateEnd;
	private Integer number;
	private Blob vidznakaHurtka;
	private KurinUPU kurinUPU;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "namehurtok")
	public String getNameHurtok() {
		return nameHurtok;
	}
	public void setNameHurtok(String nameHurtok) {
		this.nameHurtok = nameHurtok;
	}
	@Column(name = "dateregister")
	@Temporal(value = TemporalType.DATE)
	public Date getDateRegister() {
		return dateRegister;
	}
	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}
	@Column(name = "dateend")
	@Temporal(value = TemporalType.DATE)
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	@Column(name = "number")
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Lob
	@Column(name = "vidznaka", columnDefinition = "mediumblob")
	public Blob getVidznakaHurtka() {
		return vidznakaHurtka;
	}
	public void setVidznakaHurtka(Blob vidznakaHurtka) {
		this.vidznakaHurtka = vidznakaHurtka;
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "kurinupuid")
	public KurinUPU getKurinUPU() {
		return kurinUPU;
	}
	public void setKurinUPU(KurinUPU kurinUPU) {
		this.kurinUPU = kurinUPU;
	}
	public HurtokUPU(Long id, String nameHurtok, Date dateRegister, Date dateEnd, Integer number, Blob vidznakaHurtka,
			KurinUPU kurinUPU) {
		super();
		this.id = id;
		this.nameHurtok = nameHurtok;
		this.dateRegister = dateRegister;
		this.dateEnd = dateEnd;
		this.number = number;
		this.vidznakaHurtka = vidznakaHurtka;
		this.kurinUPU = kurinUPU;
	}
	public HurtokUPU() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
