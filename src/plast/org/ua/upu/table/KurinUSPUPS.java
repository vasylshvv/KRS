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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "kurinuspups")
public class KurinUSPUPS {
	private Long id;
	private String namekurin;
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
	@Column(name = "namekurin")
	public String getNamekurin() {
		return namekurin;
	}
	public void setNamekurin(String namekurin) {
		this.namekurin = namekurin;
	}
	
	public KurinUSPUPS(Long id, String namekurin, Ulad ulad) {
		super();
		this.id = id;
		this.namekurin = namekurin;
		this.ulad = ulad;
	}
	public KurinUSPUPS() {
		super();
		// TODO Auto-generated constructor stub
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "uladid")
	public Ulad getUlad() {
		return ulad;
	}
	public void setUlad(Ulad ulad) {
		this.ulad = ulad;
	}
	
}
