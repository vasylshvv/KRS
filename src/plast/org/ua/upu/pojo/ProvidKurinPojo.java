package plast.org.ua.upu.pojo;

import java.util.Date;

public class ProvidKurinPojo {
	private Long idperson;
	private String namedilovod;
    private String namestupin;
    private String lastname;
    private String firstname;
    private String phonenumber;
    private String email;
    private Date datebirth;
	public Long getIdperson() {
		return idperson;
	}
	public void setIdperson(Long idperson) {
		this.idperson = idperson;
	}
	public String getNamedilovod() {
		return namedilovod;
	}
	public void setNamedilovod(String namedilovod) {
		this.namedilovod = namedilovod;
	}
	public String getNamestupin() {
		return namestupin;
	}
	public void setNamestupin(String namestupin) {
		this.namestupin = namestupin;
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
	public Date getDatebirth() {
		return datebirth;
	}
	public void setDatebirth(Date datebirth) {
		this.datebirth = datebirth;
	}
	public ProvidKurinPojo(Long idperson, String namedilovod, String namestupin, String lastname, String firstname,
			String phonenumber, String email, Date datebirth) {
		super();
		this.idperson = idperson;
		this.namedilovod = namedilovod;
		this.namestupin = namestupin;
		this.lastname = lastname;
		this.firstname = firstname;
		this.phonenumber = phonenumber;
		this.email = email;
		this.datebirth = datebirth;
	}
	public ProvidKurinPojo() {
		super();		
	}    
}
