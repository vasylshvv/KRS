package plast.org.ua.upu.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="okruha")
public class Okruha {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name ="nameokruha")
	private String nameokruha;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNameokruha() {
		return nameokruha;
	}
	public void setNameokruha(String nameokruha) {
		this.nameokruha = nameokruha;
	}
	public Okruha(Long id, String nameokruha) {	
		this.id = id;
		this.nameokruha = nameokruha;
	}
	public Okruha() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Okruha(String nameokruha) {
		this.nameokruha = nameokruha;
	}
	
}
