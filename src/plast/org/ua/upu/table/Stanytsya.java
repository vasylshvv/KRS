package plast.org.ua.upu.table;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stanytsya")
public class Stanytsya {
	private Long id;
	private String namestan;
	private Okruha okruha;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "namestan")
	public String getNamestan() {
		return namestan;
	}
	public void setNamestan(String namestan) {
		this.namestan = namestan;
	}
	
	public Stanytsya(Long id, String namestan, Okruha okruha) {
		this.id = id;
		this.namestan = namestan;
		this.okruha = okruha;
	}
	public Stanytsya() {
		super();
		// TODO Auto-generated constructor stub
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="okruhaid")
	public Okruha getOkruha() {
		return okruha;
	}
	public void setOkruha(Okruha okruha) {
		this.okruha = okruha;
	}
	

}
