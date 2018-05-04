package plast.org.ua.upu.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IDilovedennyaDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.pojo.PersonsUPUChangeDilovodHurtokPojo;
import plast.org.ua.upu.pojo.PersonsUPUChangeDilovodKurinPojo;
import plast.org.ua.upu.pojo.ProvidKurinPojo;
import plast.org.ua.upu.table.DilovedennyaHurtokUPU;
import plast.org.ua.upu.table.DilovedennyaKurinUPU;
import plast.org.ua.upu.table.PersonDilovodHurtok;
import plast.org.ua.upu.table.PersonDilovodKurin;

public class DilovedennyaDao extends HibernateDaoSupport implements IDilovedennyaDao{
	private static final Log log = LogFactory.getLog(DilovedennyaDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IDilovedennyaDao instance;
	
	public static IDilovedennyaDao getInstance() {
		if(instance == null){
			instance = (IDilovedennyaDao)context.getBean("dilovod");
		}
		return instance;
	}

	@Override
	public void addDilovodHurtok(DilovedennyaHurtokUPU dilovedennyaHurtokUPU) {
		log.debug("add new dilovedenna in hurtok");
		getHibernateTemplate().save(dilovedennyaHurtokUPU);
	}
	@Override
	public void addPersonDilovodHurtok(PersonDilovodHurtok dilovodHurtok) {
		log.debug("add new dilovedenna persons in hurtok");
		getHibernateTemplate().save(dilovodHurtok);
		
	}
	@Override
	public List<DilovedennyaHurtokUPU> findAllDilovedennyaHurtok() {
		try {
			log.debug("find all dilovedennya");
			String query = "from DilovedennyaHurtokUPU";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}		
	}
	@Override
	public List<DilovedennyaHurtokUPU> findOneDilovedennyaHurtok(Long id) {
		try {
			log.debug("find all dilovedennya");
			String query = "from DilovedennyaHurtokUPU where id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	@Override
	public List<PersonsUPUChangeDilovodHurtokPojo> findAllDilovodHurtokPerson(Long personid) {
		try {
			log.debug("find all PersonDilovodHurtok");
			String query = "SELECT new plast.org.ua.upu.pojo.PersonsUPUChangeDilovodHurtokPojo( "
					+ "pdh.id, "
					+ "d.id, "
					+ "p.id, "
					+ "k.id, "
					+ "h.id, "
					+ "pdh.datebegin, "
					+ "pdh.dateend, "
					+ "h.nameHurtok, "
					+ "d.nameDilovodHurt ) "
					+ "FROM PersonDilovodHurtok pdh "
					+ "JOIN pdh.persons p "
					+ "JOIN pdh.hurtok h "
					+ "JOIN pdh.kurin k "
					+ "JOIN pdh.dilovodhurtok d "
					+ "where p.id="+personid;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	@Override
	public List<PersonsUPUChangeDilovodHurtokPojo> findAllDilovodSMHurtokPerson(Long personid) {
		try {
			log.debug("find all PersonDilovodHurtok");
			String query = "SELECT new plast.org.ua.upu.pojo.PersonsUPUChangeDilovodHurtokPojo( "
					+ "pdh.id, "
					+ "d.id, "
					+ "p.id, "
					+ "smh.id, "
					+ "pdh.datebegin, "
					+ "pdh.dateend, "
					+ "smh.nameSamHurtok, "
					+ "d.nameDilovodHurt ) "
					+ "FROM PersonDilovodHurtok pdh "
					+ "JOIN pdh.persons p "
					+ "JOIN pdh.smhurtok smh "
					+ "JOIN pdh.dilovodhurtok d "
					+ "where p.id="+personid;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	//NOT WORKING 
	@Override
	public List<PersonDilovodHurtok> findDilovodHurtok(Long personid) {
		try {
			log.debug("find all dilovedennya");
			String query = "pdh.id, "
					+ "pdh.dilovodhurtok.id, "
					+ "pdh.persons.id, "
					+ "pdh.kurin.id, "
					+ "pdh.hurtok.id, "
					+ "pdh.datebegin, "
					+ "pdh.dateend, "
					+ "pdh.hurtok.nameHurtok, "
					+ "pdh.dilovodhurtok.nameDilovodHurt "
					+ "from PersonDilovodHurtok pdh where pdh.persons.id="+personid
					+ " order by pdh.datebegin asc";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	@Override
	public List<PersonDilovodHurtok> findOnlyOneDilovodHurtok(Long id) {
		try {
			log.debug("find all dilovedennya");
			String query = "from PersonDilovodHurtok pdh where pdh.id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	@Override
	public List<PersonDilovodHurtok> findPersonKurinDilovodHurtok(Long kurinid) {
		try {
			log.debug("find all dilovedennya");
			String query = "from PersonDilovodHurtok pdh where pdh.dateend is null and pdh.kurin.id="+kurinid;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	@Override
	public List<PersonDilovodHurtok> findPersonSMhurtokDilovodHurtok(Long smhurtokid) {
		try {
			log.debug("find all dilovedennya");
			String query = "from PersonDilovodHurtok pdh where pdh.dateend is null and pdh.smhurtok.id="+smhurtokid;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	@Override
	public void addDilovodKurin(DilovedennyaKurinUPU dilovedennyaKurinUPU) {
		log.debug("add dilovedennya kurin");
		getHibernateTemplate().save(dilovedennyaKurinUPU);
	}
	@Override
	public List<DilovedennyaKurinUPU> findAllDilovedennyaKurin() {
		try {
			log.debug("find all DilovedennyaKurinUPU");
			String query = "from DilovedennyaKurinUPU";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<DilovedennyaKurinUPU> findOneDilovedennyaKurin(Long id) {
		try {
			log.debug("find all DilovedennyaKurinUPU");
			String query = "from DilovedennyaKurinUPU where id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public void addPersonDilovodKurin(PersonDilovodKurin dilovodKurin) {
		log.debug("add person dilovod kurin");
		getHibernateTemplate().save(dilovodKurin);
		
	}

	@Override
	public List<PersonsUPUChangeDilovodKurinPojo> findAllDilovodKurinPerson(Long personid) {
		try {
			log.debug("find all PersonDilovodKurin");
			String query = "SELECT new plast.org.ua.upu.pojo.PersonsUPUChangeDilovodKurinPojo( "
					+ "pdk.id, "
					+ "d.id, "
					+ "p.id, "
					+ "k.id, "			
					+ "pdk.datebegin, "
					+ "pdk.dateend, "					
					+ "d.nameDilovod ) "
					+ "FROM PersonDilovodKurin pdk "
					+ "JOIN pdk.persons p "					
					+ "JOIN pdk.kurin k "
					+ "JOIN pdk.dilovodkurin d "
					+ "where p.id="+personid;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	@Override
	public List<PersonDilovodKurin> findOnlyOneDilovodKurin(Long id) {
		try {
			log.debug("find all dilovedennya");
			String query = "from PersonDilovodKurin pdk where pdk.id="+id;
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	@Override
	public List<ProvidKurinPojo> findProvidKurin(Long kurinid) {
		try {
			log.debug("provid kurennya");
			String query="SELECT new plast.org.ua.upu.pojo.ProvidKurinPojo( "
					+ "p.id, "
					+ "d.nameDilovod, "
					+ "s.nameStupin, "
					+ "p.lastName, "
					+ "p.firstName, "
					+ "p.phoneNumber, "
					+ "p.email, "
					+ "p.datebirth ) "
					+ "FROM PersonDilovodKurin pdk, PersonStupinUlad psu, Persons p "
					+ "JOIN pdk.dilovodkurin d "
					+ "JOIN psu.stupin s "
					+ "where pdk.persons = p.id "
					+ "and psu.persons = p.id "
					+ "and psu.dateto is null "
					+ "and pdk.dateend is null "
					+ "and pdk.kurin.id = "+kurinid
					+" order by d.id asc";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
