package plast.org.ua.upu.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.DilovedennyaDao;
import plast.org.ua.upu.dao.KVDao;
import plast.org.ua.upu.dao.KurinUSPUPSDao;
import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.QuarterlyReportsUPUDao;
import plast.org.ua.upu.dao.ReportReasonDao;
import plast.org.ua.upu.dao.SamHurtokUPUDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.dao.StupinDao;
import plast.org.ua.upu.dao.UladDao;
import plast.org.ua.upu.dao.VporyadnykDao;
import plast.org.ua.upu.dao.VyshkilDao;
import plast.org.ua.upu.dao.VyshkilTabirDao;
import plast.org.ua.upu.idao.IDilovedennyaDao;
import plast.org.ua.upu.idao.IKVDao;
import plast.org.ua.upu.idao.IKurinUSPUPSDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.IQuarterlyReportsUPUDao;
import plast.org.ua.upu.idao.IReportReasonDao;
import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.idao.IStupinDao;
import plast.org.ua.upu.idao.IUladDao;
import plast.org.ua.upu.idao.IVporyadnykDao;
import plast.org.ua.upu.idao.IVyshkilDao;
import plast.org.ua.upu.idao.IVyshkilTabirDao;
import plast.org.ua.upu.pojo.PersonsUPUPojo;
import plast.org.ua.upu.pojo.PersonsUSPUPSPojo;
import plast.org.ua.upu.pojo.VporyadnykPojo;
import plast.org.ua.upu.pojo.VporyadnykSamurtokPojo;
import plast.org.ua.upu.table.DilovedennyaHurtokUPU;
import plast.org.ua.upu.table.KV;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.KurinUSPUPS;
import plast.org.ua.upu.table.PersonDilovodHurtok;
import plast.org.ua.upu.table.QuarterlyReportsUPU;
import plast.org.ua.upu.table.ReportReason;
import plast.org.ua.upu.table.SamHurtokUPU;
import plast.org.ua.upu.table.Stanytsya;
import plast.org.ua.upu.table.Stupin;
import plast.org.ua.upu.table.Ulad;
import plast.org.ua.upu.table.Vporyadnyk;
import plast.org.ua.upu.table.Vyshkil;
import plast.org.ua.upu.table.VyshkilTabir;
import plast.org.ua.upu.table.Zvyazkovyy;

public class RegistrationSamHurtokController implements Controller{
	private IStanystyaDao stanytsyaDao = StanytsyaDao.getInstance();
	private ISamHurtokUPUDao samHurtokUPUDao = SamHurtokUPUDao.getInstance();
	private IVporyadnykDao vporyadnykDao = VporyadnykDao.getInstance();
	private IUladDao uladDao = UladDao.getInstance();
	private IStupinDao stupinDao = StupinDao.getInstance();
	private IKurinUSPUPSDao kurinUSPUPSDao = KurinUSPUPSDao.getInstance();
	private IKVDao kvDao = KVDao.getInstance();
	private IVyshkilDao vyshkilDao = VyshkilDao.getInstance();
	private IVyshkilTabirDao vyshkilTabirDao = VyshkilTabirDao.getInstance();
	private IPersonsDao personsDao = PersonsDao.getInstance();
	private IDilovedennyaDao dilovedennyaDao = DilovedennyaDao.getInstance();
	private IQuarterlyReportsUPUDao quartrepdao = QuarterlyReportsUPUDao.getInstance();
	private IVporyadnykDao vporDao = VporyadnykDao.getInstance();
	private IReportReasonDao reportReasonDao = ReportReasonDao.getInstance();
	private List<Stanytsya> listStanytsya;
	private List<Stanytsya> listStanytsyap;
	private List<SamHurtokUPU> listSamHurtok;
	private List<VporyadnykSamurtokPojo> listVporyadnyk;	
	private List<Vporyadnyk> listVpor;
	private List<Ulad> listUlad;
	private List<Ulad> listUladUPU;
	private List<Stupin> listStupin;
	private List<Stupin> listStupinUPU;
	private List<KurinUSPUPS> listKurinUspUps;
	private List<KV> listKV;
	private List<Vyshkil> listVyshkil;
	private List<VyshkilTabir> listVyshkilTabir;
	private List<PersonsUSPUPSPojo> listPersonsUSPUPS;
	private List<PersonsUPUPojo> listPerson;
	private List<DilovedennyaHurtokUPU> listDilovodHurtok;
	private List<PersonDilovodHurtok> listPersonDilovodHurt;
	private List<QuarterlyReportsUPU> listQuarterlyReportsUPUs;
	private List<ReportReason> listReportReason;
	private List listentered;
	private SamHurtokUPU samHurtokUPU;
	private Vporyadnyk vporsm;
	private QuarterlyReportsUPU quarter;
	private DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private Calendar cal = Calendar.getInstance();

	// Get the date today using Calendar object.
	private Date today = Calendar.getInstance().getTime();
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null; 
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String strSelstan = request.getParameter("selstan");        
        String strSamHurt = request.getParameter("selsamhurt");
        String parameterEntered = request.getParameter("parameter");
       
        
        if (strSelstan!=null && strSamHurt!=null){
        	modelAndView =  new ModelAndView("/jsp/registrsamhurt.jsp");
        	Long selstan = Long.parseLong(strSelstan);
			Long selsamhurt = Long.parseLong(strSamHurt);
			
			listStanytsya = stanytsyaDao.findStanytsya(selstan);			
			listSamHurtok = samHurtokUPUDao.findOneSamHurtok(selsamhurt);
			
			listQuarterlyReportsUPUs = new ArrayList<QuarterlyReportsUPU>();
			listQuarterlyReportsUPUs = quartrepdao.quaterCountsmHurtok(toDate(), selsamhurt);
			
			
			int count = 0;
			count = listQuarterlyReportsUPUs.size();
			if(count == 0){
				addQuarterlyReport(strSamHurt);
			}
			
			
			listVporyadnyk = vporyadnykDao.findVporyadnykSamHurtok(selsamhurt);
			listUlad = uladDao.findAllUlad();
			listStupin = stupinDao.findAllStupin();
			
			listUladUPU = uladDao.findUlad("соч");
			Long uladId = null;
			for (Ulad uladUPU : listUladUPU) {
				uladId = uladUPU.getId();
			}
			listStupinUPU = stupinDao.findStupinUlad(uladId);
			
			listStanytsyap = stanytsyaDao.findAllStanytsya();
			listKurinUspUps = kurinUSPUPSDao.findAllKurin();
			listKV = kvDao.findAll();
			listVyshkil = vyshkilDao.findAllVyshkil();
			listVyshkilTabir = vyshkilTabirDao.findAllVyshkilTabir();
			listPersonsUSPUPS = personsDao.findPersonUSPUPS(selstan);
			listDilovodHurtok = dilovedennyaDao.findAllDilovedennyaHurtok();
			listPerson = personsDao.findPersonSMHurtok(selsamhurt);
			listPersonDilovodHurt = dilovedennyaDao.findPersonSMhurtokDilovodHurtok(selsamhurt);
			
			listentered = new ArrayList();
			listentered.add(parameterEntered);
			
			
			listQuarterlyReportsUPUs = quartrepdao.findQuartReportSamHurtok(selsamhurt, toDate());
			
			Long idQuarterlyReport = (long) 0;
			
			for (QuarterlyReportsUPU rp : listQuarterlyReportsUPUs) {
				idQuarterlyReport = rp.getId();
			}
			
			
			listReportReason = reportReasonDao.findReason(idQuarterlyReport);
			
			modelAndView.addObject("liststanytsya", listStanytsya);
			modelAndView.addObject("listsamhurtok", listSamHurtok);
			
			
			modelAndView.addObject("listulad", listUlad);
			modelAndView.addObject("liststupin", listStupin);
			modelAndView.addObject("liststupinupu", listStupinUPU);
			modelAndView.addObject("vporyadnyk", listVporyadnyk);
			
			
			modelAndView.addObject("listperson", listPerson);			
			modelAndView.addObject("listdilovodhurtok", listDilovodHurtok);
//			modelAndView.addObject("listdilovodkurin", listDilovodKurin);
//			modelAndView.addObject("listprovidkurin",listProvidKurin);
//			
			modelAndView.addObject("liststanp", listStanytsyap);
			modelAndView.addObject("listkurinuspups", listKurinUspUps);
			modelAndView.addObject("listkv", listKV);
			modelAndView.addObject("listvyshkil", listVyshkil);
			modelAndView.addObject("listvyshkiltabir", listVyshkilTabir);
			modelAndView.addObject("listpersonsuspups", listPersonsUSPUPS);
//			modelAndView.addObject("zvyazkovyy", listZvyazkovyy);
			modelAndView.addObject("personsdilovodhurt", listPersonDilovodHurt);
//			modelAndView.addObject("listkurinupustan", listKurinUPUStan);
			modelAndView.addObject("listentered", listentered);
			
			modelAndView.addObject("liststatus", listQuarterlyReportsUPUs);
			
			modelAndView.addObject("listreason", listReportReason);
			
		} else {
			return new ModelAndView("redirect:index.html");
		}
		return modelAndView;
	}
	private String toDate() {

		cal.setTime(today);
		// Using DateFormat format method we can create a string
		// representation of a date with the defined format.
		String sysdate = df.format(today);

		// Print what date is today!
		System.out.println("Report Date: " + sysdate);
		return sysdate;
	}
	private void addQuarterlyReport(String strSelsmHurt) throws ParseException{
		/* FOR QUARTERLY REPORT */
		/*
		 * quarter = new QuarterlyReportsUPU(firstFromQuarter,
		 * firstToQuarter, null, null, 0, zvyazkovyy, kurinupu, null,
		 * null); quartrepdao.addQuarterlyReport(quarter); quarter = new
		 * QuarterlyReportsUPU(secondFromQuarter, secondToQuarter, null,
		 * null, 0, zvyazkovyy, kurinupu, null, null);
		 * quartrepdao.addQuarterlyReport(quarter); quarter = new
		 * QuarterlyReportsUPU(thirdFromQuarter, thirdToQuarter, null,
		 * null, 0, zvyazkovyy, kurinupu, null, null);
		 * quartrepdao.addQuarterlyReport(quarter); quarter = new
		 * QuarterlyReportsUPU(fourthFromQuarter, fourthToQuarter, null,
		 * null, 0, zvyazkovyy, kurinupu, null, null);
		 * quartrepdao.addQuarterlyReport(quarter);
		 */

		/* FOR HALF YEAR REPORT */
		Date firstFromHalfYear = df.parse("01.09." + toYear_1());
		Date firstToHalfYear = df.parse(LastDayMonthFEB(1, toYear())+".02." + toYear());

		Date secondFromHalfYear = df.parse("01.03." + toYear());
		Date secondToHalfYear = df.parse("31.08." + toYear());
		
		
		Long selsamhurt = Long.parseLong(strSelsmHurt);
		listSamHurtok = samHurtokUPUDao.findOneSamHurtok(selsamhurt);
		
		for (SamHurtokUPU smh : listSamHurtok) {
			samHurtokUPU = new SamHurtokUPU(smh.getId(), smh.getNameSamHurtok(), 
					smh.getSexSamHurtok(), smh.getEmailSamHurtok(), smh.getDateFrom(), smh.getDateTo(), 
					smh.getHashidhurtok(), smh.getVidznakaHurtka(), smh.getStanytsya());
		}
		
		
		listVpor = vporDao.findVporyadnykForSamHurtok(selsamhurt);
		for (Vporyadnyk vp : listVpor) {
			vporsm = new Vporyadnyk(vp.getId(), vp.getDatebegin(), vp.getDateend(), vp.getSamhurtokupu(), vp.getPersons(), 
					vp.getHashcode());
		}
		
		
//		quarter = new QuarterlyReportsUPU(firstFromHalfYear, firstToHalfYear, null, null, 0, zvyazkovyy,
//				kurinupu, null, null);
		quarter = new QuarterlyReportsUPU(firstFromHalfYear, firstToHalfYear, null, null, 0, null, null, samHurtokUPU, vporsm, null);		
		quartrepdao.addQuarterlyReport(quarter);
		
		quarter = new QuarterlyReportsUPU(secondFromHalfYear, secondToHalfYear, null, null, 0, null,null, samHurtokUPU, vporsm, null);
		quartrepdao.addQuarterlyReport(quarter);
	}
	private String toYear() {

		cal.setTime(today);
		// Using DateFormat format method we can create a string
		// representation of a date with the defined format.
		// String sysdate = df.format(today);

		String year = Integer.toString(cal.get(Calendar.YEAR));

		// Print what date is today!

		return year;
	}

	private String toYear_1() {

		cal.setTime(today);
		// Using DateFormat format method we can create a string
		// representation of a date with the defined format.
		// String sysdate = df.format(today);

		String year_1 = Integer.toString(cal.get(Calendar.YEAR) - 1);

		// Print what date is today!

		return year_1;
	}
	@SuppressWarnings("unused")
	private String LastDayMonthFEB(int month, String year){
		int ANY_MONTH = month; 
		int ANY_YEAR = Integer.parseInt(year);
		 
		Calendar cal = Calendar.getInstance();
		  cal.set(Calendar.MONTH, ANY_MONTH);
		    cal.set(Calendar.YEAR, ANY_YEAR);
		    cal.set(Calendar.DAY_OF_MONTH, 1);// This is necessary to get proper results
		    Integer lastDay = cal.getActualMaximum(Calendar.DATE);
		    return lastDay.toString();
		    
	}
}
