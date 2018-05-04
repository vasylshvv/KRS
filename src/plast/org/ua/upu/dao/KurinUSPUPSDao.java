package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.IKurinUSPUPSDao;
import plast.org.ua.upu.table.KurinUSPUPS;

public class KurinUSPUPSDao extends HibernateDaoSupport implements IKurinUSPUPSDao{
	private static final Log log = LogFactory.getLog(KurinUSPUPSDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IKurinUSPUPSDao instance;
	
	public static IKurinUSPUPSDao getInstance() {
		if(instance == null){
			instance = (IKurinUSPUPSDao)context.getBean("kurinuspups");
		}
		return instance;
	}
	@Override
	public void addKurin(KurinUSPUPS kurinUSPUPS) {
		log.debug("add new kurin usp or ups");
		getHibernateTemplate().save(kurinUSPUPS);
		
	}
	@Override
	public List<KurinUSPUPS> findAllKurin() {
		try {
			log.debug("select findAllKurinUSPUPS");
			String query = "from KurinUSPUPS order by namekurin asc";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<KurinUSPUPS> findOneKurin(Long id) {
		try {
			log.debug("select findOneKurinUSPUPS");
			String query = "from KurinUSPUPS where id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
