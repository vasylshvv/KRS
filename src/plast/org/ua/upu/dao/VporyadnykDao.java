package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IVporyadnykDao;
import plast.org.ua.upu.idao.IZvyazkovyyDao;
import plast.org.ua.upu.pojo.VporyadnykPojo;
import plast.org.ua.upu.pojo.VporyadnykSamurtokPojo;
import plast.org.ua.upu.table.Vporyadnyk;

public class VporyadnykDao extends HibernateDaoSupport implements IVporyadnykDao{
	private static final Log log = LogFactory.getLog(VporyadnykDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IVporyadnykDao instance;
	
	public static IVporyadnykDao getInstance() {
		if(instance == null){
			instance = (IVporyadnykDao)context.getBean("vporyad");
		}
		return instance;
	}

	@Override
	public void addVporyadnyk(Vporyadnyk vporyadnyk) {
		log.debug("add vporyadnyk");
		getHibernateTemplate().save(vporyadnyk);
		
	}
	@Override
	public void update(Vporyadnyk vporyadnyk) {
		log.debug("update vporyadnyk");
		getHibernateTemplate().update(vporyadnyk);
		
	}
	@Override
	public List<Vporyadnyk> findVporyadnykForHurtok(Long idhurtok, Long idkurin) {
		try {

			log.debug("find vporyadnyk");
			String query = "from Vporyadnyk v "
					+ "where v.kurinupu.id="+idkurin
					+" and v.hurtokupu.id="+idhurtok
					+" and v.dateend is null";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<Vporyadnyk> findVporyadnykForSamHurtok(Long idsamhurtok) {
		try {

			log.debug("find vporyadnyk");
			String query = "from Vporyadnyk v "
					+ "where v.samhurtokupu.id="+idsamhurtok
					+" and v.dateend is null";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<Vporyadnyk> findVporyadnykForHurtok(Long idhurtok, Long idkurin, Long idperson) {
		try {

			log.debug("find vporyadnyk");
			String query = "from Vporyadnyk v "
					+ "where v.kurinupu.id="+idkurin
					+" and v.hurtokupu.id="+idhurtok
					+" and v.persons.id="+idperson
					+" and v.dateend is null";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<Vporyadnyk> findVporyadnykForSamHurtok(Long idsamhurtok, Long idperson) {
		try {

			log.debug("find vporyadnyk");
			String query = "from Vporyadnyk v "
					+ "where v.samhurtokupu.id="+idsamhurtok
					+" and v.persons.id="+idperson
					+" and v.dateend is null";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<VporyadnykPojo> findAllVporyadnyk(Long idkurin) {
		try {

			log.debug("find vporyadnyk");
			String query = "SELECT new plast.org.ua.upu.pojo.VporyadnykPojo( " 
							+ "v.id, " 
							+ "p.lastName, "
							+ "p.firstName, " 
							+ "s.nameStupin," 
							+ "v.datebegin, " 
							+ "p.id, "
							+ "h.id ) "
							+ "FROM PersonStupinUlad psu, Vporyadnyk v " 
							+ "JOIN psu.persons p " 
							+ "JOIN psu.ulad u "
							+ "JOIN psu.stupin s " 
							+ "JOIN v.kurinupu k "
							+ "JOIN v.hurtokupu h " 
							+ "where p.id = v.persons " 
							+ "and v.dateend is null "
							+ "and psu.dateto is null " 
							+ "and k.id=" + idkurin;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<VporyadnykSamurtokPojo> findVporyadnykSamHurtok(Long idsamhurtok) {
		try {

			log.debug("find vporyadnyk");
			String query = "SELECT new plast.org.ua.upu.pojo.VporyadnykSamurtokPojo( " 
							+ "v.id, " 
							+ "p.lastName, "
							+ "p.firstName, " 
							+ "s.nameStupin," 
							+ "v.datebegin, " 
							+ "p.id, "
							+ "h.id, "
							+ "v.hashcode) "
							+ "FROM PersonStupinUlad psu, Vporyadnyk v " 
							+ "JOIN psu.persons p " 
							+ "JOIN psu.ulad u "
							+ "JOIN psu.stupin s " 						
							+ "JOIN v.samhurtokupu h " 
							+ "where p.id = v.persons " 
							+ "and v.dateend is null "
							+ "and psu.dateto is null " 
							+ "and h.id=" + idsamhurtok;
			System.out.println("select samhurtok vporyadnyk: "+query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
