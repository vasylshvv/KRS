package plast.org.ua.upu.dao;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IQuarterlyReportsUPUDao;
import plast.org.ua.upu.table.QuarterlyReportsUPU;

public class QuarterlyReportsUPUDao extends HibernateDaoSupport implements IQuarterlyReportsUPUDao{

	private static final Log log = LogFactory.getLog(UladDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IQuarterlyReportsUPUDao instance;
	
	public static IQuarterlyReportsUPUDao getInstance() {
		if(instance == null){
			instance = (IQuarterlyReportsUPUDao)context.getBean("quarterly");
		}
		return instance;
	}
	
	@Override
	public void addQuarterlyReport(QuarterlyReportsUPU quarterlyReportsUPU) {
		getHibernateTemplate().save(quarterlyReportsUPU);
		
	}

	@Override
	public void updateQuartReportKurin(QuarterlyReportsUPU quarterlyReportsUPU) {
		getHibernateTemplate().update(quarterlyReportsUPU);
		
	}

	@Override
	public List<QuarterlyReportsUPU> findQuartReportKurin(Long idkurin, String sysdate) {
		try {
			log.debug("find on date");
			String query = "from QuarterlyReportsUPU "
						 + "where kurinupuid="+idkurin+" "
						 + "and datebegin <= STR_TO_DATE('"+sysdate+"', '%d.%m.%Y') "
						 + "and dateend >= STR_TO_DATE('"+sysdate+"', '%d.%m.%Y') ";	
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	@Override
	public List<QuarterlyReportsUPU> findQuartReportSamHurtok(Long idsmhurtok, String sysdate) {
		try {
			log.debug("find on date");
			String query = "from QuarterlyReportsUPU "
						 + "where samhurtokupuid="+idsmhurtok+" "
						 + "and datebegin <= STR_TO_DATE('"+sysdate+"', '%d.%m.%Y') "
						 + "and dateend >= STR_TO_DATE('"+sysdate+"', '%d.%m.%Y') ";	
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	@Override
	public List<QuarterlyReportsUPU> quaterCountKurin(String ondate, Long kurinid) {
		try {
			log.debug("find on date");
			String query = "from QuarterlyReportsUPU "
						 + "where kurinupuid="+kurinid+" "
						 + "and YEAR(datebegin) = YEAR(STR_TO_DATE('"+ondate+"', '%d.%m.%Y')) "
						 + "and YEAR(dateend) = YEAR(STR_TO_DATE('"+ondate+"', '%d.%m.%Y')) ";	
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	@Override
	public List<QuarterlyReportsUPU> quaterCountsmHurtok(String ondate, Long smhurtokid) {
		try {
			log.debug("find on date");
			String query = "from QuarterlyReportsUPU "
						 + "where samhurtokupuid="+smhurtokid+" "
						 + "and YEAR(datebegin) = YEAR(STR_TO_DATE('"+ondate+"', '%d.%m.%Y')) "
						 + "and YEAR(dateend) = YEAR(STR_TO_DATE('"+ondate+"', '%d.%m.%Y')) ";	
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	@Override
	public List<QuarterlyReportsUPU> findOnlyDateKurin(String ondate) {
		try {
			log.debug("find on date");
			String query = "from QuarterlyReportsUPU "
						 + "where datebegin = '"+ondate+"' "
						 		+ " and kurinupuid is not null";	
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	@Override
	public List<QuarterlyReportsUPU> findOnlyDatesmHurtok(String ondate) {
		try {
			log.debug("find on date");
			String query = "from QuarterlyReportsUPU "
						 + "where datebegin = '"+ondate+"' "
						 		+ " and samhurtokupuid is not null";	
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<QuarterlyReportsUPU> findOnlyDateEachKurin(String ondate, Long idkurin) {
		try {
			log.debug("find on date");
			String query = "from QuarterlyReportsUPU "
						 + "where datebegin = '"+ondate+"' "
						 		+ "and kurinupuid="+idkurin;	
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	@Override
	public List<QuarterlyReportsUPU> findOnlyDateEachSMHurtok(String ondate, Long idhurtok) {
		try {
			log.debug("find on date");
			String query = "from QuarterlyReportsUPU "
						 + "where datebegin = '"+ondate+"' "
						 		+ "and samhurtokupuid="+idhurtok;	
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}
