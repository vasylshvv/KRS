package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IOkruhaDao;
import plast.org.ua.upu.table.Okruha;

public class OkruhaDao extends HibernateDaoSupport implements IOkruhaDao{
	private static final Log log = LogFactory.getLog(StanytsyaDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IOkruhaDao instance;
	
	public static IOkruhaDao getInstance() {
		if(instance == null){
			instance = (IOkruhaDao)context.getBean("okruha");
		}
		return instance;
	}
	@Override
	public void addOkruha(Okruha okruha) {
		log.debug("add okruha");
		getHibernateTemplate().save(okruha);
	}
	@Override
	public List<Okruha> findAllOkruha() {
		try {
			log.debug("find all");
			String query = "from Okruha order by nameokruha asc";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<Okruha> findOneOkruha(Long id) {
		try {
			log.debug("find all");
			String query = "from Okruha where id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
