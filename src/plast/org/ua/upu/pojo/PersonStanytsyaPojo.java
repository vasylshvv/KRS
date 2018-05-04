package plast.org.ua.upu.pojo;

public class PersonStanytsyaPojo {
	private Long personid;
	private Long stanid;
	private String stanname;
	
	
	public Long getPersonid() {
		return personid;
	}


	public void setPersonid(Long personid) {
		this.personid = personid;
	}


	public Long getStanid() {
		return stanid;
	}


	public void setStanid(Long stanid) {
		this.stanid = stanid;
	}


	public String getStanname() {
		return stanname;
	}


	public void setStanname(String stanname) {
		this.stanname = stanname;
	}


	public PersonStanytsyaPojo(Long personid, Long stanid, String stanname) {
		super();
		this.personid = personid;
		this.stanid = stanid;
		this.stanname = stanname;
	}


	public PersonStanytsyaPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
