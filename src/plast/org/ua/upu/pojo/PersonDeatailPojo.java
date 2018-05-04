package plast.org.ua.upu.pojo;

import java.util.Date;

public class PersonDeatailPojo {
	private Long idperson;
	private String firstname;
	private String lastname;
	private Date birthday;
	private String uladname;
	private Long uladid;
	private String stupinname;
	private Long stupinid;
	private String stanytsya;
	private Long stanytsyaid;
	private String kurinusp;
	private Long kurinuspid;
	private String kvname;
	private Long kvid;
	private String vyshkiltabir;
	private Long vyshkiltabirid;
	private String adress;
	private String jobeducation;
	private String mobilephone;
	private String email;
	private Date datesworn;
	private Date datestartplast;
	public Long getIdperson() {
		return idperson;
	}
	public void setIdperson(Long idperson) {
		this.idperson = idperson;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getUladname() {
		return uladname;
	}
	public void setUladname(String uladname) {
		this.uladname = uladname;
	}
	public Long getUladid() {
		return uladid;
	}
	public void setUladid(Long uladid) {
		this.uladid = uladid;
	}
	public String getStupinname() {
		return stupinname;
	}
	public void setStupinname(String stupinname) {
		this.stupinname = stupinname;
	}
	public Long getStupinid() {
		return stupinid;
	}
	public void setStupinid(Long stupinid) {
		this.stupinid = stupinid;
	}
	public String getStanytsya() {
		return stanytsya;
	}
	public void setStanytsya(String stanytsya) {
		this.stanytsya = stanytsya;
	}
	public Long getStanytsyaid() {
		return stanytsyaid;
	}
	public void setStanytsyaid(Long stanytsyaid) {
		this.stanytsyaid = stanytsyaid;
	}
	public String getKurinusp() {
		return kurinusp;
	}
	public void setKurinusp(String kurinusp) {
		this.kurinusp = kurinusp;
	}
	public Long getKurinuspid() {
		return kurinuspid;
	}
	public void setKurinuspid(Long kurinuspid) {
		this.kurinuspid = kurinuspid;
	}
	public String getKvname() {
		return kvname;
	}
	public void setKvname(String kvname) {
		this.kvname = kvname;
	}
	public Long getKvid() {
		return kvid;
	}
	public void setKvid(Long kvid) {
		this.kvid = kvid;
	}
	public String getVyshkiltabir() {
		return vyshkiltabir;
	}
	public void setVyshkiltabir(String vyshkiltabir) {
		this.vyshkiltabir = vyshkiltabir;
	}
	public Long getVyshkiltabirid() {
		return vyshkiltabirid;
	}
	public void setVyshkiltabirid(Long vyshkiltabirid) {
		this.vyshkiltabirid = vyshkiltabirid;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getJobeducation() {
		return jobeducation;
	}
	public void setJobeducation(String jobeducation) {
		this.jobeducation = jobeducation;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDatesworn() {
		return datesworn;
	}
	public void setDatesworn(Date datesworn) {
		this.datesworn = datesworn;
	}
	public Date getDatestartplast() {
		return datestartplast;
	}
	public void setDatestartplast(Date datestartplast) {
		this.datestartplast = datestartplast;
	}
	public PersonDeatailPojo(Long idperson, String firstname, String lastname, Date birthday,/* String uladname,
			Long uladid, String stupinname, Long stupinid, */String stanytsya, Long stanytsyaid/*, String kurinusp,
			Long kurinuspid/*, String kvname, Long kvid, String vyshkiltabir, Long vyshkiltabirid, String adress,
			String jobeducation, String mobilephone, String email, Date datesworn, Date datestartplast*/) {
		super();
		this.idperson = idperson;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		//this.uladname = uladname;
		//this.uladid = uladid;
		//this.stupinname = stupinname;
		//this.stupinid = stupinid;
		this.stanytsya = stanytsya;
		this.stanytsyaid = stanytsyaid;
		//this.kurinusp = kurinusp;
		//this.kurinuspid = kurinuspid;
		/*this.kvname = kvname;
		this.kvid = kvid;
		this.vyshkiltabir = vyshkiltabir;
		this.vyshkiltabirid = vyshkiltabirid;
		this.adress = adress;
		this.jobeducation = jobeducation;
		this.mobilephone = mobilephone;
		this.email = email;
		this.datesworn = datesworn;
		this.datestartplast = datestartplast;*/
	}
	public PersonDeatailPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
