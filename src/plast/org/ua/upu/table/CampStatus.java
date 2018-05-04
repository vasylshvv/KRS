package plast.org.ua.upu.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "campstatus")
public class CampStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "namecampstatus")
	private String nameCampStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameCampStatus() {
		return nameCampStatus;
	}
	public void setNameCampStatus(String nameCampStatus) {
		this.nameCampStatus = nameCampStatus;
	}
	public CampStatus(Long id, String nameCampStatus) {
		this.id = id;
		this.nameCampStatus = nameCampStatus;
	}
	public CampStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
