package plast.org.ua.upu.pojo;

import java.util.Date;

public class PersonsUPUChangeDilovodHurtokPojo {
	private Long id;
	private Long iddilovod;
	private Long idperson;
	private Long idkurin;
	private Long idhurtok;
	private Long idsmhurtok;
	private Date datebegin;
	private Date dateend;
	private String namehurtok;
	private String namedilovod;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIddilovod() {
		return iddilovod;
	}
	public void setIddilovod(Long iddilovod) {
		this.iddilovod = iddilovod;
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
	public Long getIdhurtok() {
		return idhurtok;
	}
	public void setIdhurtok(Long idhurtok) {
		this.idhurtok = idhurtok;
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
	public String getNamehurtok() {
		return namehurtok;
	}
	public void setNamehurtok(String namehurtok) {
		this.namehurtok = namehurtok;
	}
	public String getNamedilovod() {
		return namedilovod;
	}
	public void setNamedilovod(String namedilovod) {
		this.namedilovod = namedilovod;
	}
	
	public Long getIdsmhurtok() {
		return idsmhurtok;
	}
	public void setIdsmhurtok(Long idsmhurtok) {
		this.idsmhurtok = idsmhurtok;
	}
	public PersonsUPUChangeDilovodHurtokPojo(Long id, Long iddilovod, Long idperson, Long idkurin, Long idhurtok,
			Date datebegin, Date dateend, String namehurtok, String namedilovod) {
		super();
		this.id = id;
		this.iddilovod = iddilovod;
		this.idperson = idperson;
		this.idkurin = idkurin;
		this.idhurtok = idhurtok;
		this.datebegin = datebegin;
		this.dateend = dateend;
		this.namehurtok = namehurtok;
		this.namedilovod = namedilovod;
	}
	
	public PersonsUPUChangeDilovodHurtokPojo(Long id, Long iddilovod, Long idperson, Long idsmhurtok, Date datebegin,
			Date dateend, String namehurtok, String namedilovod) {
		super();
		this.id = id;
		this.iddilovod = iddilovod;
		this.idperson = idperson;
		this.idsmhurtok = idsmhurtok;
		this.datebegin = datebegin;
		this.dateend = dateend;
		this.namehurtok = namehurtok;
		this.namedilovod = namedilovod;
	}
	public PersonsUPUChangeDilovodHurtokPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
