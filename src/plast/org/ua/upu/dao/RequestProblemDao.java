package plast.org.ua.upu.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import plast.org.ua.upu.idao.IRequestProblemDao;
import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.table.RequestProblem;

public class RequestProblemDao extends HibernateDaoSupport implements IRequestProblemDao{
	private static final Log log = LogFactory.getLog(RequestProblemDao.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static IRequestProblemDao instance;
	public static IRequestProblemDao getInstance() {
		if (instance == null) {
			instance = (IRequestProblemDao)context.getBean("problem");
		}
		return instance;
	}
	@Override
	public void addProblem(RequestProblem problem) {
		log.debug("add new problem");
		getHibernateTemplate().save(problem);
		
	}
	@Override
	public List<RequestProblem> findAllProblem() {
		try {
			log.debug("find all RequestProblem");
			String query = "from RequestProblem";
			return getHibernateTemplate().find(query);
		} catch (RuntimeException e) {
			throw e;
		}	
	}
	
}
