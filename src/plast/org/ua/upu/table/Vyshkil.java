package plast.org.ua.upu.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vyshkil")
public class Vyshkil {
	private Long id;
	private String namevyshkil;
	private String description;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "namevyshkil")
	public String getNamevyshkil() {
		return namevyshkil;
	}
	public void setNamevyshkil(String namevyshkil) {
		this.namevyshkil = namevyshkil;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Vyshkil(Long id, String namevyshkil, String description) {
		super();
		this.id = id;
		this.namevyshkil = namevyshkil;
		this.description = description;
	}
	public Vyshkil() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
