package plast.org.ua.upu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.pojo.PersonDeatailPojo;
import plast.org.ua.upu.pojo.PersonKVPojo;
import plast.org.ua.upu.pojo.PersonKurinUSPPojo;
import plast.org.ua.upu.pojo.PersonStanytsyaPojo;
import plast.org.ua.upu.pojo.PersonUladStupinPojo;
import plast.org.ua.upu.pojo.PersonVyshkilPojo;
import plast.org.ua.upu.pojo.PersonVyshkilTabirPojo;
import plast.org.ua.upu.pojo.PersonsUPUPojo;
import plast.org.ua.upu.pojo.PersonsUSPUPSPojo;
import plast.org.ua.upu.table.PersonDilovodHurtok;
import plast.org.ua.upu.table.PersonDilovodKurin;
import plast.org.ua.upu.table.PersonKurinHurtok;
import plast.org.ua.upu.table.PersonKurinUSPUPS;
import plast.org.ua.upu.table.PersonStupinUlad;
import plast.org.ua.upu.table.PersonVyshkilTabir;
import plast.org.ua.upu.table.Persons;
import plast.org.ua.upu.table.PersonsKV;
import plast.org.ua.upu.table.PersonsStanytsya;
import plast.org.ua.upu.table.PersonsVyshkil;

public class PersonsDao extends HibernateDaoSupport implements IPersonsDao{
	private static final Log log = LogFactory.getLog(PersonsDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IPersonsDao instance;
	
	public static IPersonsDao getInstance() {
		if(instance == null){
			instance = (IPersonsDao)context.getBean("persons");
		}
		return instance;
	}

	@Override
	public void addPerson(Persons persons) {
		log.debug("add Person");
		getHibernateTemplate().save(persons);
	}
	@Override
	public void addPersStupUlad(PersonStupinUlad personStupinUlad) {
		log.debug("add Person Stupin Ulad");
		getHibernateTemplate().save(personStupinUlad);
		
	}
	@Override
	public void addPersKurinHurtok(PersonKurinHurtok personKurinHurtok) {
		log.debug("add Person Kurin Hurtok");
		getHibernateTemplate().save(personKurinHurtok);
		
	}
	@Override
	public void addPersonStanytsya(PersonsStanytsya personsStanytsya) {
		log.debug("add Person stanytsya");
		getHibernateTemplate().save(personsStanytsya);
		
	}
	@Override
	public void addPersonKurinUspUps(PersonKurinUSPUPS personKurinUSPUPS) {
		log.debug("add Person kurin USP UPS");
		getHibernateTemplate().save(personKurinUSPUPS);
		
	}
	@Override
	public void addPersonKV(PersonsKV personsKV) {
		log.debug("add Person KV");
		getHibernateTemplate().save(personsKV);
		
	}
	@Override
	public void addPersonVyshkil(PersonsVyshkil personsVyshkil) {
		log.debug("add Person Vyshkil");
		getHibernateTemplate().save(personsVyshkil);
		
	}
	@Override
	public void addPersonVyshkilTabir(PersonVyshkilTabir personVyshkilTabir) {
		log.debug("add Person Vyshkil Tabir");
		getHibernateTemplate().save(personVyshkilTabir);
		
	}
	@Override
	public void updatePerson(Persons persons) {
		log.debug("add update persons");
		getHibernateTemplate().update(persons);
	}
	@Override
	public void updatePersStupUlad(PersonStupinUlad personStupinUlad) {
		log.debug("add update persons stupin ulad");
		getHibernateTemplate().update(personStupinUlad);
		
	}
	@Override
	public void updatePersDilovodHurtokd(PersonDilovodHurtok personDilovodHurtok) {
		log.debug("add update persons dilovod v hurtku");
		getHibernateTemplate().update(personDilovodHurtok);
		
	}
	@Override
	public void updatePersDilovodKurin(PersonDilovodKurin personDilovodKurin) {
		log.debug("add update persons dilovod v kureni");
		getHibernateTemplate().update(personDilovodKurin);
		
	}
	@Override
	public void updatePersonStanytsya(PersonsStanytsya personsStanytsya) {
		log.debug("add update persons stanytsya");
		getHibernateTemplate().update(personsStanytsya);
		
	}
	@Override
	public void updatePersonKV(PersonsKV personsKV) {
		getHibernateTemplate().update(personsKV);
		
	}
	@Override
	public void updatePersonVyshkil(PersonsVyshkil personsVyshkil) {
		getHibernateTemplate().update(personsVyshkil);
		
	}
	@Override
	public void updatePersKurinHurtok(PersonKurinHurtok personKurinHurtok) {
		getHibernateTemplate().update(personKurinHurtok);
		
	}
	
	@Override
	public void deletePersonVyshkil(PersonsVyshkil personsVyshkil) {
		getHibernateTemplate().delete(personsVyshkil);
		
	}
	@Override
	public List<PersonsUPUPojo> findPerson(Long idkurin) {
		try {
			log.debug("find all");
			String query = "SELECT new plast.org.ua.upu.pojo.PersonsUPUPojo( "
					+ "p.id, "
					+ "s.id, "
					+ "s.nameStupin, "
					+ "p.firstName, "
					+ "p.lastName, "
					+ "p.datebirth, "
					+ "p.dateSworn, "
					+ "p.phoneNumber, "
					+ "p.email, "
					+ "p.address, "
					+ "p.jobeducation, "
					+ "h.id, "
					+ "k.id) "
					+ "FROM PersonKurinHurtok pkh, PersonStupinUlad psu, Persons p "					
					//+ "FROM PersonKurinHurtok pkh "
					//+ "JOIN pkh.persons p "
					+ "JOIN pkh.kurinUPU k "
					+ "JOIN pkh.hurtokUPU h "					
					//+ "JOIN psu.persons p "
					+ "JOIN psu.stupin s "
					+ "where pkh.persons = p.id "
					+ "and psu.persons = p.id "
					//+ "and psu.dateto is null "
					+ "and psu.datefrom in (select max(psuu.datefrom) from PersonStupinUlad psuu where psuu.persons.id = p.id) "
					+ "and k.id="+idkurin;
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
		
	}
@Override
public List<PersonsUPUPojo> findPersonSMHurtok(Long idsmurtok) {
	try {
		log.debug("find all");
		
		String query = "SELECT new plast.org.ua.upu.pojo.PersonsUPUPojo( "
				+ "p.id, "
				+ "s.id, "
				+ "s.nameStupin, "
				+ "p.firstName, "
				+ "p.lastName, "
				+ "p.datebirth, "
				+ "p.dateSworn, "
				+ "p.phoneNumber, "
				+ "p.email, "
				+ "p.address, "
				+ "p.jobeducation, "
				+ "smh.id) "
				+ "FROM PersonKurinHurtok pkh, PersonStupinUlad psu, Persons p "					
				//+ "FROM PersonKurinHurtok pkh "
				//+ "JOIN pkh.persons p "
				+ "JOIN pkh.samhurtokupu smh "				
				//+ "JOIN psu.persons p "
				+ "JOIN psu.stupin s "
				+ "where pkh.persons = p.id "
				+ "and psu.persons = p.id "
				//+ "and psu.dateto is null "
				+ "and psu.datefrom in (select max(psuu.datefrom) from PersonStupinUlad psuu where psuu.persons.id = p.id) "
				+ "and smh.id="+idsmurtok;
		System.out.println(query);
		return getHibernateTemplate().find(query);
	} catch (RuntimeException e) {
		throw e;
	}
	
}
	@Override
	public List<PersonsUSPUPSPojo> findPersonUSPUPS(Long idstan) {		
		try {
			log.debug("find all USP UPS perons");
			String query = "SELECT new plast.org.ua.upu.pojo.PersonsUSPUPSPojo( "
					+ "p.id, "
					+ "p.lastName, "
					+ "p.firstName, "
					+ "s.nameStupin ) "
					+ "FROM PersonStupinUlad psu, PersonsStanytsya ps "
					+ "JOIN psu.persons p "
					+ "JOIN psu.ulad u "
					+ "JOIN psu.stupin s "
					+ "JOIN ps.persons pr "					
					+ "WHERE u.nameulad in ('УСП','УПС') "
					+ "AND pr.id = p.id "
					+ "AND ps.stanytsya.id="+idstan+" "
					+ "order by p.lastName, p.firstName asc";
			
			return getHibernateTemplate().find(query);
		}catch (RuntimeException e) {
			throw e;
		}
		
	}
	@Override
	public List<Persons> findOnePersons(Long id) {
		try {
			log.debug("find all USP UPS perons");
			String query = "from Persons where id="+id;
			return getHibernateTemplate().find(query);
		}catch (RuntimeException e) {
			throw e;
		}
	}
	public List<PersonsVyshkil> findPersVyshkil(Long personid) {
		try {
			log.debug("find all  perons vyshkil ");
			String query = "from PersonsVyshkil pv where pv.persons.id="+personid;
			System.out.println(query);
			return getHibernateTemplate().find(query);
		}catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<PersonVyshkilTabir> findPersVyshkilTabir(Long personid) {
		try {
			log.debug("find all  perons vyshkil ");
			String query = "from PersonVyshkilTabir pvt where pvt.persons.id="+personid;
			System.out.println(query);
			return getHibernateTemplate().find(query);
		}catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public void updatePersonVyshkilTabir(PersonVyshkilTabir personVyshkilTabir) {
		getHibernateTemplate().update(personVyshkilTabir);
		
	}
	@Override
	public List<PersonsUPUPojo> findOnePersonsHurtok(Long personid) {
		try {
			log.debug("find all");
			String query = "SELECT new plast.org.ua.upu.pojo.PersonsUPUPojo( "
					+ "p.id, "
					+ "s.id, "
					+ "s.nameStupin, "
					+ "p.firstName, "
					+ "p.lastName, "
					+ "p.datebirth, "
					+ "p.dateSworn, "
					+ "p.phoneNumber, "
					+ "p.email, "
					+ "p.address, "
					+ "p.jobeducation, "
					+ "h.id, "
					+ "k.id) "
					+ "FROM PersonKurinHurtok pkh, PersonStupinUlad psu, Persons p "					
					//+ "FROM PersonKurinHurtok pkh "
					//+ "JOIN pkh.persons p "
					+ "JOIN pkh.kurinUPU k "
					+ "JOIN pkh.hurtokUPU h "
					
					//+ "JOIN psu.persons p "
					+ "JOIN psu.stupin s "
					+ "where pkh.persons = p.id "
					+ "and psu.persons = p.id "
					+ "and psu.dateto is null "
					+ "and p.id="+personid;
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}		
	}
	@Override
	public List<PersonsUPUPojo> findOnePersonsSMHurtok(Long personid) {
		try {
			log.debug("find all");
			String query = "SELECT new plast.org.ua.upu.pojo.PersonsUPUPojo( "
					+ "p.id, "
					+ "s.id, "
					+ "s.nameStupin, "
					+ "p.firstName, "
					+ "p.lastName, "
					+ "p.datebirth, "
					+ "p.dateSworn, "
					+ "p.phoneNumber, "
					+ "p.email, "
					+ "p.address, "
					+ "p.jobeducation, "
					+ "smh.id) "
					+ "FROM PersonKurinHurtok pkh, PersonStupinUlad psu, Persons p "					
					//+ "FROM PersonKurinHurtok pkh "
					//+ "JOIN pkh.persons p "
					+ "JOIN pkh.samhurtokupu smh "
					//+ "JOIN psu.persons p "
					+ "JOIN psu.stupin s "
					+ "where pkh.persons = p.id "
					+ "and psu.persons = p.id "
					+ "and psu.dateto is null "
					+ "and p.id="+personid;
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}		
	}
	@Override
	public List<PersonKurinHurtok> findHurtokKurin(Long personid) {
		try {
			log.debug(" find ONE person kurin hurtok");
			String sql = "from PersonKurinHurtok pkh where pkh.dateto is null and pkh.persons.id="+personid;
			return getHibernateTemplate().find(sql);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	@Override
	public List<PersonsStanytsya> findPersonStanytsya(Long personid) {
		try {
			log.debug(" find person stanystya");
			String sql = "from PersonsStanytsya ps where ps.persons.id="+personid;
			return getHibernateTemplate().find(sql);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<PersonStupinUlad> findPersStupUlad(Long personid) {
		try {
			log.debug(" find person ulad");
			String sql = "from PersonStupinUlad psu where psu.persons.id="+personid;
			return getHibernateTemplate().find(sql);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<PersonKurinUSPUPS> findPersKurinUSPUPS(Long personid) {
		try {
			log.debug(" find person kurin");
			String sql = "from PersonKurinUSPUPS pkus where pkus.persons.id="+personid;
			return getHibernateTemplate().find(sql);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public void updatePersonKurinUSPUPS(PersonKurinUSPUPS personKurinUSPUPS) {
		log.debug("update person kurin");
		getHibernateTemplate().update(personKurinUSPUPS);
		
	}
	@Override
	public List<PersonsKV> findPersKV(Long personid) {
		try {
			log.debug(" find person kv ");
			String sql = "from PersonsKV pkv where pkv.persons.id="+personid;
			return getHibernateTemplate().find(sql);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<PersonDeatailPojo> findOneDetailPerson(Long idperson) {
		try{
			log.debug("find only One person detail");
			String sql = "SELECT new plast.org.ua.upu.pojo.PersonDeatailPojo( "
					+ "p.id, "
					+ "p.firstName, "
					+ "p.lastName, "
					+ "p.datebirth, "
					//+ "u.nameulad, "
					//+ "u.id, "
					//+ "s.nameStupin, "
					//+ "s.id, "
					+ "st.namestan, "
					+ "st.id "
					//+ "ks.namekurin, "
					//+ "ks.id "
					+ ") " /*, "
					+ "kvv.name, "
					+ "kvv.id, "
					+ "vt.namevyshkiltabir, "
					+ "vt.id, "
					+ "p.address, "
					+ "p.jobeducation, "
					+ "p.phoneNumber, "
					+ "p.email, "
					+ "p.dateSworn, "
					+ "min(minpsu.datefrom) ) "*/
					+ "from Persons p, PersonsStanytsya ps "			/* Спочатку таблиця з порожнім значенням*/		
					/*+ "PersonStupinUlad psu, " 
					+ "PersonKurinUSPUPS pk "/*, "
					+ "PersonsKV pkv, "
					+ "PersonVyshkilTabir pvt, "					   
					+ "PersonStupinUlad minpsu "*/
					+ "INNER JOIN ps.persons as p1 ON p1.id = p.id "
				/*	+ "RIGHT OUTER JOIN psu.persons p2 "
					+ "RIGHT OUTER JOIN pk.persons p3 "
					/*+ "RIGHT JOIN pkv.persons p3 "
					+ "RIGHT JOIN pvt.persons p4 "
					+ "RIGHT JOIN minpsu.persons p5 "*/
					
				//	+ "LEFT JOIN psu.ulad u "
				//	+ "LEFT JOIN psu.stupin s "				
					+ "LEFT JOIN ps.stanytsya st "
				//	+ "LEFT JOIN pk.kurinUSPUPS ks "
					/*+ "LEFT JOIN pkv.kv kvv "
					+ "LEFT JOIN pvt.vyshkilTabir vt "*/					
					
					//+ "where psu.dateto is null "
					//+ "where p.id = p1.id "
					//+ "and p2.id = p.id "					
					//+ "and p3.id = p.id "
					/*+ "and p4.id = p.id "
					+ "and p5.id = p.id "*/
					//+ "and minpsu.persons = p.id "
					+ "where p.id="+idperson+" "
					/*+ "group by p.id, p.firstName, p.lastName, p.datebirth, "
					+ "u.nameulad, u.id, s.nameStupin, s.id, st.namestan, st.id, "
					+ "ks.namekurin, ks.id, kvv.name, kvv.id, vt.namevyshkiltabir, vt.id, "
					+ "p.address, p.jobeducation, p.phoneNumber, p.email, p.dateSworn"*/;
			return getHibernateTemplate().findByNamedQuery(sql);
			
		} catch(RuntimeException e){
			throw e;
		}
	}

	@Override
	public List<PersonStanytsyaPojo> findOnePersonStanytsya(Long personid) {
		log.debug("find stanytsya person");
		try {
			//select sub, reg from Substation sub inner join fetch Region reg sub.region = reg.id
			String sql="SELECT new plast.org.ua.upu.pojo.PersonStanytsyaPojo( "
					+ "p.id, "					
					+ "st.id, "
					+ "st.namestan ) "					
					+ "from PersonsStanytsya ps "					
					+ "RIGHT JOIN ps.persons p "					
					+ "LEFT JOIN ps.stanytsya st "															
					+ "where p.id="+personid;
			return getHibernateTemplate().find(sql);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<PersonUladStupinPojo> findOnePersonUladStupin(Long personid) {
		log.debug("find ulad stupin person");
		try {
			String sql="SELECT new plast.org.ua.upu.pojo.PersonUladStupinPojo( "
					+ "p.id, "					
					+ "u.id, "
					+ "u.nameulad, "
					+ "s.id, "
					+ "s.nameStupin, "
					+ "min(minpsu.datefrom) ) "					
					+ "from PersonStupinUlad psu, PersonStupinUlad minpsu "					
					+ "RIGHT JOIN psu.persons p "										
					+ "LEFT JOIN psu.ulad u "
					+ "LEFT JOIN psu.stupin s "															
					+ "where minpsu.persons = p.id "
					+ "and p.id="+personid
					+ " "
					+ "group by p.id, u.id, u.nameulad, s.id, s.nameStupin ";
			return getHibernateTemplate().find(sql);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<PersonKVPojo> findOnePersonKV(Long personid) {
		log.debug("find kv person");
		try {
			String sql="SELECT new plast.org.ua.upu.pojo.PersonKVPojo( "
					+ "p.id, "					
					+ "k.id, "
					+ "k.name ) "					
					+ "from PersonsKV pkv "					
					+ "RIGHT JOIN pkv.persons p "										
					+ "LEFT JOIN pkv.kv k "					
					+ "where p.id="+personid;
			return getHibernateTemplate().find(sql);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<PersonKurinUSPPojo> findOnePersonKurinUSP(Long personid) {
		log.debug("find kurin usp person");
		try {
			String sql="SELECT new plast.org.ua.upu.pojo.PersonKurinUSPPojo( "
					+ "p.id, "					
					+ "kusp.id, "
					+ "kusp.namekurin ) "					
					+ "from PersonKurinUSPUPS pkusp "					
					+ "RIGHT JOIN pkusp.persons p "										
					+ "LEFT JOIN pkusp.kurinUSPUPS kusp "					
					+ "where p.id="+personid;
			return getHibernateTemplate().find(sql);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<PersonVyshkilTabirPojo> findOnePersonVyshkilTabir(Long personid) {
		log.debug("find vyshkil tabir usp person");
		try {
			String sql="SELECT new plast.org.ua.upu.pojo.PersonVyshkilTabirPojo( "
					+ "p.id, "					
					+ "vt.id, "
					+ "vt.namevyshkiltabir ) "					
					+ "from PersonVyshkilTabir pvt "					
					+ "RIGHT JOIN pvt.persons p "										
					+ "LEFT JOIN pvt.vyshkilTabir vt "					
					+ "where p.id="+personid;
			return getHibernateTemplate().find(sql);
		} catch (RuntimeException e) {
			throw e;
		}		
	}
	@Override
	public List<PersonVyshkilPojo> findOnrPersonVyshkil(Long personid) {
		log.debug("find vyshkil  usp person");
		try {
			String sql="SELECT new plast.org.ua.upu.pojo.PersonVyshkilPojo( "
					+ "p.id, "					
					+ "v.id, "
					+ "v.namevyshkil, "
					+ "v.description ) "					
					+ "from PersonsVyshkil pv "					
					+ "RIGHT JOIN pv.persons p "										
					+ "LEFT JOIN pv.vyshkil v "					
					+ "where p.id="+personid;
			return getHibernateTemplate().find(sql);
		} catch (RuntimeException e) {
			throw e;
		}		
	}
	public static void main(String[] args) {
		
		PersonsDao a = new PersonsDao();
				
		IPersonsDao personDao = PersonsDao.getInstance();
		List<PersonStanytsyaPojo> liststanpers;
		liststanpers = personDao.findOnePersonStanytsya(7L);
		for (PersonStanytsyaPojo personStanytsyaPojo : liststanpers) {
			System.out.println(personStanytsyaPojo.getStanname()+" "+
		personStanytsyaPojo.getPersonid()+"\t"+personStanytsyaPojo.getStanid()+" *** ");
		}
		
		List<PersonUladStupinPojo> listus = personDao.findOnePersonUladStupin(7L);
		for (PersonUladStupinPojo personUladStupinPojo : listus) {
			System.out.println(personUladStupinPojo.getPersonid()+ " "+personUladStupinPojo.getNameulad()+" "
					+ " "+personUladStupinPojo.getNamestupin()+" "+personUladStupinPojo.getDatestart());
		}
		
		List<PersonKVPojo> listlv = personDao.findOnePersonKV(7L);
		for (PersonKVPojo personKVPojo : listlv) {
			System.out.println(personKVPojo.getPersonid()+"  "+ personKVPojo.getNamekv());
		}
		
		List<PersonKurinUSPPojo> listkurinusp = personDao.findOnePersonKurinUSP(7L);
				for (PersonKurinUSPPojo personKurinUSPPojo : listkurinusp) {
					System.out.println(personKurinUSPPojo.getNamekurinusp());
				}
		List<PersonVyshkilTabirPojo> listvyshtab = personDao.findOnePersonVyshkilTabir(7L);
		for (PersonVyshkilTabirPojo personVyshkilTabirPojo : listvyshtab) {
			System.out.println(personVyshkilTabirPojo.getVyshkiltabirname());
		}
		
		List<PersonVyshkilPojo> listpervysh = personDao.findOnrPersonVyshkil(7L);
		for (PersonVyshkilPojo personVyshkilPojo : listpervysh) {
			System.out.println(personVyshkilPojo.getVyshkilname()+" "+personVyshkilPojo.getVyshkildescription());
		}
	}
}
