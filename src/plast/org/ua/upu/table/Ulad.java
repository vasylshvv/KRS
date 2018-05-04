package plast.org.ua.upu.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ulad")
public class Ulad {
	private Long id;
	private String nameulad;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "nameulad")
	public String getNameulad() {
		return nameulad;
	}
	public void setNameulad(String nameulad) {
		this.nameulad = nameulad;
	}
	public Ulad(Long id, String nameulad) {
		super();
		this.id = id;
		this.nameulad = nameulad;
	}
	public Ulad() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
