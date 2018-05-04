package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;
import com.sun.xml.internal.bind.CycleRecoverable.Context;

import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.table.Stanytsya;

public class StanytsyaDao extends HibernateDaoSupport implements IStanystyaDao{
	private static final Log log = LogFactory.getLog(StanytsyaDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IStanystyaDao instance;
	
	public static IStanystyaDao getInstance() {
		if(instance == null){
			instance = (IStanystyaDao)context.getBean("stan");
		}
		return instance;
	}
	@Override
	public void addStan(Stanytsya stanytsya) {
		log.debug("add stan");
		getHibernateTemplate().save(stanytsya);
	}
	@Override
	public List<Stanytsya> findAllStanytsya() {
		try {
			log.debug("find all");
			String query = "from Stanytsya order by namestan asc";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
		
	}
	@Override
	public List<Stanytsya> findStanytsya(Long id) {
		try {
			log.debug("find all");
			String query = "from Stanytsya where id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<Stanytsya> findLvOkruhaStanytsya() {
		try {
			log.debug("find all");
			String query = "from Stanytsya where id in(1,2,8,9,10,14,17,19,20,23,24,27,34,35,36,38,41,42,45,46) order by namestan asc";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
