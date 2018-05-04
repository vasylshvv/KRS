package plast.org.ua.upu.pojo;

public class PersonKVPojo {
	private Long personid;
	private Long idkv;
	private String namekv;
	public Long getPersonid() {
		return personid;
	}
	public void setPersonid(Long personid) {
		this.personid = personid;
	}
	public Long getIdkv() {
		return idkv;
	}
	public void setIdkv(Long idkv) {
		this.idkv = idkv;
	}
	public String getNamekv() {
		return namekv;
	}
	public void setNamekv(String namekv) {
		this.namekv = namekv;
	}
	public PersonKVPojo(Long personid, Long idkv, String namekv) {
		super();
		this.personid = personid;
		this.idkv = idkv;
		this.namekv = namekv;
	}
	public PersonKVPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
