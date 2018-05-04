package plast.org.ua.upu.table;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "samhurtokupu")
public class SamHurtokUPU {
	private Long id;
	private String nameSamHurtok;
	private Integer sexSamHurtok;
//	private Integer numbSamHurtok;
	private String emailSamHurtok;
	private Date dateFrom;
	private Date dateTo;
	private String hashidhurtok;
	private Blob vidznakaHurtka;
	private Stanytsya stanytsya;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "namesamhurtok", length = 30)
	public String getNameSamHurtok() {
		return nameSamHurtok;
	}
	public void setNameSamHurtok(String nameSamHurtok) {
		this.nameSamHurtok = nameSamHurtok;
	}
	@Column(name = "sexsamhurtok", length = 3)
	public Integer getSexSamHurtok() {
		return sexSamHurtok;
	}
	public void setSexSamHurtok(Integer sexSamHurtok) {
		this.sexSamHurtok = sexSamHurtok;
	}
//	@Column(name = "numbsamhurtok", length = 5)
//	public Integer getNumbSamHurtok() {
//		return numbSamHurtok;
//	}
//	public void setNumbSamHurtok(Integer numbSamHurtok) {
//		this.numbSamHurtok = numbSamHurtok;
//	}
	@Column(name = "emailsamhurtok")
	public String getEmailSamHurtok() {
		return emailSamHurtok;
	}
	public void setEmailSamHurtok(String emailSamHurtok) {
		this.emailSamHurtok = emailSamHurtok;
	}
	@Column(name = "datefrom")
	@Temporal(value = TemporalType.DATE)
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	@Column(name = "dateto")
	@Temporal(value = TemporalType.DATE)
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	@Column(name = "hashidsamhurtok")
	public String getHashidhurtok() {
		return hashidhurtok;
	}
	public void setHashidhurtok(String hashidhurtok) {
		this.hashidhurtok = hashidhurtok;
	}
	@Lob
	@Column(name = "vidznaka", columnDefinition = "mediumblob")
	public Blob getVidznakaHurtka() {
		return vidznakaHurtka;
	}
	public void setVidznakaHurtka(Blob vidznakaHurtka) {
		this.vidznakaHurtka = vidznakaHurtka;
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "stanytsyaid")
	public Stanytsya getStanytsya() {
		return stanytsya;
	}	
	public void setStanytsya(Stanytsya stanytsya) {
		this.stanytsya = stanytsya;
	}
		
	public SamHurtokUPU(Long id, String nameSamHurtok, Integer sexSamHurtok, /*Integer numbSamHurtok,*/
			String emailSamHurtok, Date dateFrom, Date dateTo, String hashidhurtok, Blob vidznakaHurtka,
			Stanytsya stanytsya) {
		super();
		this.id = id;
		this.nameSamHurtok = nameSamHurtok;
		this.sexSamHurtok = sexSamHurtok;
		//this.numbSamHurtok = numbSamHurtok;
		this.emailSamHurtok = emailSamHurtok;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.hashidhurtok = hashidhurtok;
		this.vidznakaHurtka = vidznakaHurtka;
		this.stanytsya = stanytsya;
	}
	public SamHurtokUPU() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
