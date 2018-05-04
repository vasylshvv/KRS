package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.ICampStatusDao;
import plast.org.ua.upu.table.CampStatus;

public class CampStatusDao extends HibernateDaoSupport implements ICampStatusDao{
	private static final Log log = LogFactory.getLog(CampStatusDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static ICampStatusDao instance;
	
	public static ICampStatusDao getInstance() {
		if(instance == null){
			instance = (ICampStatusDao)context.getBean("campstatus");
		}
		return instance;
	}

	@Override
	public List<CampStatus> findAllCampStatus() {
		try {
			log.debug("finad all CampStatusv");
			String query = "from CampStatus";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			log.debug(e);
			throw e;
		}
	}

	@Override
	public List<CampStatus> findOneCampStatus(Long id) {
		try {
			log.debug("finad all CampStatus");
			String query = "from CampStatus where id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			log.debug(e);
			throw e;
		}
	}

	@Override
	public void addCampStatus(CampStatus campStatus) {
		log.debug("add campStatus");
		getHibernateTemplate().save(campStatus);
		
	}
	
}
