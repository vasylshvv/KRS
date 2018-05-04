package plast.org.ua.upu.controller.ajax;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.QuarterlyReportsUPUDao;
import plast.org.ua.upu.dao.ReportReasonDao;
import plast.org.ua.upu.dao.SamHurtokUPUDao;
import plast.org.ua.upu.dao.VporyadnykDao;
import plast.org.ua.upu.dao.ZvyazkovyyDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.IQuarterlyReportsUPUDao;
import plast.org.ua.upu.idao.IReportReasonDao;
import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.idao.IVporyadnykDao;
import plast.org.ua.upu.idao.IZvyazkovyyDao;
import plast.org.ua.upu.md.MD5;
import plast.org.ua.upu.sendemail.SendEmail;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.Persons;
import plast.org.ua.upu.table.QuarterlyReportsUPU;
import plast.org.ua.upu.table.ReportReason;
import plast.org.ua.upu.table.SamHurtokUPU;
import plast.org.ua.upu.table.Vporyadnyk;
import plast.org.ua.upu.table.Zvyazkovyy;

public class QuarterlyReportsUPUContoller implements Controller {
	private IQuarterlyReportsUPUDao quartrepdao = QuarterlyReportsUPUDao.getInstance();
	private IKurinUPUDao kurinUPUDao = KurinUPUDao.getInstance();
	private ISamHurtokUPUDao samHurtokUPUDao = SamHurtokUPUDao.getInstance();
	private IZvyazkovyyDao zvyazkovyyDao = ZvyazkovyyDao.getInstance();
	private IVporyadnykDao vporDao = VporyadnykDao.getInstance();
	private IReportReasonDao reasonDao = ReportReasonDao.getInstance();
	private IPersonsDao personsDao = PersonsDao.getInstance();
	private List<QuarterlyReportsUPU> listQuarterly;
	private List<KurinUPU> listKurinUPU;
	private List<SamHurtokUPU> listSMHurtokUPU;
	private List<Zvyazkovyy> listZvyazkovyy;
	private List<Vporyadnyk> listVporsm;
	private List<Persons> listPerson;
	private QuarterlyReportsUPU quarter;
	private KurinUPU kurinupu;
	private Zvyazkovyy zvyazkovyy;
	private ReportReason reportReason;
	private SamHurtokUPU smhurtoupu;
	private Vporyadnyk vporyadnyksm;
	private DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private Calendar cal = Calendar.getInstance();

	// Get the date today using Calendar object.
	private Date today = Calendar.getInstance().getTime();
	private String email = null;
	private Long idPerson = null;
	private Integer sex = null;
	
	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SendEmail sd = new SendEmail("techniclsuport@gmail.com", "@ctiv@tion");
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		
		ModelAndView modelAndView = null;

		Enumeration<String> parameters = request.getParameterNames();
		JSONObject json = new JSONObject();

		while (parameters.hasMoreElements()) {
			String string = parameters.nextElement();
			System.out.println("param = " + string);
			System.out.println("value_param = " + request.getParameter(string));
		}
		String need = request.getParameter("need");
		String kurinID = request.getParameter("kurinid");
		Long idkurin = null;
		if(kurinID!=null){
			idkurin = Long.parseLong(kurinID);
		}
		String smHurtokId = request.getParameter("smhurtokid");
		Long idsmhurtok = null;
		if(smHurtokId!= null){
			idsmhurtok = Long.parseLong(smHurtokId); 
		}	
		
		
		
		String description = request.getParameter("reason");
		switch (request.getParameter("btn")) {
		case "sendToZv":
			
		
				System.out.println("add half year");
				String kurin = null;
				String hashid = null;
				String namekurin = null;
				listKurinUPU = kurinUPUDao.findOneKurinUPU(idkurin);
				for (KurinUPU k : listKurinUPU) {
					kurinupu = new KurinUPU(k.getId(), k.getNumberKurin(), k.getSexKurin(), k.getNameKurin(),
							k.getDateRegister(), k.getEmailKurin(), k.getHashid(), k.getStanytsya());
					kurin = k.getNameKurin();
					sex = k.getSexKurin();
				}
				
				listZvyazkovyy = zvyazkovyyDao.findOneZvyazkovyyInKurin(idkurin);
				for (Zvyazkovyy zv : listZvyazkovyy) {
					zvyazkovyy = new Zvyazkovyy(zv.getId(), zv.getDatebegin(), zv.getDateend(), zv.getKurinupu(),
							zv.getPersons(), zv.getHashcode());
					idPerson = zv.getPersons().getId();
					hashid = zv.getHashcode();
					
					
				}
				
				listPerson = personsDao.findOnePersons(idPerson);
				String fullnamezv = null;
				for (Persons ps : listPerson) {
					email = ps.getEmail();
					fullnamezv = ps.getFirstName()+ps.getLastName();
				}
				
				if(hashid == null){
					namekurin = kurin;
					hashid = MD5.getHash(fullnamezv+namekurin);
					listZvyazkovyy = zvyazkovyyDao.findOneZvyazkovyyInKurin(idkurin);
					for (Zvyazkovyy zv : listZvyazkovyy) {
						zvyazkovyy = new Zvyazkovyy(zv.getId(), zv.getDatebegin(), zv.getDateend(), zv.getKurinupu(),
								zv.getPersons(), hashid);
					}
					zvyazkovyyDao.updateOldZvyazkovyy(zvyazkovyy);
							
				}
				
				System.out.println("update");
				listQuarterly = quartrepdao.findQuartReportKurin(idkurin, toDate());
				for (QuarterlyReportsUPU qRUPU : listQuarterly) {
					quarter = new QuarterlyReportsUPU(qRUPU.getId(), qRUPU.getDatebegin(), qRUPU.getDateend(), today,
							qRUPU.getDateApproveZvyazkovyy(), 1, /*qRUPU.getZvyazkovyy()*/zvyazkovyy, qRUPU.getKurinUPU(),
							qRUPU.getSamHurtokUPU(), qRUPU.getVporyadnyksamhurtid(), need);
					quartrepdao.updateQuartReportKurin(quarter);

				}
//				if (description == null) {
//					description = "Звіт курінного";
//				}
//				smhurtoupu = null;
//				vporyadnyksm = null;
//
//				reportReason = new ReportReason(kurinupu, smhurtoupu, zvyazkovyy, vporyadnyksm, description, today,
//						quarter, 1);
//				reasonDao.saveReason(reportReason);
				String textkur = null;
			
				if(sex == 0){
					textkur = "<p><h2>СКОБ!</h2></p><p><b>Друже зв'язковий курінний прозвітував курінь <h3>"+ kurin+"</h3></b></p>"
							+ "<p>Твоє кодове слово на підтвердження звіту <b>"+hashid+"</b></p>";
				} else if(sex == 1){
					textkur = "<p><h2>СКОБ!</h2></p><p><b>Подруго зв'язкова курінна прозвітувала курінь <h3>"+ kurin+"</h3></b></p>"
							+ "<p>Твоє кодове слово на підтвердження звіту <b>"+hashid+"</b></p>";
				} else {
					textkur = "<p><h2>СКОБ!</h2></p><p><b>Друже/Подруго зв'язковий/ва курінний/на прозвітував/ла курінь <h3>"+ kurin+"</h3></b></p>"
							+ "<p>Твоє кодове слово на підтвердження звіту <b>"+hashid+"</b></p>";
				}
				
				
				
				sd.send("е-звітування", textkur, "vasylshvv@gmail.com", email);
				json = new JSONObject();
				json.put("status", "1:acceptKurin");

				System.out.println(json);

				response.getWriter().println(json);
				
			

			break;

		case "sendToVh":
			System.out.println("add half year");
			
			String hurtok = null;
			String hashidVpor = null;
			String sfullname = null;
			String namehurtok = null;
			listSMHurtokUPU = samHurtokUPUDao.findOneSamHurtok(idsmhurtok);
			for (SamHurtokUPU sm : listSMHurtokUPU) {
				smhurtoupu = new SamHurtokUPU(sm.getId(), sm.getNameSamHurtok(), sm.getSexSamHurtok(), 
						sm.getEmailSamHurtok(), sm.getDateFrom(), sm.getDateTo(), sm.getHashidhurtok(), 
						sm.getVidznakaHurtka(), sm.getStanytsya());
				hurtok = sm.getNameSamHurtok();
				sex = sm.getSexSamHurtok();
				
				namehurtok = sm.getNameSamHurtok();
			}
			
			listVporsm = vporDao.findVporyadnykForSamHurtok(idsmhurtok);
			for (Vporyadnyk vp : listVporsm) {
				vporyadnyksm = new Vporyadnyk(vp.getId(), vp.getDatebegin(), vp.getDateend(), vp.getSamhurtokupu(), vp.getPersons(), 
						vp.getHashcode());
				idPerson = vp.getPersons().getId();
				hashidVpor = vp.getHashcode();
			}
			
			
			listPerson = personsDao.findOnePersons(idPerson);
			for (Persons ps : listPerson) {
				email = ps.getEmail();
				sfullname = ps.getFirstName()+ps.getLastName();
			}
			
			
		
			
			
			
			System.out.println("update");
			listQuarterly = quartrepdao.findQuartReportSamHurtok(idsmhurtok, toDate());
			
			for (QuarterlyReportsUPU qRUPU : listQuarterly) {
				quarter = new QuarterlyReportsUPU(qRUPU.getId(), qRUPU.getDatebegin(), qRUPU.getDateend(), today,
						qRUPU.getDateApproveZvyazkovyy(), 1, qRUPU.getZvyazkovyy(), qRUPU.getKurinUPU(),
						qRUPU.getSamHurtokUPU(), /*qRUPU.getVporyadnyksamhurtid()*/vporyadnyksm, need);
				quartrepdao.updateQuartReportKurin(quarter);

			}
//			if (description == null) {
//				description = "Звіт гурткового самостійного гуртка";
//			}
//			kurinupu = null;
//			zvyazkovyy = null;
//
//			reportReason = new ReportReason(kurinupu, smhurtoupu, zvyazkovyy, vporyadnyksm, description, today,
//					quarter, 1);
//			reasonDao.saveReason(reportReason);
			
			if(hashidVpor == null){
				
				hashidVpor =  MD5.getHash(namehurtok+sfullname);
				listVporsm = vporDao.findVporyadnykForSamHurtok(idsmhurtok);
				for (Vporyadnyk vp : listVporsm) {
					vporyadnyksm = new Vporyadnyk(vp.getId(), vp.getDatebegin(), vp.getDateend(), vp.getSamhurtokupu(), vp.getPersons(), 
							hashidVpor);
				}
				vporDao.update(vporyadnyksm);
			}
			
			String texthurt = null;
			if(sex == 0){
				texthurt = "<p><h2>СКОБ!</h2></p><p><b>Друже виховник гуртковий прозвітував самостійний гурток <h3>"+ hurtok+"</h3></b></p>"
						+ "<p>Твоє кодове слово на підтвердження звіту <b>"+hashidVpor+"</b></p>";
			} else if(sex == 1){
				texthurt = "<p><h2>СКОБ!</h2></p><p><b>Подруго виховниця гурткова прозвітувала самостійний гурток <h3>"+ hurtok+"</h3></b></p>"
						+ "<p>Твоє кодове слово на підтвердження звіту <b>"+hashidVpor+"</b></p>";
			} else {
				texthurt = "<p><h2>СКОБ!</h2></p><p><b>Друже/Подруго виховник/ця гуртковий прозвітував/ла самостійний гурток <h3>"+ hurtok+"</h3></b></p>"
						+ "<p>Твоє кодове слово на підтвердження звіту <b>"+hashidVpor+"</b></p>";
			}
			
			
			sd.send("е-звітування", texthurt, "vasylshvv@gmail.com", email);
			
			
			json = new JSONObject();
			json.put("status", "1:acceptSmHurtok");

			System.out.println(json);

			response.getWriter().println(json);
				
			
			break;

		case "acceptDate":
			
			if(idkurin!= null){
				listQuarterly = new ArrayList<QuarterlyReportsUPU>();
				listQuarterly = quartrepdao.findQuartReportKurin(idkurin, toDate());
				for (QuarterlyReportsUPU qRUPU : listQuarterly) {
					quarter = new QuarterlyReportsUPU(qRUPU.getId(), qRUPU.getDatebegin(), qRUPU.getDateend(), qRUPU.getDateApproveKurin(),
							today, 3, qRUPU.getZvyazkovyy(), qRUPU.getKurinUPU(),
							qRUPU.getSamHurtokUPU(), qRUPU.getVporyadnyksamhurtid(), need);
					quartrepdao.updateQuartReportKurin(quarter);

				}
//				if (description == null) {
//					description = "Звіт Зв'язкового";
//				}
				
				listKurinUPU = kurinUPUDao.findOneKurinUPU(idkurin);
				for (KurinUPU k : listKurinUPU) {
					kurinupu = new KurinUPU(k.getId(), k.getNumberKurin(), k.getSexKurin(), k.getNameKurin(),
							k.getDateRegister(), k.getEmailKurin(), k.getHashid(), k.getStanytsya());
					email = k.getEmailKurin();
					sex = k.getSexKurin();
				}

				listZvyazkovyy = zvyazkovyyDao.findOneZvyazkovyyInKurin(idkurin);
				for (Zvyazkovyy zv : listZvyazkovyy) {
					zvyazkovyy = new Zvyazkovyy(zv.getId(), zv.getDatebegin(), zv.getDateend(), zv.getKurinupu(),
							zv.getPersons(), zv.getHashcode());
				}
				
//				smhurtoupu = null;
//				vporyadnyksm = null;
//
//				reportReason = new ReportReason(kurinupu, smhurtoupu, zvyazkovyy, vporyadnyksm, description, today,
//						quarter, 3);
//				reasonDao.saveReason(reportReason);
				
				String text = null;
				if(sex ==0 ){
					text = "<p>Зв'язковий підтвердив звіт<p>";
				} else if(sex == 1){
					text = "<p>Зв'язкова підтвердила звіт<p>";
				} else {
					text = "<p>Зв'язковий/ва підтвердив/ла звіт<p>";
				}
				sd.send("e-звітування", text, "vasylshvv@gmail.com", email);
				
				json = new JSONObject();
				json.put("status", "3:acceptDateZV");

				System.out.println(json);

				response.getWriter().println(json);
			} else if(idsmhurtok!= null){
				
				listQuarterly = new ArrayList<QuarterlyReportsUPU>();
				listQuarterly = quartrepdao.findQuartReportSamHurtok(idsmhurtok, toDate());
				for (QuarterlyReportsUPU qRUPU : listQuarterly) {
					quarter = new QuarterlyReportsUPU(qRUPU.getId(), qRUPU.getDatebegin(), qRUPU.getDateend(), qRUPU.getDateApproveKurin(),
							today, 3, qRUPU.getZvyazkovyy(), qRUPU.getKurinUPU(),
							qRUPU.getSamHurtokUPU(), qRUPU.getVporyadnyksamhurtid(), need);
					quartrepdao.updateQuartReportKurin(quarter);

				}
//				if (description == null) {
//					description = "Звіт впорядника самостійного";
//				}
				kurinupu = null;
				zvyazkovyy = null;

				listSMHurtokUPU = samHurtokUPUDao.findOneSamHurtok(idsmhurtok);
				for (SamHurtokUPU sm : listSMHurtokUPU) {
					smhurtoupu = new SamHurtokUPU(sm.getId(), sm.getNameSamHurtok(), sm.getSexSamHurtok(), 
							sm.getEmailSamHurtok(), sm.getDateFrom(), sm.getDateTo(), sm.getHashidhurtok(), 
							sm.getVidznakaHurtka(), sm.getStanytsya());
					email = sm.getEmailSamHurtok();
					sex = sm.getSexSamHurtok();
				}
				
				listVporsm = vporDao.findVporyadnykForSamHurtok(idsmhurtok);
				for (Vporyadnyk vp : listVporsm) {
					vporyadnyksm = new Vporyadnyk(vp.getId(), vp.getDatebegin(), vp.getDateend(), vp.getSamhurtokupu(), vp.getPersons(), 
							vp.getHashcode());
				}
				
//				reportReason = new ReportReason(kurinupu, smhurtoupu, zvyazkovyy, vporyadnyksm, description, today,
//						quarter, 3);
//				reasonDao.saveReason(reportReason);
				
				String text = null;
				if(sex == 0){
					text = "<p>Виховник підтвердив звіт<p>";
				} else if(sex == 1){
					text = "<p>Виховниця підтвердила звіт<p>";
				} else {
					text = "<p>Виховник/ця підтвердив/ла звіт<p>";
				}
				sd.send("e-звітування", text, "vasylshvv@gmail.com", email);
				json = new JSONObject();
				json.put("status", "3:acceptDateVP");

				System.out.println(json);

				response.getWriter().println(json);
			}
			break;
		case "cancelDate":

			
			if(idkurin!=null){
				listQuarterly = new ArrayList<QuarterlyReportsUPU>();
				listQuarterly = quartrepdao.findQuartReportKurin(idkurin, toDate());
				for (QuarterlyReportsUPU qRUPU : listQuarterly) {
					quarter = new QuarterlyReportsUPU(qRUPU.getId(), qRUPU.getDatebegin(), qRUPU.getDateend(), qRUPU.getDateApproveKurin(),
							today, 2, qRUPU.getZvyazkovyy(), qRUPU.getKurinUPU(),
							qRUPU.getSamHurtokUPU(), qRUPU.getVporyadnyksamhurtid(), qRUPU.getNeed());
					quartrepdao.updateQuartReportKurin(quarter);

				}
//				if (description == null) {
//					description = "Відхилено зв'язковим";
//				}
				
				listKurinUPU = kurinUPUDao.findOneKurinUPU(idkurin);
				for (KurinUPU k : listKurinUPU) {
					kurinupu = new KurinUPU(k.getId(), k.getNumberKurin(), k.getSexKurin(), k.getNameKurin(),
							k.getDateRegister(), k.getEmailKurin(), k.getHashid(), k.getStanytsya());
					email = k.getEmailKurin();
					sex = k.getSexKurin();
				}

				listZvyazkovyy = zvyazkovyyDao.findOneZvyazkovyyInKurin(idkurin);
				for (Zvyazkovyy zv : listZvyazkovyy) {
					zvyazkovyy = new Zvyazkovyy(zv.getId(), zv.getDatebegin(), zv.getDateend(), zv.getKurinupu(),
							zv.getPersons(), zv.getHashcode());
				}
				
				smhurtoupu = null;
				vporyadnyksm = null;

				reportReason = new ReportReason(kurinupu, smhurtoupu, zvyazkovyy, vporyadnyksm, description, today,
						quarter, 2);
				reasonDao.saveReason(reportReason);
				
				
				String text = null;
				if(sex == 0){
					text = "<p>Зв'язковий відмінив звіт<p> + <p>Причина: "+description+"</p>";
				} else if(sex == 1){
					text = "<p>Зв'язкова відмінила звіт<p>+ <p>Причина: "+description+"</p>";
				} else {
					text = "<p>Зв'язковий/ва відмінив/ла звіт<p>+ <p>Причина: "+description+"</p>";
				}
				
				sd.send("e-звітування", text, "vasylshvv@gmail.com", email);
				json = new JSONObject();
				json.put("status", "2:cancelDateZV");

				System.out.println(json);

				response.getWriter().println(json);
			} else if(idsmhurtok!=null){
				listQuarterly = new ArrayList<QuarterlyReportsUPU>();
				listQuarterly = quartrepdao.findQuartReportSamHurtok(idsmhurtok, toDate());
				for (QuarterlyReportsUPU qRUPU : listQuarterly) {
					quarter = new QuarterlyReportsUPU(qRUPU.getId(), qRUPU.getDatebegin(), qRUPU.getDateend(), qRUPU.getDateApproveKurin(),
							today, 2, qRUPU.getZvyazkovyy(), qRUPU.getKurinUPU(),
							qRUPU.getSamHurtokUPU(), qRUPU.getVporyadnyksamhurtid(), qRUPU.getNeed());
					quartrepdao.updateQuartReportKurin(quarter);

				}
//				if (description == null) {
//					description = "Відхилено впорядником самостійного гуртка";
//				}
				kurinupu = null;
				zvyazkovyy = null;

				listSMHurtokUPU = samHurtokUPUDao.findOneSamHurtok(idsmhurtok);
				for (SamHurtokUPU sm : listSMHurtokUPU) {
					smhurtoupu = new SamHurtokUPU(sm.getId(), sm.getNameSamHurtok(), sm.getSexSamHurtok(), 
							sm.getEmailSamHurtok(), sm.getDateFrom(), sm.getDateTo(), sm.getHashidhurtok(), 
							sm.getVidznakaHurtka(), sm.getStanytsya());
					email = sm.getEmailSamHurtok();
					sex = sm.getSexSamHurtok();
				}
				
				listVporsm = vporDao.findVporyadnykForSamHurtok(idsmhurtok);
				for (Vporyadnyk vp : listVporsm) {
					vporyadnyksm = new Vporyadnyk(vp.getId(), vp.getDatebegin(), vp.getDateend(), vp.getSamhurtokupu(), vp.getPersons(), 
							vp.getHashcode());
				}
				
				
				
				reportReason = new ReportReason(kurinupu, smhurtoupu, zvyazkovyy, vporyadnyksm, description, today,
						quarter, 2);
				reasonDao.saveReason(reportReason);
				
				
				String text = null;
				if(sex == 0){
					text = "<p>Виховник відмінив звіт<p> + <p>Причина: "+description+"</p>";
				} else if(sex == 1){
					text = "<p>Виховниця відмінила звіт<p> + <p>Причина: "+description+"</p>";
				} else {
					text = "<p>Виховник/ця відмінив/ла звіт<p>+ <p>Причина: "+description+"</p>";
				}
				
				sd.send("e-звітування", text, "vasylshvv@gmail.com", email);
				json = new JSONObject();
				json.put("status", "2:cancelDateVP");

				System.out.println(json);

				response.getWriter().println(json);
			}
			
			
			
			break;
			
			

		default:
			break;
			
		}

		
		response.getWriter().close();
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

	private Integer countReportKurin(String sysdate, Long idkurin) {
		listQuarterly = new ArrayList<QuarterlyReportsUPU>();

		listQuarterly = quartrepdao.quaterCountKurin(sysdate, idkurin);

		int count = 0;
		count = listQuarterly.size();
		return count;
	}
}
