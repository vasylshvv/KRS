package plast.org.ua.upu.controller.ajax;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.HurtokUPUDao;
import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.SamHurtokUPUDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.dao.VporyadnykDao;
import plast.org.ua.upu.idao.IHurtokUPUDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.idao.IVporyadnykDao;
import plast.org.ua.upu.md.MD5;
import plast.org.ua.upu.sendemail.SendEmail;
import plast.org.ua.upu.table.HurtokUPU;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.Persons;
import plast.org.ua.upu.table.SamHurtokUPU;
import plast.org.ua.upu.table.Stanytsya;
import plast.org.ua.upu.table.Vporyadnyk;

public class VporyadnykController implements Controller{
	private Vporyadnyk vporyadnyk;
	private KurinUPU kurinUPU;
	private HurtokUPU hurtokUPU;
	private SamHurtokUPU samHurtokUPU;
	private Persons persons;
	private IVporyadnykDao vporyadnykDao = VporyadnykDao.getInstance();
	private IKurinUPUDao kurinUPUDao = KurinUPUDao.getInstance();
	private IHurtokUPUDao hurtokUPUDao = HurtokUPUDao.getInstance();
	private IPersonsDao personsDao = PersonsDao.getInstance();
	private ISamHurtokUPUDao samHurtokUPUDao = SamHurtokUPUDao.getInstance();
	private IStanystyaDao stanystyaDao = StanytsyaDao.getInstance();
	private List<KurinUPU> listkurinUPU;
	private List<HurtokUPU> listHurtokUPU;
	private List<SamHurtokUPU> listSamHurtokUPU;
	private List<Persons> listPersons;
	private List<Vporyadnyk> listVporyadnyk;
	private List<Stanytsya> liStanytsyas;
	private Date datebegin, enddateprevvp;
	private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        
        String kurinupuidstr = request.getParameter("kurinupuid");
        System.out.println("kurinupuidstr = "+kurinupuidstr);
        String hurtokidstr = request.getParameter("hurtokid");
        System.out.println("hurtokidstr = "+hurtokidstr);
        String olddatetovpstr = request.getParameter("olddatetovp");
        System.out.println("olddatetovp = "+olddatetovpstr);
		String newdatefromvpstr = request.getParameter("newdatefromvp");
		System.out.println("newdatefromvp = "+newdatefromvpstr);
		String newvporyadnykstr = request.getParameter("newvporyadnyk");// idperson потрібно подумати бо 2 рази передається idperson
		System.out.println("newvporyadnyk = "+newvporyadnykstr);
		String addupdate = request.getParameter("addupdate");
		System.out.println("addupdate = "+addupdate);
		String stridperson = request.getParameter("idperson"); //**
		System.out.println("stridperson = "+stridperson);
		String datestartchange = request.getParameter("datestart");
		System.out.println("datestartchange = "+datestartchange);
		
		String samhurtokid = request.getParameter("samhurtokid");
		
		Integer param = Integer.parseInt(request.getParameter("kurinorsmhurt"));
		
		if(Integer.parseInt(addupdate) == 0){
			/* Якщо додається впорядник вперше*/
			addnewVporyadnyk(param, kurinupuidstr, hurtokidstr, samhurtokid, newvporyadnykstr, newdatefromvpstr);
			return modelAndView;
		} else if(Integer.parseInt(addupdate) == 1){
			// Міняємо впорядника
			
			/*Спочатку закриваю дату*/
			
			enddateprevvp = new Date(dateFormat.parse(olddatetovpstr).getTime());
			
			if(param == 0 ){
				listVporyadnyk = vporyadnykDao.findVporyadnykForHurtok(Long.parseLong(hurtokidstr), Long.parseLong(kurinupuidstr));
				
				for (Vporyadnyk vp : listVporyadnyk) {
					vporyadnyk = new Vporyadnyk(vp.getId(), vp.getDatebegin(), enddateprevvp, vp.getKurinupu(), vp.getHurtokupu(), vp.getPersons(), vp.getHashcode());
					vporyadnykDao.update(vporyadnyk);
				}
			} else if(param == 1){
				listVporyadnyk = vporyadnykDao.findVporyadnykForSamHurtok(Long.parseLong(samhurtokid));
				for (Vporyadnyk vp : listVporyadnyk) {
					vporyadnyk = new Vporyadnyk(vp.getId(), vp.getDatebegin(), enddateprevvp, vp.getSamhurtokupu(), vp.getPersons(), vp.getHashcode());
					vporyadnykDao.update(vporyadnyk);
				}
			}
			
			
			/* Додаємо нового впорядника*/
			addnewVporyadnyk(param, kurinupuidstr, hurtokidstr, samhurtokid, newvporyadnykstr, newdatefromvpstr);
			
			
			System.out.println("change vporydnyk");
			return modelAndView;
		} else  if(Integer.parseInt(addupdate) == 2) {
			// Змінюємо дані впорядника
			datebegin = new Date(dateFormat.parse(datestartchange).getTime());
			
			if(param == 0){
				listVporyadnyk = vporyadnykDao.findVporyadnykForHurtok(Long.parseLong(hurtokidstr), Long.parseLong(kurinupuidstr), Long.parseLong(stridperson));
				for (Vporyadnyk vp : listVporyadnyk) {
					vporyadnyk = new Vporyadnyk(vp.getId(), datebegin, vp.getDateend(), vp.getKurinupu(), vp.getHurtokupu(), vp.getPersons(), vp.getHashcode());
					vporyadnykDao.update(vporyadnyk);
				}
			} else if(param == 1){
				listVporyadnyk = vporyadnykDao.findVporyadnykForSamHurtok(Long.parseLong(samhurtokid), Long.parseLong(stridperson));
				for (Vporyadnyk vp : listVporyadnyk) {
					vporyadnyk = new Vporyadnyk(vp.getId(), datebegin, vp.getDateend(), vp.getSamhurtokupu(), vp.getPersons(), vp.getHashcode());
					vporyadnykDao.update(vporyadnyk);
				}
			}
			
			return modelAndView;
		} else if(Integer.parseInt(addupdate) == -1){
			
			// Пошук впорядника за куренем та гуртком
			if(param == 0){
				listVporyadnyk = vporyadnykDao.findVporyadnykForHurtok(Long.parseLong(hurtokidstr), Long.parseLong(kurinupuidstr));
			} else if(param == 1) {
				listVporyadnyk = vporyadnykDao.findVporyadnykForSamHurtok(Long.parseLong(samhurtokid));
			}
			String datebeginstr = null;
			Long persId = null;
			for (Vporyadnyk vp : listVporyadnyk) {
				datebeginstr = vp.getDatebegin().toString();
				persId = vp.getPersons().getId();
			}
			
			
			String day = datebeginstr.substring(8, 10);
			String month = datebeginstr.substring(5, 7);
			String year = datebeginstr.substring(0, 4);
			String datebeg = day+"."+month+"."+year;
			
			System.out.println("datebeg = "+datebeg);
			System.out.println("persId = "+persId);
			
			String fullname = null;
			listPersons = personsDao.findOnePersons(persId);
			for (Persons ps : listPersons) {
				fullname = ps.getLastName()+ " "+ ps.getFirstName();
			}
			String JSON = "{\"datebegin\""+":\""+datebeg+"\", "+"\"idperson\""+":"+persId+", \"fullname\""+":"+"\""+
			fullname+"\"}";
			System.out.println("JSON\n"+JSON);
			response.getWriter().println(JSON);
			response.getWriter().close();
			return modelAndView;
		}
		
		return modelAndView;
	}
	private void addnewVporyadnyk(Integer param, String kurinupuidstr, String hurtokidstr, String samhurtokid, String newvporyadnykstr, String newdatefromvpstr) throws Exception{
		String hashcode = null;
		String sfullname = null;
		String namehurtok = null;
		Integer sexHurtokSM = null;
		Long stanID = null;
		String stanytsya = null;
		String emailVpor = null;
		try {
			datebegin = new Date(dateFormat.parse(newdatefromvpstr).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		vporyadnyk = new Vporyadnyk();
		vporyadnyk.setDatebegin(datebegin);
		
		// param: 0 - kurin, 1 - smhurtok
		
		if(param == 0){
			listkurinUPU = kurinUPUDao.findOneKurinUPU(Long.parseLong(kurinupuidstr));
	        for (KurinUPU kUPU : listkurinUPU) {
				kurinUPU = new KurinUPU(kUPU.getId(), kUPU.getNumberKurin(), kUPU.getSexKurin(), kUPU.getNameKurin(),
						kUPU.getDateRegister(), kUPU.getEmailKurin(), kUPU.getHashid(), kUPU.getStanytsya());
			}
			vporyadnyk.setKurinupu(kurinUPU);
			
			listHurtokUPU = hurtokUPUDao.findHurtokUPU(Long.parseLong(hurtokidstr));
			for (HurtokUPU hurtUPU : listHurtokUPU) {
				hurtokUPU = new HurtokUPU(hurtUPU.getId(), hurtUPU.getNameHurtok(), hurtUPU.getDateRegister(), 
						hurtUPU.getDateEnd(), hurtUPU.getNumber(), hurtUPU.getVidznakaHurtka(), hurtUPU.getKurinUPU()); 
				namehurtok = hurtUPU.getNameHurtok();
			}
			vporyadnyk.setHurtokupu(hurtokUPU);
		} else if(param == 1){
			listSamHurtokUPU = samHurtokUPUDao.findOneSamHurtok(Long.parseLong(samhurtokid));
			for (SamHurtokUPU smhupu : listSamHurtokUPU) {
				samHurtokUPU = new SamHurtokUPU(smhupu.getId(), smhupu.getNameSamHurtok(), smhupu.getSexSamHurtok(), smhupu.getEmailSamHurtok(), smhupu.getDateFrom(), smhupu.getDateTo(), smhupu.getHashidhurtok(), smhupu.getVidznakaHurtka(), smhupu.getStanytsya());
				namehurtok = smhupu.getNameSamHurtok();
				sexHurtokSM = smhupu.getSexSamHurtok();
				stanID = smhupu.getStanytsya().getId();
			}
			vporyadnyk.setSamhurtokupu(samHurtokUPU);
			liStanytsyas = stanystyaDao.findStanytsya(stanID);
			for (Stanytsya stan : liStanytsyas) {
				stanytsya = stan.getNamestan();
			}
		}
		
		
		
		listPersons = personsDao.findOnePersons(Long.parseLong(newvporyadnykstr));
        for (Persons prs : listPersons) {
			persons = new Persons(prs.getId(), prs.getLastName(), prs.getFirstName(), prs.getDatebirth(), 
					prs.getPhoneNumber(), prs.getEmail(), prs.getDateSworn(), prs.getAddress(), prs.getJobeducation());
			sfullname = prs.getFirstName()+prs.getLastName();
			emailVpor = prs.getEmail();
		}
        hashcode = MD5.getHash(namehurtok+sfullname);
		vporyadnyk.setPersons(persons);
		vporyadnyk.setHashcode(hashcode);
		vporyadnykDao.addVporyadnyk(vporyadnyk);
		
		if(param == 1){			 
				
				String first = "<h2>СКОБ!</h2>";
				String second = null;
				if(sexHurtokSM == 0){
					second = "<p>Друже виховник, гуртковий зареєстрував самостійний гурток "+ namehurtok +"  станиці "+stanytsya+"</p>";
				} else if(sexHurtokSM == 1) {
					second = "<p>Подруго виховниця, гурткова зареєструвала самостійний гурток "+ namehurtok + " станиці "+stanytsya+"</p>";
				} else if(sexHurtokSM == 2){
					second = "<p>Друже/Подруго виховник/ця, гуртковий/ва зареєстрував/ла самостійний гурток "+ namehurtok + " станиці "+stanytsya+"</p>";
				}
				
				String third = "<p>Для перегляду/редагування та затвердженя даних самостійного гуртка та її членів потрібно використовувати кодове слово: <b>"+hashcode+"</b></p>";
				
				String forth = "<p><b>ПРОХАННЯ</b> не передавайте свого коду стороннім особам. Дбайте про безпеку даних</p>";
				
				String text = first + second +third+forth;
		        
		        

				SendEmail sendEmail = new SendEmail("techniclsuport@gmail.com", "@ctiv@tion");
				try {
					sendEmail.send("Реєстр самостійного гуртка", text, "vasylshvv@gmail.com", emailVpor);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
		}
		
	}
}
