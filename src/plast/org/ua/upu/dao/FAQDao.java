package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IDilovedennyaDao;
import plast.org.ua.upu.idao.IFAQDao;
import plast.org.ua.upu.table.FAQ;

public class FAQDao extends HibernateDaoSupport implements IFAQDao{
	
	private static final Log log = LogFactory.getLog(FAQDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IFAQDao instance;
	

	public static IFAQDao getInstance() {
		if(instance == null){
			instance = (IFAQDao)context.getBean("faq");
		}
		return instance;
	}
	@Override
	public void addFAQ(FAQ faq) {
		log.debug("add faq");
		getHibernateTemplate().save(faq);
	}
	@Override
	public List<FAQ> findAll() {
		try {
			log.debug("find all faq");
			String query = "from FAQ";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
}
