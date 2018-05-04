package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import plast.org.ua.upu.idao.IVyshkilTabirDao;
import plast.org.ua.upu.table.VyshkilTabir;

public class VyshkilTabirDao extends HibernateDaoSupport implements IVyshkilTabirDao{
	private static final Log log = LogFactory.getLog(VyshkilTabirDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IVyshkilTabirDao instance;
	
	
	public static IVyshkilTabirDao getInstance() {
		if(instance == null){
			instance = (IVyshkilTabirDao)context.getBean("vyshktabir");
		}
		return instance;
	}
	@Override
	public void addVyshkilTabir(VyshkilTabir vyshkilTabir) {
		log.debug("add vyshkiltabir");
		getHibernateTemplate().save(vyshkilTabir);
	}
	@Override
	public List<VyshkilTabir> findAllVyshkilTabir() {
		try {
			log.debug("find vyshkiltabir ");
			String query = "from VyshkilTabir";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<VyshkilTabir> findOneVyshkilTabir(Long id) {
		try {
			log.debug("find vyshkiltabir one");
			String query = "from VyshkilTabir where id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
