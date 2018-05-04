package plast.org.ua.upu.pojo;

public class PersonVyshkilPojo {
	private Long personid;
	private Long vyshkilid;
	private String vyshkilname;
	private String vyshkildescription;
	public Long getPersonid() {
		return personid;
	}
	public void setPersonid(Long personid) {
		this.personid = personid;
	}
	public Long getVyshkilid() {
		return vyshkilid;
	}
	public void setVyshkilid(Long vyshkilid) {
		this.vyshkilid = vyshkilid;
	}
	public String getVyshkilname() {
		return vyshkilname;
	}
	public void setVyshkilname(String vyshkilname) {
		this.vyshkilname = vyshkilname;
	}
	
	public String getVyshkildescription() {
		return vyshkildescription;
	}
	public void setVyshkildescription(String vyshkildescription) {
		this.vyshkildescription = vyshkildescription;
	}
	
	public PersonVyshkilPojo(Long personid, Long vyshkilid, String vyshkilname, String vyshkildescription) {
		super();
		this.personid = personid;
		this.vyshkilid = vyshkilid;
		this.vyshkilname = vyshkilname;
		this.vyshkildescription = vyshkildescription;
	}
	public PersonVyshkilPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
