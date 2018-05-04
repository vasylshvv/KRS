package plast.org.ua.upu.pojo;

import java.util.Date;

public class VporyadnykPojo {
	private Long idvporyad;
	private String lastname;
	private String firstname;
	private String stupin;
	private Date fromdatevp;
	private Long idperson;
	private Long idhurtok;
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
	public Long getIdhurtok() {
		return idhurtok;
	}
	public void setIdhurtok(Long idhurtok) {
		this.idhurtok = idhurtok;
	}
	
	public VporyadnykPojo(Long idvporyad, String lastname, String firstname, String stupin, Date fromdatevp,
			Long idperson, Long idhurtok) {
		super();
		this.idvporyad = idvporyad;
		this.lastname = lastname;
		this.firstname = firstname;
		this.stupin = stupin;
		this.fromdatevp = fromdatevp;
		this.idperson = idperson;
		this.idhurtok = idhurtok;
	}
	public VporyadnykPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
