package plast.org.ua.upu.table;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "kurinupu")
public class KurinUPU {
	
	private Long id;	
	private Integer numberKurin;
	private Integer sexKurin;	
	private String nameKurin;	
	private Date dateRegister;
	private String emailKurin;	
	private String hashid;	
	private Stanytsya stanytsya;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "numberkurin")
	public Integer getNumberKurin() {
		return numberKurin;
	}
	public void setNumberKurin(Integer numberKurin) {
		this.numberKurin = numberKurin;
	}
	
//	public String getNumberKurin() {
//		return numberKurin;
//	}
//	public void setNumberKurin(String numberKurin) {
//		this.numberKurin = numberKurin;
//	}
	@Column(name = "sexkurin")
	public Integer getSexKurin() {
		return sexKurin;
	}	
	public void setSexKurin(Integer sexKurin) {
		this.sexKurin = sexKurin;
	}
	
	@Column(name = "namekurin")
	public String getNameKurin() {
		return nameKurin;
	}
	public void setNameKurin(String nameKurin) {
		this.nameKurin = nameKurin;
	}
	@Column(name = "dateregister")
	@Temporal(value = TemporalType.DATE)
	public Date getDateRegister() {
		return dateRegister;
	}
	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}
	@Column(name = "emailkurin")
	public String getEmailKurin() {
		return emailKurin;
	}
	public void setEmailKurin(String emailKurin) {
		this.emailKurin = emailKurin;
	}
	@Column(name = "hashid")
	public String getHashid() {
		return hashid;
	}
	public void setHashid(String hashid) {
		this.hashid = hashid;
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "stanytsyaid")
	public Stanytsya getStanytsya() {
		return stanytsya;
	}
	public void setStanytsya(Stanytsya stanytsya) {
		this.stanytsya = stanytsya;
	}
	public KurinUPU(Long id, /*String*/ Integer numberKurin, Integer sexKurin, String nameKurin, Date dateRegister,
			String emailKurin, String hashid, Stanytsya stanytsya) {
		super();
		this.id = id;
		this.numberKurin = numberKurin;
		this.sexKurin = sexKurin;
		this.nameKurin = nameKurin;
		this.dateRegister = dateRegister;
		this.emailKurin = emailKurin;
		this.hashid = hashid;
		this.stanytsya = stanytsya;
	}
	public KurinUPU() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
