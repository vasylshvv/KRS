package plast.org.ua.upu.pojo;

public class PersonVyshkilTabirPojo {
	private Long personid;
	private Long vyshkiltabirid;
	private String vyshkiltabirname;
	public Long getPersonid() {
		return personid;
	}
	public void setPersonid(Long personid) {
		this.personid = personid;
	}
	public Long getVyshkiltabirid() {
		return vyshkiltabirid;
	}
	public void setVyshkiltabirid(Long vyshkiltabirid) {
		this.vyshkiltabirid = vyshkiltabirid;
	}
	public String getVyshkiltabirname() {
		return vyshkiltabirname;
	}
	public void setVyshkiltabirname(String vyshkiltabirname) {
		this.vyshkiltabirname = vyshkiltabirname;
	}
	public PersonVyshkilTabirPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PersonVyshkilTabirPojo(Long personid, Long vyshkiltabirid, String vyshkiltabirname) {
		super();
		this.personid = personid;
		this.vyshkiltabirid = vyshkiltabirid;
		this.vyshkiltabirname = vyshkiltabirname;
	}
	
}
