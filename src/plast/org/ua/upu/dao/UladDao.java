package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.idao.IUladDao;
import plast.org.ua.upu.table.Ulad;

public class UladDao extends HibernateDaoSupport implements IUladDao{
	private static final Log log = LogFactory.getLog(UladDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IUladDao instance;
	
	public static IUladDao getInstance() {
		if(instance == null){
			instance = (IUladDao)context.getBean("ulad");
		}
		return instance;
	}
	@Override
	public void addUlad(Ulad ulad) {
		log.debug("add ulad");
		getHibernateTemplate().save(ulad);
	}@Override
	public List<Ulad> findAllUlad() {
		try {
			log.debug("find ulad");
			String query = "from Ulad";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<Ulad> findUlad(String nameUlad) {
		try {
			log.debug("find ulad");
			String query = "from Ulad where nameulad='"+nameUlad+"'";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<Ulad> findUlad(Long id) {
		try {
			log.debug("find ulad");
			String query = "from Ulad where id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
