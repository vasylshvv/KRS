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
@Table(name = "zvyazkovvy")
public class Zvyazkovyy {
	private Long id;
	private Date datebegin;
	private Date dateend;
	private KurinUPU kurinupu;
	private Persons persons;
	private String hashcode;
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
	@JoinColumn(name="kurinupuid")
	public KurinUPU getKurinupu() {
		return kurinupu;
	}
	public void setKurinupu(KurinUPU kurinupu) {
		this.kurinupu = kurinupu;
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="personsid")
	public Persons getPersons() {
		return persons;
	}
	public void setPersons(Persons persons) {
		this.persons = persons;
	}
	@Column(name = "hashcode")
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	
	public Zvyazkovyy(Long id, Date datebegin, Date dateend, KurinUPU kurinupu, Persons persons, String hashcode) {
		super();
		this.id = id;
		this.datebegin = datebegin;
		this.dateend = dateend;
		this.kurinupu = kurinupu;
		this.persons = persons;
		this.hashcode = hashcode;
	}
	public Zvyazkovyy() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
