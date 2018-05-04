package plast.org.ua.upu.pojo;

import java.util.Date;

public class VporyadnykSamurtokPojo {
	private Long idvporyad;
	private String lastname;
	private String firstname;
	private String stupin;
	private Date fromdatevp;
	private Long idperson;
	private Long idsamhurtok;
	private String hashcode;
	public Long getIdvporyad() {
		return idvporyad;
	}
	public void setIdvporyad(Long idvporyad) {
		this.idvporyad = idvporyad;
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
	public Date getFromdatevp() {
		return fromdatevp;
	}
	public void setFromdatevp(Date fromdatevp) {
		this.fromdatevp = fromdatevp;
	}
	public Long getIdperson() {
		return idperson;
	}
	public void setIdperson(Long idperson) {
		this.idperson = idperson;
	}
	public Long getIdsamhurtok() {
		return idsamhurtok;
	}
	public void setIdsamhurtok(Long idsamhurtok) {
		this.idsamhurtok = idsamhurtok;
	}
	
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	public VporyadnykSamurtokPojo(Long idvporyad, String lastname, String firstname, String stupin, Date fromdatevp,
			Long idperson, Long idsamhurtok, String hashcode) {
		super();
		this.idvporyad = idvporyad;
		this.lastname = lastname;
		this.firstname = firstname;
		this.stupin = stupin;
		this.fromdatevp = fromdatevp;
		this.idperson = idperson;
		this.idsamhurtok = idsamhurtok;
		this.hashcode = hashcode;
	}
	public VporyadnykSamurtokPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
