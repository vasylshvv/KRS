package plast.org.ua.upu.pojo;

import java.util.Date;

public class PersonsUPUChangeDilovodKurinPojo {
	private Long id;
	private Long iddilovodkur;
	private Long idperson;
	private Long idkurin;	
	private Date datebegin;
	private Date dateend;	
	private String namedilovodkur;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIddilovodkur() {
		return iddilovodkur;
	}
	public void setIddilovodkur(Long iddilovodkur) {
		this.iddilovodkur = iddilovodkur;
	}
	public Long getIdperson() {
		return idperson;
	}
	public void setIdperson(Long idperson) {
		this.idperson = idperson;
	}
	public Long getIdkurin() {
		return idkurin;
	}
	public void setIdkurin(Long idkurin) {
		this.idkurin = idkurin;
	}
	public Date getDatebegin() {
		return datebegin;
	}
	public void setDatebegin(Date datebegin) {
		this.datebegin = datebegin;
	}
	public Date getDateend() {
		return dateend;
	}
	public void setDateend(Date dateend) {
		this.dateend = dateend;
	}
	public String getNamedilovodkur() {
		return namedilovodkur;
	}
	public void setNamedilovodkur(String namedilovodkur) {
		this.namedilovodkur = namedilovodkur;
	}
	public PersonsUPUChangeDilovodKurinPojo(Long id, Long iddilovodkur, Long idperson, Long idkurin, Date datebegin,
			Date dateend, String namedilovodkur) {
		super();
		this.id = id;
		this.iddilovodkur = iddilovodkur;
		this.idperson = idperson;
		this.idkurin = idkurin;
		this.datebegin = datebegin;
		this.dateend = dateend;
		this.namedilovodkur = namedilovodkur;
	}
	public PersonsUPUChangeDilovodKurinPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
