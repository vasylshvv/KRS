package plast.org.ua.upu.pojo;

public class ReportCommonSmHurtokPojo {
	//private Long idokruha;
	private Long idstan;
	private Long idsmhurtok;
	private String numbersmhurtok;
	private String namesmhurtok;
	private Integer prykhylnyk;
	private Integer uchansnyk;
	private Integer rozviduvach;
	private Integer skobvirlytsya;
	private Integer hetmanskobvirlytsa;
	private Integer allcount;
	private Integer approved;

	public Long getIdstan() {
		return idstan;
	}

	public void setIdstan(Long idstan) {
		this.idstan = idstan;
	}

	public Long getIdsmhurtok() {
		return idsmhurtok;
	}

	public void setIdsmhurtok(Long idsmhurtok) {
		this.idsmhurtok = idsmhurtok;
	}

	public String getNumbersmhurtok() {
		return numbersmhurtok;
	}

	public void setNumbersmhurtok(String numbersmhurtok) {
		this.numbersmhurtok = numbersmhurtok;
	}

	public String getNamesmhurtok() {
		return namesmhurtok;
	}

	public void setNamesmhurtok(String namesmhurtok) {
		this.namesmhurtok = namesmhurtok;
	}

	public Integer getPrykhylnyk() {
		return prykhylnyk;
	}

	public void setPrykhylnyk(Integer prykhylnyk) {
		this.prykhylnyk = prykhylnyk;
	}

	public Integer getUchansnyk() {
		return uchansnyk;
	}

	public void setUchansnyk(Integer uchansnyk) {
		this.uchansnyk = uchansnyk;
	}

	public Integer getRozviduvach() {
		return rozviduvach;
	}

	public void setRozviduvach(Integer rozviduvach) {
		this.rozviduvach = rozviduvach;
	}

	public Integer getSkobvirlytsya() {
		return skobvirlytsya;
	}

	public void setSkobvirlytsya(Integer skobvirlytsya) {
		this.skobvirlytsya = skobvirlytsya;
	}

	public Integer getHetmanskobvirlytsa() {
		return hetmanskobvirlytsa;
	}

	public void setHetmanskobvirlytsa(Integer hetmanskobvirlytsa) {
		this.hetmanskobvirlytsa = hetmanskobvirlytsa;
	}

	public Integer getAllcount() {
		return allcount;
	}

	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}
//
//	public Long getIdokruha() {
//		return idokruha;
//	}
//
//	public void setIdokruha(Long idokruha) {
//		this.idokruha = idokruha;
//	}
	

	public ReportCommonSmHurtokPojo(Long idstan, Long idsmhurtok, String numbersmhurtok, String namesmhurtok,
			Integer prykhylnyk, Integer uchansnyk, Integer rozviduvach, Integer skobvirlytsya,
			Integer hetmanskobvirlytsa, Integer allcount, Integer approved) {	
		this.idstan = idstan;
		this.idsmhurtok = idsmhurtok;
		this.numbersmhurtok = numbersmhurtok;
		this.namesmhurtok = namesmhurtok;
		this.prykhylnyk = prykhylnyk;
		this.uchansnyk = uchansnyk;
		this.rozviduvach = rozviduvach;
		this.skobvirlytsya = skobvirlytsya;
		this.hetmanskobvirlytsa = hetmanskobvirlytsa;
		this.allcount = allcount;
		this.approved = approved; 
	}

	public Integer getApproved() {
		return approved;
	}

	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	public ReportCommonSmHurtokPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
