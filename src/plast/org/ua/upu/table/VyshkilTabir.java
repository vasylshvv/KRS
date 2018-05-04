package plast.org.ua.upu.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vyshkiltabir")
public class VyshkilTabir {
	private Long id;
	private String namevyshkiltabir;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "namevyshkiltabir")
	public String getNamevyshkiltabir() {
		return namevyshkiltabir;
	}
	public void setNamevyshkiltabir(String namevyshkiltabir) {
		this.namevyshkiltabir = namevyshkiltabir;
	}
	public VyshkilTabir(Long id, String namevyshkiltabir) {
		super();
		this.id = id;
		this.namevyshkiltabir = namevyshkiltabir;
	}
	public VyshkilTabir() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
