package plast.org.ua.upu.table;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="requestproblem")
public class RequestProblem {
	private Long id;
	private String sfullname;
	private String email;
	private String textrequest;
	private Date sysdate;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "sfullname", length = 50)
	public String getSfullname() {
		return sfullname;
	}
	public void setSfullname(String sfullname) {
		this.sfullname = sfullname;
	}
	public String getEmail() {
		return email;
	}
	@Column(name = "email", length = 50)
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTextrequest() {
		return textrequest;
	}
	@Column(name = "textrequest", length = 1000)
	public void setTextrequest(String textrequest) {
		this.textrequest = textrequest;
	}
	@Column(name = "sysdate")
	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getSysdate() {
		return sysdate;
	}
	public void setSysdate(Date sysdate) {
		this.sysdate = sysdate;
	}
	public RequestProblem(Long id, String sfullname, String email, String textrequest, Date sysdate) {
		super();
		this.id = id;
		this.sfullname = sfullname;
		this.email = email;
		this.textrequest = textrequest;
		this.sysdate = sysdate;
	}
	public RequestProblem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
