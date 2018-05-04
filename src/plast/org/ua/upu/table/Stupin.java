package plast.org.ua.upu.table;

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

@Entity
@Table(name = "stupin")
public class Stupin {
	private Long id;
	private String nameStupin;
	private Ulad ulad;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "namestupin")
	public String getNameStupin() {
		return nameStupin;
	}
	public void setNameStupin(String nameStupin) {
		this.nameStupin = nameStupin;
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "uladid")
	public Ulad getUlad() {
		return ulad;
	}
	public void setUlad(Ulad ulad) {
		this.ulad = ulad;
	}
	public Stupin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stupin(Long id, String nameStupin, Ulad ulad) {
		super();
		this.id = id;
		this.nameStupin = nameStupin;
		this.ulad = ulad;
	}
	
}
