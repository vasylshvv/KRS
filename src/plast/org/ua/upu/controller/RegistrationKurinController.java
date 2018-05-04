package plast.org.ua.upu.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.DilovedennyaDao;
import plast.org.ua.upu.dao.HurtokUPUDao;
import plast.org.ua.upu.dao.KVDao;
import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.dao.KurinUSPUPSDao;
import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.QuarterlyReportsUPUDao;
import plast.org.ua.upu.dao.ReportReasonDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.dao.StupinDao;
import plast.org.ua.upu.dao.UladDao;
import plast.org.ua.upu.dao.VporyadnykDao;
import plast.org.ua.upu.dao.VyshkilDao;
import plast.org.ua.upu.dao.VyshkilTabirDao;
import plast.org.ua.upu.dao.ZvyazkovyyDao;
import plast.org.ua.upu.idao.IDilovedennyaDao;
import plast.org.ua.upu.idao.IHurtokUPUDao;
import plast.org.ua.upu.idao.IKVDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.IKurinUSPUPSDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.IQuarterlyReportsUPUDao;
import plast.org.ua.upu.idao.IReportReasonDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.idao.IStupinDao;
import plast.org.ua.upu.idao.IUladDao;
import plast.org.ua.upu.idao.IVporyadnykDao;
import plast.org.ua.upu.idao.IVyshkilDao;
import plast.org.ua.upu.idao.IVyshkilTabirDao;
import plast.org.ua.upu.idao.IZvyazkovyyDao;
import plast.org.ua.upu.insert.InsertUlad;
import plast.org.ua.upu.pojo.PersonsUPUPojo;
import plast.org.ua.upu.pojo.PersonsUSPUPSPojo;
import plast.org.ua.upu.pojo.ProvidKurinPojo;
import plast.org.ua.upu.pojo.VporyadnykPojo;
import plast.org.ua.upu.pojo.ZvyazkovyyPojo;
import plast.org.ua.upu.table.DilovedennyaHurtokUPU;
import plast.org.ua.upu.table.DilovedennyaKurinUPU;
import plast.org.ua.upu.table.HurtokUPU;
import plast.org.ua.upu.table.KV;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.KurinUSPUPS;
import plast.org.ua.upu.table.PersonDilovodHurtok;
import plast.org.ua.upu.table.QuarterlyReportsUPU;
import plast.org.ua.upu.table.ReportReason;
import plast.org.ua.upu.table.Stanytsya;
import plast.org.ua.upu.table.Stupin;
import plast.org.ua.upu.table.Ulad;
import plast.org.ua.upu.table.Vyshkil;
import plast.org.ua.upu.table.VyshkilTabir;
import plast.org.ua.upu.table.Zvyazkovyy;

public class RegistrationKurinController implements Controller{
	private IStanystyaDao stanytsyaDao = StanytsyaDao.getInstance();
	private IKurinUPUDao kurinUPUDao = KurinUPUDao.getInstance();
	private IHurtokUPUDao hurtokUPUDao = HurtokUPUDao.getInstance();
	private IUladDao uladDao = UladDao.getInstance();
	private IStupinDao stupinDao = StupinDao.getInstance();
	private IPersonsDao personsDao = PersonsDao.getInstance();
	private IKurinUSPUPSDao kurinUSPUPSDao = KurinUSPUPSDao.getInstance();
	private IKVDao kvDao = KVDao.getInstance(); 
	private IVyshkilDao vyshkilDao = VyshkilDao.getInstance();
	private IVyshkilTabirDao vyshkilTabirDao = VyshkilTabirDao.getInstance();
	private IZvyazkovyyDao zvyazkovyyDao = ZvyazkovyyDao.getInstance();
	private IVporyadnykDao vporyadnykDao = VporyadnykDao.getInstance();
	private IDilovedennyaDao dilovedennyaDao = DilovedennyaDao.getInstance();
	private IQuarterlyReportsUPUDao quartrepdao = QuarterlyReportsUPUDao.getInstance();
	private IReportReasonDao reportReasonDao = ReportReasonDao.getInstance();
	private List<KurinUPU> listKurinUPU;
	private List<KurinUPU> listKurinUPUStan;
	private List<Stanytsya> listStanytsya;
	private List<Stanytsya> listStanytsyap;
	private List<HurtokUPU> listHurtokUPU;
	private List<Ulad> listUlad;
	private List<Ulad> listUladUPU;
	private List<Stupin> listStupin;
	private List<Stupin> listStupinUPU;
	private List<PersonsUPUPojo> listPerson;
	private List<KurinUSPUPS> listKurinUspUps;
	private List<KV> listKV;
	private List<Vyshkil> listVyshkil;
	private List<VyshkilTabir> listVyshkilTabir;
	private List<PersonsUSPUPSPojo> listPersonsUSPUPS;
	private List<ZvyazkovyyPojo> listZvyazkovyyPojo;
	private List<Zvyazkovyy> listZvyazkovyy;
	private List<VporyadnykPojo> listVporyadnyk;
	private List<DilovedennyaHurtokUPU> listDilovodHurtok;
	private List<DilovedennyaKurinUPU> listDilovodKurin;
	private List<PersonDilovodHurtok> listPersonDilovodHurt;
	private List<ProvidKurinPojo> listProvidKurin;
	private List<QuarterlyReportsUPU> listQuarterlyReportsUPUs; 
	private List<ReportReason> listReportReason;
	private List listentered;
	private Ulad ulad;
	private KurinUPU kurinupu;
	private QuarterlyReportsUPU quarter;
	private Zvyazkovyy zvyazkovyy; 
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
        String strSelkur = request.getParameter("selkur");
        String parameterEntered = request.getParameter("parameter");
       // String strSamHurt = request.getParameter("selsamhurt"); 
		
		
		
		if(strSelstan!=null && strSelkur!=null){
			Long selstan = Long.parseLong(strSelstan);
			Long selkur = Long.parseLong(strSelkur);
			modelAndView =  new ModelAndView("/jsp/registrkurupu.jsp");
		
			listStanytsya = stanytsyaDao.findStanytsya(selstan);
			for (Stanytsya stanytsya : listStanytsya) {
				System.out.println(stanytsya.getNamestan());
			}
			listKurinUPU = kurinUPUDao.findOneKurinUPU(selkur);
			for (KurinUPU kurinUPU : listKurinUPU) {
				System.out.println(kurinUPU.getNameKurin());
			}
			/* інофрмація для кнопки на підтвердження*/
			
			listQuarterlyReportsUPUs = new ArrayList<QuarterlyReportsUPU>();
			listQuarterlyReportsUPUs = quartrepdao.quaterCountKurin(toDate(), selkur);
			
			int count = 0;
			count = listQuarterlyReportsUPUs.size();
			if(count == 0){
				addQuarterlyReport(strSelkur);
			}
			
			listentered = new ArrayList();
			listentered.add(parameterEntered);
			
			listHurtokUPU = hurtokUPUDao.findHurtokINKurinUPU(selkur);
			listUlad = uladDao.findAllUlad();
			listStupin = stupinDao.findAllStupin();
			
			listUladUPU = uladDao.findUlad("УПЮ");
			Long uladId = null;
			for (Ulad uladUPU : listUladUPU) {
				uladId = uladUPU.getId();
			}
			listStupinUPU = stupinDao.findStupinUlad(uladId);
			
			listPerson = personsDao.findPerson(selkur);
			for (PersonsUPUPojo pers : listPerson) {
				System.out.println(pers.getNamestupin()+"\t"+pers.getPhonenumber()+" \t"+pers.getFirstname() + " "+pers.getLastname());
			}
			
			listStanytsyap = stanytsyaDao.findAllStanytsya();
			
			listKurinUspUps = kurinUSPUPSDao.findAllKurin();
			
			listKV = kvDao.findAll();
			listVyshkil = vyshkilDao.findAllVyshkil();
			listVyshkilTabir = vyshkilTabirDao.findAllVyshkilTabir();
			listPersonsUSPUPS = personsDao.findPersonUSPUPS(selstan);
			listZvyazkovyyPojo = zvyazkovyyDao.findZvyazkovyyKurin(selkur);
			listVporyadnyk = vporyadnykDao.findAllVporyadnyk(selkur);
			listDilovodHurtok = dilovedennyaDao.findAllDilovedennyaHurtok();
			listDilovodKurin = dilovedennyaDao.findAllDilovedennyaKurin();
			listPersonDilovodHurt = dilovedennyaDao.findPersonKurinDilovodHurtok(selkur);
			listProvidKurin = dilovedennyaDao.findProvidKurin(selkur);
			
			listKurinUPUStan = kurinUPUDao.findStanytsyaKurinUPU(selstan);
			
			listQuarterlyReportsUPUs = quartrepdao.findQuartReportKurin(selkur, toDate());
			
			Long idQuarterlyReport = (long) 0;
			
			for (QuarterlyReportsUPU rp : listQuarterlyReportsUPUs) {
				idQuarterlyReport = rp.getId();
			}
			
			
			listReportReason = reportReasonDao.findReason(idQuarterlyReport);
			
			
			
			modelAndView.addObject("liststanytsya", listStanytsya);
			modelAndView.addObject("listkurinupu", listKurinUPU);
			modelAndView.addObject("listhurtokupu", listHurtokUPU);
			modelAndView.addObject("listulad", listUlad);
			modelAndView.addObject("liststupin", listStupin);
			modelAndView.addObject("liststupinupu", listStupinUPU);
			modelAndView.addObject("listperson", listPerson);
			modelAndView.addObject("listdilovodhurtok", listDilovodHurtok);
			modelAndView.addObject("listdilovodkurin", listDilovodKurin);
			modelAndView.addObject("listprovidkurin",listProvidKurin);
			
			modelAndView.addObject("liststanp", listStanytsyap);
			modelAndView.addObject("listkurinuspups", listKurinUspUps);
			modelAndView.addObject("listkv", listKV);
			modelAndView.addObject("listvyshkil", listVyshkil);
			modelAndView.addObject("listvyshkiltabir", listVyshkilTabir);
			modelAndView.addObject("listpersonsuspups", listPersonsUSPUPS);
			modelAndView.addObject("zvyazkovyy", listZvyazkovyyPojo);
			modelAndView.addObject("vporyadnyk", listVporyadnyk);
			modelAndView.addObject("personsdilovodhurt", listPersonDilovodHurt);
			modelAndView.addObject("listkurinupustan", listKurinUPUStan);
			
			modelAndView.addObject("listentered", listentered);
			
			modelAndView.addObject("liststatus", listQuarterlyReportsUPUs);
			
			modelAndView.addObject("listreason", listReportReason);
			
			} else {
				return new ModelAndView("redirect:index.html");
			}
		return modelAndView;
	}
	private void addQuarterlyReport(String strSelkur) throws ParseException{
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
		Date firstToHalfYear = df.parse(LastDayMonthFEB(1, toYear()) +".02." + toYear());

		Date secondFromHalfYear = df.parse("01.03." + toYear());
		Date secondToHalfYear = df.parse("31.08." + toYear());
		
		Long idkurin = Long.parseLong(strSelkur);
		listKurinUPU = kurinUPUDao.findOneKurinUPU(idkurin);
		for (KurinUPU k : listKurinUPU) {
			kurinupu = new KurinUPU(k.getId(), k.getNumberKurin(), k.getSexKurin(), k.getNameKurin(),
					k.getDateRegister(), k.getEmailKurin(), k.getHashid(), k.getStanytsya());
		}
		
		listZvyazkovyy = zvyazkovyyDao.findOneZvyazkovyyInKurin(idkurin);
		for (Zvyazkovyy zv : listZvyazkovyy) {
			zvyazkovyy = new Zvyazkovyy(zv.getId(), zv.getDatebegin(), zv.getDateend(), zv.getKurinupu(),
					zv.getPersons(), zv.getHashcode());
		}
		
		quarter = new QuarterlyReportsUPU(firstFromHalfYear, firstToHalfYear, null, null, 0, zvyazkovyy,
				kurinupu, null, null, null);
		quartrepdao.addQuarterlyReport(quarter);
		quarter = new QuarterlyReportsUPU(secondFromHalfYear, secondToHalfYear, null, null, 0, zvyazkovyy,
				kurinupu, null, null, null);
		quartrepdao.addQuarterlyReport(quarter);
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
