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
@Table(name = "personsvyshkil")
public class PersonsVyshkil {
	private Long id;
	private Date datefrom;
	private Date dateto;
	private Persons persons;
	private Vyshkil vyshkil;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY	)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "datefrom")
	@Temporal(value = TemporalType.DATE)
	public Date getDatefrom() {
		return datefrom;
	}
	public void setDatefrom(Date datefrom) {
		this.datefrom = datefrom;
	}
	@Column(name = "dateto")
	@Temporal(value = TemporalType.DATE)
	public Date getDateto() {
		return dateto;
	}
	public void setDateto(Date dateto) {
		this.dateto = dateto;
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
	@JoinColumn(name="vyshkilid")
	public Vyshkil getVyshkil() {
		return vyshkil;
	}
	public void setVyshkil(Vyshkil vyshkil) {
		this.vyshkil = vyshkil;
	}
	public PersonsVyshkil(Long id, Date datefrom, Date dateto, Persons persons, Vyshkil vyshkil) {
		super();
		this.id = id;
		this.datefrom = datefrom;
		this.dateto = dateto;
		this.persons = persons;
		this.vyshkil = vyshkil;
	}
	
	public PersonsVyshkil(Long id) {
		super();
		this.id = id;
	}
	public PersonsVyshkil() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
