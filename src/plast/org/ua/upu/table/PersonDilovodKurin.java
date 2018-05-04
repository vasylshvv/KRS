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
@Table(name = "persondilovodkurin")
public class PersonDilovodKurin {
	private Long id;
	private Date datebegin;
	private Date dateend;
	private Persons persons;
	private KurinUPU kurin;
	private DilovedennyaKurinUPU dilovodkurin;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Temporal(value = TemporalType.DATE)
	@Column(name = "datebegin")
	public Date getDatebegin() {
		return datebegin;
	}
	public void setDatebegin(Date datebegin) {
		this.datebegin = datebegin;
	}
	@Temporal(value = TemporalType.DATE)
	@Column(name = "dateend")
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
	@JoinColumn(name="kurinid")
	public KurinUPU getKurin() {
		return kurin;
	}
	public void setKurin(KurinUPU kurin) {
		this.kurin = kurin;
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="dilovodkurinid")
	public DilovedennyaKurinUPU getDilovodkurin() {
		return dilovodkurin;
	}
	public void setDilovodkurin(DilovedennyaKurinUPU dilovodkurin) {
		this.dilovodkurin = dilovodkurin;
	}
	public PersonDilovodKurin(Long id, Date datebegin, Date dateend, Persons persons, KurinUPU kurin,
			DilovedennyaKurinUPU dilovodkurin) {
		super();
		this.id = id;
		this.datebegin = datebegin;
		this.dateend = dateend;
		this.persons = persons;
		this.kurin = kurin;
		this.dilovodkurin = dilovodkurin;
	}
	public PersonDilovodKurin() {
		super();
		// TODO Auto-generated constructor stub
	}
}
