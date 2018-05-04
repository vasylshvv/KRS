package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.table.KurinUPU;

public class KurinUPUDao extends HibernateDaoSupport implements IKurinUPUDao{
	private static final Log log = LogFactory.getLog(KurinUPUDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IKurinUPUDao instance;
	
	public static IKurinUPUDao getInstance() {
		if(instance == null){
			instance = (IKurinUPUDao)context.getBean("kurinupu");
		}
		return instance;
	}
	@Override
	public void addKurinUPU(KurinUPU kurinUPU) {
		log.debug("add kurin");
		getHibernateTemplate().save(kurinUPU);
		
	}
	@Override
	public void updateKurinUPU(KurinUPU kurinUPU) {
		log.debug("update kurin");
		getHibernateTemplate().update(kurinUPU);
		
	}
	@Override
	public List<KurinUPU> findAllKurinUPU() {
		try {
			log.debug("select findAllKurinUPU");
			String query = "from KurinUPU order by numberkurin asc";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
		
	}
	@Override
	public List<KurinUPU> findOneKurinUPU(Long id) {
		try {
			log.debug("select findCountKurinUPU");
			String query = "from KurinUPU where id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<KurinUPU> findStanytsyaKurinUPU(Long stanytsyaId) {
		try {
			log.debug("select findStanytsyaKurinUPU");
			String query = "from KurinUPU where stanytsyaid="+stanytsyaId+ " order by numberKurin asc";
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<KurinUPU> findLvOkruhaStanytsyaKurinUPU() {
		try {
			log.debug("select findStanytsyaKurinUPU");
			String query = "from KurinUPU where stanytsyaid in(1,2,8,9,10,14,17,19,20,23,24,27,34,35,36,38,41,42,45,46) order by numberKurin asc";
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
