package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IUladDao;
import plast.org.ua.upu.idao.IZvyazkovyyDao;
import plast.org.ua.upu.pojo.ZvyazkovyyPojo;
import plast.org.ua.upu.table.Zvyazkovyy;

public class ZvyazkovyyDao extends HibernateDaoSupport implements IZvyazkovyyDao {
	private static final Log log = LogFactory.getLog(ZvyazkovyyDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IZvyazkovyyDao instance;

	public static IZvyazkovyyDao getInstance() {
		if (instance == null) {
			instance = (IZvyazkovyyDao) context.getBean("zvyaz");
		}
		return instance;
	}

	@Override
	public void addZvyazkovyy(Zvyazkovyy zvyazkovyy) {
		log.debug("add new zvyazkovyy");
		getHibernateTemplate().save(zvyazkovyy);
	}

	@Override
	public void updateOldZvyazkovyy(Zvyazkovyy zvyazkovyy) {
		log.debug("update old zvyazkovyy");
		getHibernateTemplate().update(zvyazkovyy);

	}

	@Override
	public List<ZvyazkovyyPojo> findZvyazkovyyKurin(Long idkurin) {
		try {

			log.debug("find zvyazkovyy");
			String query = "SELECT new plast.org.ua.upu.pojo.ZvyazkovyyPojo( " 
							+ "z.id, " 
							+ "p.lastName, "
							+ "p.firstName, " 
							+ "s.nameStupin," 
							+ "z.datebegin, " 
							+ "p.id, "
							+ "z.hashcode) "
							+ "FROM PersonStupinUlad psu, Zvyazkovyy z " 
							+ "JOIN psu.persons p " 
							+ "JOIN psu.ulad u "
							+ "JOIN psu.stupin s " 
							+ "JOIN z.kurinupu k " 
							+ "where p.id = z.persons " 
							+ "and z.dateend is null "
							+ "and psu.dateto is null " 
							+ "and k.id=" + idkurin;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<Zvyazkovyy> findOneZvyazkovyy(Long id) {
		try {
			log.debug("find zvyazkovyy");
			String query = " FROM Zvyazkovyy where id=" + id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<Zvyazkovyy> findOneZvyazkovyyInKurin(Long idkurin) {
		try {
			log.debug("find zvyazkovyy");
			String query = " FROM Zvyazkovyy where kurinupuid=" + idkurin+ " and dateend is null";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
