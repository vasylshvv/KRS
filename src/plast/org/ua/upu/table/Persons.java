package plast.org.ua.upu.table;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "persons")
public class Persons {
	private Long id;
	private String lastName;
	private String firstName;
	private Date datebirth;
	private String phoneNumber;
	private String email;
	private Date dateSworn;
	private String address;
	private String jobeducation;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "lastname")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name = "firstname")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name = "datebirth")
	@Temporal(value = TemporalType.DATE)
	public Date getDatebirth() {
		return datebirth;
	}
	public void setDatebirth(Date datebirth) {
		this.datebirth = datebirth;
	}
	@Column(name = "phonenumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "datesworn")
	@Temporal(value = TemporalType.DATE)
	public Date getDateSworn() {
		return dateSworn;
	}
	public void setDateSworn(Date dateSworn) {
		this.dateSworn = dateSworn;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "jobeducation")
	public String getJobeducation() {
		return jobeducation;
	}
	public void setJobeducation(String jobeducation) {
		this.jobeducation = jobeducation;
	}
	
	public Persons(Long id, String lastName, String firstName, Date datebirth, String phoneNumber, String email,
			Date dateSworn, String address, String jobeducation) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.datebirth = datebirth;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.dateSworn = dateSworn;
		this.address = address;
		this.jobeducation = jobeducation;
	}
	public Persons() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
