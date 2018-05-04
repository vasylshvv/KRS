package plast.org.ua.upu.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "faq")
public class FAQ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "question")
	private String question;
	@Column(name = "answer")
	private String answer;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public FAQ(Long id, String question, String answer) {
		this.id = id;
		this.question = question;
		this.answer = answer;
	}

	public FAQ() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
