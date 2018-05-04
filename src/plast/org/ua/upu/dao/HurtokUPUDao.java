package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IHurtokUPUDao;
import plast.org.ua.upu.table.HurtokUPU;

public class HurtokUPUDao extends HibernateDaoSupport implements IHurtokUPUDao{
	private static final Log log = LogFactory.getLog(HurtokUPUDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IHurtokUPUDao instance;
	
	public static IHurtokUPUDao getInstance() {
		if(instance == null){
			instance = (IHurtokUPUDao)context.getBean("hurtokupu");
		}
		return instance;
	}
	@Override
	public void addHurtokUPU(HurtokUPU hurtokUPU) {
		log.debug("add hurtok");
		getHibernateTemplate().save(hurtokUPU);
		
	}
	@Override
	public List<HurtokUPU> findAllHurtokUPU() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<HurtokUPU> findHurtokINKurinUPU(Long kurinId) {
		try {
			log.debug("select findHurtokINKurinUPU");			
			String query = "from HurtokUPU where kurinupuid="+kurinId+ " order by number asc";
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<HurtokUPU> findHurtokUPU(Long id) {
		try {
			log.debug("select findHurtokINKurinUPU");			
			String query = "from HurtokUPU where id="+id;			
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
