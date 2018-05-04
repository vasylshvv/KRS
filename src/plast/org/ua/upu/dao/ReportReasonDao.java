package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IReportReasonDao;
import plast.org.ua.upu.table.ReportReason;

public class ReportReasonDao extends HibernateDaoSupport implements IReportReasonDao{
	
	private static final Log log = LogFactory.getLog(ReportReasonDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IReportReasonDao instance;
	
	public static IReportReasonDao getInstance() {
		if(instance == null){
			instance = (IReportReasonDao)context.getBean("reasonrep");
		}
		return instance;
	}
	
		@Override
		public void saveReason(ReportReason reportReason) {
			getHibernateTemplate().save(reportReason);
			
		}
		
	@Override
	public List<ReportReason> findReason(Long idQuarterlyReport) {
		try {
			log.debug("find on date");
			String query = "from ReportReason "
						 + "where reportsupuid="+idQuarterlyReport;	
			System.out.println(query);
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
}
