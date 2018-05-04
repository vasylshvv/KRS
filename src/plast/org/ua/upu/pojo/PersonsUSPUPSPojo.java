package plast.org.ua.upu.pojo;

import java.util.Date;

public class PersonsUSPUPSPojo {
	private Long idperson;
	private String lastname;
	private String firstname;
	private String stupin;
	public Long getIdperson() {
		return idperson;
	}
	public void setIdperson(Long idperson) {
		this.idperson = idperson;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getStupin() {
		return stupin;
	}
	public void setStupin(String stupin) {
		this.stupin = stupin;
	}
	
	public PersonsUSPUPSPojo(Long idperson, String lastname, String firstname, String stupin) {
		super();
		this.idperson = idperson;
		this.lastname = lastname;
		this.firstname = firstname;
		this.stupin = stupin;		
	}
	public PersonsUSPUPSPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
