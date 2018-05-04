package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.table.SamHurtokUPU;

public class SamHurtokUPUDao extends HibernateDaoSupport implements ISamHurtokUPUDao{
	private static final Log log = LogFactory.getLog(SamHurtokUPUDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static ISamHurtokUPUDao instance;
	
	public static ISamHurtokUPUDao getInstance() {
		if (instance == null) {
			instance = (ISamHurtokUPUDao)context.getBean("samurtokupu");
		}
		return instance;
	}
	@Override
	public void addsamhurtok(SamHurtokUPU samHurtokUPU) {
		log.debug("add sam hurtok");
		getHibernateTemplate().save(samHurtokUPU);
	}
	@Override
	public List<SamHurtokUPU> findAllSamHurtok() {
		try {
			log.debug("select SamHurtokUPU");
			String query = "from SamHurtokUPU order by namesamhurtok asc";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<SamHurtokUPU> findAllSamHurtok(Long stanid) {
		try {
			log.debug("select SamHurtokUPU");
			String query = "from SamHurtokUPU where stanytsyaid = "+ stanid
					+ " order by namesamhurtok asc";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<SamHurtokUPU> findOneSamHurtok(Long idhurtok) {
		try {
			log.debug("select SamHurtokUPU");
			String query = "from SamHurtokUPU where id = "+ idhurtok
					+ " order by namesamhurtok asc";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<SamHurtokUPU> findLvOkruhaStanytsyaSamHurtok() {
		try {
			log.debug("select SamHurtokUPU");
			String query = "from SamHurtokUPU where stanytsyaid in(1,2,8,9,10,14,17,19,20,23,24,27,34,35,36,38,41,42,45,46) "
					+ " order by namesamhurtok asc";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public void updateSMhurtok(SamHurtokUPU samHurtokUPU) {
		log.debug("update sam hurtok");
		getHibernateTemplate().update(samHurtokUPU);
		
	}
}
