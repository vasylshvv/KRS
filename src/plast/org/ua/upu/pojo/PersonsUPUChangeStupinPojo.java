package plast.org.ua.upu.pojo;

import java.util.Date;

public class PersonsUPUChangeStupinPojo {
	private Long id;
	private Date begindate;
	private Date enddate;
	private String namestupin;
	private Long idstupin;
	private String nameulad;
	private Long uladid;
	private Long personid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getBegindate() {
		return begindate;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getNamestupin() {
		return namestupin;
	}
	public void setNamestupin(String namestupin) {
		this.namestupin = namestupin;
	}
	public Long getIdstupin() {
		return idstupin;
	}
	public void setIdstupin(Long idstupin) {
		this.idstupin = idstupin;
	}
	public String getNameulad() {
		return nameulad;
	}
	public void setNameulad(String nameulad) {
		this.nameulad = nameulad;
	}
		
	public void setPersonid(Long personid) {
		this.personid = personid;
	}
	
	public PersonsUPUChangeStupinPojo(Long id, Date begindate, Date enddate, String namestupin, Long idstupin,
			String nameulad, Long uladid, Long personid) {
		super();
		this.id = id;
		this.begindate = begindate;
		this.enddate = enddate;
		this.namestupin = namestupin;
		this.idstupin = idstupin;
		this.nameulad = nameulad;
		this.uladid = uladid;
		this.personid = personid;
	}
	public PersonsUPUChangeStupinPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
