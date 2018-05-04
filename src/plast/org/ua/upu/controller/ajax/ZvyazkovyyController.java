package plast.org.ua.upu.controller.ajax;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.dao.ZvyazkovyyDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.idao.IZvyazkovyyDao;
import plast.org.ua.upu.md.MD5;
import plast.org.ua.upu.sendemail.SendEmail;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.Persons;
import plast.org.ua.upu.table.Stanytsya;
import plast.org.ua.upu.table.Zvyazkovyy;

public class ZvyazkovyyController implements Controller{
	private Zvyazkovyy zvyazkovyy;
	private KurinUPU kurinUPU;
	private Persons persons;
	private Stanytsya stanytsya;
	private IZvyazkovyyDao zvyazkovyyDao = ZvyazkovyyDao.getInstance();
	private IPersonsDao personsDao = PersonsDao.getInstance();
	private IKurinUPUDao kurinUPUDao = KurinUPUDao.getInstance();
	private IStanystyaDao stanystyaDao = StanytsyaDao.getInstance();
	private Date datebegin, dateend;
	private List<KurinUPU> listkurinUPU;
	private List<Persons> listPersons;
	private List<Zvyazkovyy> listZvyazkovyy;
	private List<Stanytsya> listStanytsya; 
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        
        String kurinstr = request.getParameter("kurinupuid");
        System.out.println("kurinstr = "+kurinstr);
        String olddatestr = request.getParameter("olddateto");
        System.out.println("olddatestr = "+olddatestr);
        String newdatestr = request.getParameter("newdatefrom");
        System.out.println("newdatestr = "+newdatestr);
        String newzvyazkovyy = request.getParameter("newzvyazkovyy");
        System.out.println("newzvyazkovyy = "+newzvyazkovyy);
        String oldzvyazkovyy = request.getParameter("oldzvyazkovyy");
        System.out.println("oldzvyazkovyy = "+oldzvyazkovyy);
        
        String idzvyazkovyy = request.getParameter("idzvyazkovyy");
        System.out.println("idzvyazkovyy = "+idzvyazkovyy);
        String datebegzv = request.getParameter("datebegzv");
        System.out.println("datebegzv = "+datebegzv);
        
        if(idzvyazkovyy!=null){
        	datebegin = new Date(dateFormat.parse(datebegzv).getTime());
        	listZvyazkovyy = zvyazkovyyDao.findOneZvyazkovyy(Long.parseLong(idzvyazkovyy));
        	for (Zvyazkovyy zv : listZvyazkovyy) {
				zvyazkovyy = new Zvyazkovyy(zv.getId(), datebegin, zv.getDateend(), zv.getKurinupu(), zv.getPersons(), zv.getHashcode());
				zvyazkovyyDao.updateOldZvyazkovyy(zvyazkovyy);
			} 
        	return modelAndView;
        } else {
        	if(!oldzvyazkovyy.equals("0")){
            	listZvyazkovyy = zvyazkovyyDao.findOneZvyazkovyy(Long.parseLong(oldzvyazkovyy));
            	dateend = new Date(dateFormat.parse(olddatestr).getTime());
            	for (Zvyazkovyy zv : listZvyazkovyy) {
    				zvyazkovyy = new Zvyazkovyy(zv.getId(), zv.getDatebegin(), dateend, zv.getKurinupu(), zv.getPersons(), zv.getHashcode());
    			}
            	zvyazkovyyDao.updateOldZvyazkovyy(zvyazkovyy);
            } 
            
            datebegin = new Date(dateFormat.parse(newdatestr).getTime());
            zvyazkovyy = new Zvyazkovyy();
            zvyazkovyy.setDatebegin(datebegin);
            //zvyazkovyy.setDateend(dateend);
            listkurinUPU = kurinUPUDao.findOneKurinUPU(Long.parseLong(kurinstr));
            String namekurin = null;
            Integer sexKurin = null;
            Integer numberKurin = null;
            String kurinName = null;
            String stanytsya = null;
            Long stanID = null;
            
            for (KurinUPU kUPU : listkurinUPU) {
    			kurinUPU = new KurinUPU(kUPU.getId(), kUPU.getNumberKurin(), kUPU.getSexKurin(), kUPU.getNameKurin(),
    					kUPU.getDateRegister(), kUPU.getEmailKurin(), kUPU.getHashid(), kUPU.getStanytsya());
    			namekurin = kUPU.getNameKurin();
    			sexKurin = kUPU.getSexKurin();
    			numberKurin = kUPU.getNumberKurin();
    			kurinName = kUPU.getNameKurin();
    			stanID = kUPU.getStanytsya().getId();
    			
    		}
            zvyazkovyy.setKurinupu(kurinUPU);
            
            listStanytsya = stanystyaDao.findStanytsya(stanID);
            for (Stanytsya stan : listStanytsya) {
				stanytsya = stan.getNamestan();
			}
            
            listPersons = personsDao.findOnePersons(Long.parseLong(newzvyazkovyy));
            String fullnamezv = null;
            String emailZvyaz = null;
            for (Persons prs : listPersons) {
    			persons = new Persons(prs.getId(), prs.getLastName(), prs.getFirstName(), prs.getDatebirth(), 
    					prs.getPhoneNumber(), prs.getEmail(), prs.getDateSworn(), prs.getAddress(), prs.getJobeducation());
    			fullnamezv = prs.getFirstName()+prs.getLastName();
    			emailZvyaz = prs.getEmail();
    		}
             
            zvyazkovyy.setPersons(persons);            
            String hashcode = MD5.getHash(fullnamezv+namekurin);
            zvyazkovyy.setHashcode(hashcode);
            zvyazkovyyDao.addZvyazkovyy(zvyazkovyy);
            
            
            
            String first = "<h2>СКОБ!</h2>";
			String second = null;
			String numbKurStr = null;
			if(numberKurin == 0){
				numbKurStr = "підг. ";
			} else {
				numbKurStr = numberKurin.toString();
			}
			
			if(sexKurin == 0){
				second = "<p>Друже зв'язковий курінний зареєстрував курінь "+numbKurStr +" ім. "+kurinName+ " станиці "+stanytsya+"</p>";						
			} else if(sexKurin == 1) {
				second = "<p>Подруго зв'язкова курінна зареєструвала курінь "+numbKurStr +" ім. "+kurinName+ " станиці "+stanytsya+"</p>";
			} else if(sexKurin == 2){
				second = "<p>Друже/Подруго зв'язковий/зв'язкова курінний/курінна "+numbKurStr +" ім. "+kurinName+ " станиці "+stanytsya+"</p>";
			}
			
			String third = "<p>Для перегляду/редагування та затвердженя даних курення та її членів потрібно використовувати кодове слово: <b>"+hashcode+"</b></p>";
			
			String forth = "<p><b>ПРОХАННЯ</b> не передавайте свого коду стороннім особам. Дбайте про безпеку даних</p>";
			
			String text = first + second +third+forth;
			SendEmail sendEmail = new SendEmail("techniclsuport@gmail.com", "@ctiv@tion");
			sendEmail.send("Реєстр куреня", text, "vasylshvv@gmail.com", emailZvyaz);
            
            
    		return modelAndView;
        }
        /* Якщо зімна звзкового відбувається*/
        
	}
}
