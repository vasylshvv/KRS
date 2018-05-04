package plast.org.ua.upu.pojo;

import java.util.Date;

public class PersonUladStupinPojo {
	private Long personid;
	private Long uladid;
	private String nameulad;
	private Long stupinid;
	private String namestupin;
	private Date datestart;
	public Long getPersonid() {
		return personid;
	}
	public void setPersonid(Long personid) {
		this.personid = personid;
	}
	public Long getUladid() {
		return uladid;
	}
	public void setUladid(Long uladid) {
		this.uladid = uladid;
	}
	public String getNameulad() {
		return nameulad;
	}
	public void setNameulad(String nameulad) {
		this.nameulad = nameulad;
	}
	public Long getStupinid() {
		return stupinid;
	}
	public void setStupinid(Long stupinid) {
		this.stupinid = stupinid;
	}
	public String getNamestupin() {
		return namestupin;
	}
	public void setNamestupin(String namestupin) {
		this.namestupin = namestupin;
	}
	public Date getDatestart() {
		return datestart;
	}
	public void setDatestart(Date datestart) {
		this.datestart = datestart;
	}
	public PersonUladStupinPojo(Long personid, Long uladid, String nameulad, Long stupinid, String namestupin,
			Date datestart) {
		super();
		this.personid = personid;
		this.uladid = uladid;
		this.nameulad = nameulad;
		this.stupinid = stupinid;
		this.namestupin = namestupin;
		this.datestart = datestart;
	}
	public PersonUladStupinPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
