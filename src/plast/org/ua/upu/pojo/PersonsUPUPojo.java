package plast.org.ua.upu.pojo;

import java.util.Date;

public class PersonsUPUPojo {
	private Long idperson;
	private Long idstupin;
	private String namestupin;
	private String firstname;
	private String lastname;
	private Date birthday;
	private Date datesworn;
	private String phonenumber;
	private String email;
	private String adress;
	private String education;
	private Long idhurtok;
	private Long idkurin;
	private Long idsamhurtok;
	public Long getIdperson() {
		return idperson;
	}
	public void setIdperson(Long idperson) {
		this.idperson = idperson;
	}
	public Long getIdstupin() {
		return idstupin;
	}
	public void setIdstupin(Long idstupin) {
		this.idstupin = idstupin;
	}
	public String getNamestupin() {
		return namestupin;
	}
	public void setNamestupin(String namestupin) {
		this.namestupin = namestupin;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getIdhurtok() {
		return idhurtok;
	}
	public void setIdhurtok(Long idhurtok) {
		this.idhurtok = idhurtok;
	}	
	public Long getIdkurin() {
		return idkurin;
	}
	public void setIdkurin(Long idkurin) {
		this.idkurin = idkurin;
	}	
	public Date getDatesworn() {
		return datesworn;
	}
	public void setDatesworn(Date datesworn) {
		this.datesworn = datesworn;
	}
	
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	
	public Long getIdsamhurtok() {
		return idsamhurtok;
	}
	public void setIdsamhurtok(Long idsamhurtok) {
		this.idsamhurtok = idsamhurtok;
	}
	public PersonsUPUPojo(Long idperson, Long idstupin, String namestupin, String firstname, String lastname,
			Date birthday, Date datesworn, String phonenumber, String email, String adress, String education,
			Long idhurtok, Long idkurin) {
		super();
		this.idperson = idperson;
		this.idstupin = idstupin;
		this.namestupin = namestupin;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.datesworn = datesworn;
		this.phonenumber = phonenumber;
		this.email = email;
		this.adress = adress;
		this.education = education;
		this.idhurtok = idhurtok;
		this.idkurin = idkurin;
	}
	
	public PersonsUPUPojo(Long idperson, Long idstupin, String namestupin, String firstname, String lastname,
			Date birthday, Date datesworn, String phonenumber, String email, String adress, String education,
			Long idsamhurtok) {
		super();
		this.idperson = idperson;
		this.idstupin = idstupin;
		this.namestupin = namestupin;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.datesworn = datesworn;
		this.phonenumber = phonenumber;
		this.email = email;
		this.adress = adress;
		this.education = education;
		this.idsamhurtok = idsamhurtok;
	}
	public PersonsUPUPojo() {
		super();
	}
}