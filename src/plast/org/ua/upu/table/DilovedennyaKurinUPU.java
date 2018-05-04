package plast.org.ua.upu.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dilovedennyakurinupu")
public class DilovedennyaKurinUPU {
	private Long id;
	private String nameDilovod;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "namedilovod")
	public String getNameDilovod() {
		return nameDilovod;
	}
	public void setNameDilovod(String nameDilovod) {
		this.nameDilovod = nameDilovod;
	}
	public DilovedennyaKurinUPU(Long id, String nameDilovod) {
		super();
		this.id = id;
		this.nameDilovod = nameDilovod;
	}
	public DilovedennyaKurinUPU() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
