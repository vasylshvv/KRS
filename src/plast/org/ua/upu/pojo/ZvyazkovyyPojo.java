package plast.org.ua.upu.pojo;

import java.util.Date;

public class ZvyazkovyyPojo {
	private Long idzvyaz;
	private String lastname;
	private String firstname;
	private String stupin;
	private Date fromdatezv;
	private Long idperson;
	private String hashcode;
	public Long getIdzvyaz() {
		return idzvyaz;
	}
	public void setIdzvyaz(Long idzvyaz) {
		this.idzvyaz = idzvyaz;
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
	public Date getFromdatezv() {
		return fromdatezv;
	}
	public void setFromdatezv(Date fromdatezv) {
		this.fromdatezv = fromdatezv;
	}
	public Long getIdperson() {
		return idperson;
	}
	public void setIdperson(Long idperson) {
		this.idperson = idperson;
	}
	
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	public ZvyazkovyyPojo(Long idzvyaz, String lastname, String firstname, String stupin, Date fromdatezv,
			Long idperson, String hashcode) {
		super();
		this.idzvyaz = idzvyaz;
		this.lastname = lastname;
		this.firstname = firstname;
		this.stupin = stupin;
		this.fromdatezv = fromdatezv;
		this.idperson = idperson;
		this.hashcode = hashcode;
	}
	public ZvyazkovyyPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
