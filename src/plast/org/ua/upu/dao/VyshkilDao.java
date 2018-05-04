package plast.org.ua.upu.dao;

import java.util.List;

import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import plast.org.ua.upu.idao.IVyshkilDao;
import plast.org.ua.upu.table.Vyshkil;

public class VyshkilDao extends HibernateDaoSupport implements IVyshkilDao{
	private static final Log log = LogFactory.getLog(VyshkilDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IVyshkilDao instance;
	
	public static IVyshkilDao getInstance() {
		if(instance == null){
			instance = (IVyshkilDao)context.getBean("vyshkil");
		}
		return instance;
	}
	@Override
	public void addVyshkil(Vyshkil vyshkil) {
		log.debug("add vyshkil");
		getHibernateTemplate().save(vyshkil);
	}
	@Override
	public List<Vyshkil> findAllVyshkil() {
		try {
			log.debug("find vyshkil");
			String query = "from Vyshkil";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<Vyshkil> findOneVyshkil(Long id) {
		try {
			log.debug("find vyshkil one");
			String query = "from Vyshkil where id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
