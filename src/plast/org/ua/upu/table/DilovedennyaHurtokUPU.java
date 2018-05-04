package plast.org.ua.upu.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dilovedennyahurtokupu")
public class DilovedennyaHurtokUPU {
	private Long id;
	private String nameDilovodHurt;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "namedilovodhurt")
	public String getNameDilovodHurt() {
		return nameDilovodHurt;
	}
	public void setNameDilovodHurt(String nameDilovodHurt) {
		this.nameDilovodHurt = nameDilovodHurt;
	}
	public DilovedennyaHurtokUPU(Long id, String nameDilovodHurt) {
		super();
		this.id = id;
		this.nameDilovodHurt = nameDilovodHurt;
	}
	public DilovedennyaHurtokUPU() {
		super();
		// TODO Auto-generated constructor stub
	}

}
