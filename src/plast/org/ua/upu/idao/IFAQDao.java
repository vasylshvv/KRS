package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.table.FAQ;


public interface IFAQDao {
	public void addFAQ(FAQ faq);
	public List<FAQ> findAll();
	
}
