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
@Table(name = "persondilovodhurtok")
public class PersonDilovodHurtok {
	private Long id;
	private Date datebegin;
	private Date dateend;
	private Persons persons;
	private HurtokUPU hurtok;
	private KurinUPU kurin;
	private SamHurtokUPU smhurtok;
	private DilovedennyaHurtokUPU dilovodhurtok;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "datebegin")
	@Temporal(value = TemporalType.DATE)
	public Date getDatebegin() {
		return datebegin;
	}
	public void setDatebegin(Date datebegin) {
		this.datebegin = datebegin;
	}
	@Column(name = "dateend")
	@Temporal(value = TemporalType.DATE)
	public Date getDateend() {
		return dateend;
	}
	public void setDateend(Date dateend) {
		this.dateend = dateend;
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="personsid")
	public Persons getPersons() {
		return persons;
	}
	public void setPersons(Persons persons) {
		this.persons = persons;
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="hurtokid")
	public HurtokUPU getHurtok() {
		return hurtok;
	}
	public void setHurtok(HurtokUPU hurtok) {
		this.hurtok = hurtok;
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="kurinid")
	public KurinUPU getKurin() {
		return kurin;
	}
	public void setKurin(KurinUPU kurin) {
		this.kurin = kurin;
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="dilovodhurtokid")
	public DilovedennyaHurtokUPU getDilovodhurtok() {
		return dilovodhurtok;
	}
	public void setDilovodhurtok(DilovedennyaHurtokUPU dilovodhurtok) {
		this.dilovodhurtok = dilovodhurtok;
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="smhurtokid")
	public SamHurtokUPU getSmhurtok() {
		return smhurtok;
	}
	public void setSmhurtok(SamHurtokUPU smhurtok) {
		this.smhurtok = smhurtok;
	}
	public PersonDilovodHurtok(Long id, Date datebegin, Date dateend, Persons persons, HurtokUPU hurtok, KurinUPU kurin,
			DilovedennyaHurtokUPU dilovodhurtok) {
		super();
		this.id = id;
		this.datebegin = datebegin;
		this.dateend = dateend;
		this.persons = persons;
		this.hurtok = hurtok;
		this.kurin = kurin;
		this.dilovodhurtok = dilovodhurtok;
	}
	
	public PersonDilovodHurtok(Long id, Date datebegin, Date dateend, Persons persons, SamHurtokUPU smhurtok,
			DilovedennyaHurtokUPU dilovodhurtok) {
		super();
		this.id = id;
		this.datebegin = datebegin;
		this.dateend = dateend;
		this.persons = persons;
		this.smhurtok = smhurtok;
		this.dilovodhurtok = dilovodhurtok;
	}
	public PersonDilovodHurtok() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
