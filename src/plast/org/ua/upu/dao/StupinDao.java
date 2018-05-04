package plast.org.ua.upu.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IStupinDao;
import plast.org.ua.upu.pojo.PersonsCountStupinPojo;
import plast.org.ua.upu.pojo.PersonsUPUChangeStupinPojo;
import plast.org.ua.upu.table.PersonStupinUlad;
import plast.org.ua.upu.table.Stupin;

public class StupinDao extends HibernateDaoSupport implements IStupinDao{
	private static final Log log = LogFactory.getLog(StupinDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IStupinDao instance;
	
	public static IStupinDao getInstance() {
		if(instance == null){
			instance = (IStupinDao)context.getBean("stupin");
		}
		return instance;
	}

	@Override
	public void addStupin(Stupin stupin) {
		log.debug("add stupin");
		getHibernateTemplate().save(stupin);
	}

	@Override
	public List<Stupin> findAllStupin() {
		try {
			log.debug("find Stupin");
			String query = "from Stupin";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Override
	public List<Stupin> findStupin(Long id) {
		try {
			log.debug("find Stupin");
			String query = "from Stupin where id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<Stupin> findStupinUlad(Long idUlad) {
		try {
			log.debug("find Stupin");
			String query = "from Stupin where ulad="+idUlad;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<Stupin> findStupinNeim(String nameStupin) {
		try {
			log.debug("find Stupin");
			String query = "from Stupin where nameStupin='"+nameStupin+"'";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<PersonsUPUChangeStupinPojo> findStupinChange(Long personid) {
		try {
			log.debug("find Stupin");
			String query = "SELECT new plast.org.ua.upu.pojo.PersonsUPUChangeStupinPojo( "
					+ "psu.id, "
					+ "psu.datefrom, "
					+ "psu.dateto, "
					+ "s.nameStupin, "
					+ "s.id, "
					+ "u.nameulad, "
					+ "u.id, "
					+ "p.id ) "
					+ "FROM PersonStupinUlad psu "
					+ "JOIN psu.persons p "
					+ "JOIN psu.ulad u "
					+ "JOIN psu.stupin s "
					+ "where p.id="+personid
					+ " order by psu.datefrom asc";			
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<PersonStupinUlad> findStartPerson(Long personid) {
		try {
			log.debug("find Stupin");
			String query = "from PersonStupinUlad p  where p.persons.id="+personid;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<PersonStupinUlad> findOnlyOneStupin(Long id) {
		try {
			log.debug("find Stupin");
			String query = "from PersonStupinUlad p  where p.id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<PersonsCountStupinPojo> findCountStupin(Long idkurin, String namestupin, String ondate) {
		try {
			log.debug(" find count stupin");
			String query = "SELECT new plast.org.ua.upu.pojo.PersonsCountStupinPojo( "
					+ "psu.id ) "
					+ "FROM PersonKurinHurtok pkh, PersonStupinUlad psu, Persons p "
					+ "JOIN pkh.kurinUPU k "
					+ "JOIN psu.stupin s "
					+ "where pkh.persons = p.id "
					+ "and psu.persons = p.id "
					+ "and psu.datefrom <=STR_TO_DATE('"+ondate+"', '%d.%m.%Y') "
					+ "and (psu.dateto > STR_TO_DATE('"+ondate+"', '%d.%m.%Y') or psu.dateto is null) "
					+ "and s.nameStupin = '"+namestupin+"' "
					+ "and k.id="+idkurin;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<PersonsCountStupinPojo> findCountStupinSMHurt(Long idsmHurt, String namestupin, String ondate) {
		try {
			log.debug(" find count stupin");
			String query = "SELECT new plast.org.ua.upu.pojo.PersonsCountStupinPojo( "
					+ "psu.id ) "
					+ "FROM PersonKurinHurtok pkh, PersonStupinUlad psu, Persons p "
					+ "JOIN pkh.samhurtokupu k "
					+ "JOIN psu.stupin s "
					+ "where pkh.persons = p.id "
					+ "and psu.persons = p.id "
					+ "and psu.datefrom <=STR_TO_DATE('"+ondate+"', '%d.%m.%Y') "
					+ "and (psu.dateto > STR_TO_DATE('"+ondate+"', '%d.%m.%Y') or psu.dateto is null) "
					+ "and s.nameStupin = '"+namestupin+"' "
					+ "and k.id="+idsmHurt;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
