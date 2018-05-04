package plast.org.ua.upu.pojo;

public class PersonKurinUSPPojo {
	private Long idperson;
	private Long idkurinusp;
	private String namekurinusp;
	
	public PersonKurinUSPPojo(Long idperson, Long idkurinusp, String namekurinusp) {
		super();
		this.idperson = idperson;
		this.idkurinusp = idkurinusp;
		this.namekurinusp = namekurinusp;
	}

	public Long getIdperson() {
		return idperson;
	}

	public void setIdperson(Long idperson) {
		this.idperson = idperson;
	}

	public Long getIdkurinusp() {
		return idkurinusp;
	}

	public void setIdkurinusp(Long idkurinusp) {
		this.idkurinusp = idkurinusp;
	}

	public String getNamekurinusp() {
		return namekurinusp;
	}

	public void setNamekurinusp(String namekurinusp) {
		this.namekurinusp = namekurinusp;
	}

	public PersonKurinUSPPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
