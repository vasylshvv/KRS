package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IKVDao;
import plast.org.ua.upu.table.KV;


public class KVDao extends HibernateDaoSupport implements IKVDao{
	private static final Log log = LogFactory.getLog(KVDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IKVDao instance;
	
	public static IKVDao getInstance() {
		if(instance == null){
			instance = (IKVDao)context.getBean("kv");
		}
		return instance;
	}
	@Override
	public void addKV(KV kv) {
		log.debug("add kv");
		getHibernateTemplate().save(kv);
	}
	@Override
	public List<KV> findAll() {
		try {
			log.debug("finad all kv");
			String query = "from KV";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			log.debug(e);
			throw e;
		}
	}
	@Override
	public List<KV> findKV(Long id) {
		try {
			log.debug("finad all kv");
			String query = "from KV where id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			log.debug(e);
			throw e;
		}
	}
}
