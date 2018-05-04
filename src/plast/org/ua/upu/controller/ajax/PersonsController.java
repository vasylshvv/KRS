package plast.org.ua.upu.controller.ajax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

import plast.org.ua.upu.dao.HurtokUPUDao;
import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.SamHurtokUPUDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.dao.StupinDao;
import plast.org.ua.upu.dao.UladDao;
import plast.org.ua.upu.idao.IHurtokUPUDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.idao.IStupinDao;
import plast.org.ua.upu.idao.IUladDao;
import plast.org.ua.upu.pojo.PersonsUPUChangeStupinPojo;
import plast.org.ua.upu.pojo.PersonsUPUPojo;
import plast.org.ua.upu.table.HurtokUPU;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.PersonKurinHurtok;
import plast.org.ua.upu.table.PersonStupinUlad;
import plast.org.ua.upu.table.Persons;
import plast.org.ua.upu.table.PersonsStanytsya;
import plast.org.ua.upu.table.SamHurtokUPU;
import plast.org.ua.upu.table.Stanytsya;
import plast.org.ua.upu.table.Stupin;
import plast.org.ua.upu.table.Ulad;

public class PersonsController implements Controller{
	private Persons persons;
	private PersonStupinUlad personStupinUlad;
	private PersonKurinHurtok personKurinHurtok;
	private Stupin stupin;
	private Ulad ulad;
	private KurinUPU kurinUPU;
	private HurtokUPU hurtokUPU;
	private SamHurtokUPU samHurtokUPU;
	private Stanytsya stanytsya;
	private PersonsStanytsya personsStanytsya;
	private Date birthdate, datebirth, dateswr, sworndate, datereg, datefrom;
	private IPersonsDao personsDao = PersonsDao.getInstance();	
	private IStupinDao stupinDao = StupinDao.getInstance();
	private IUladDao uladDao = UladDao.getInstance();
	private IKurinUPUDao kurinUPUDao = KurinUPUDao.getInstance();
	private IHurtokUPUDao hurtokUPUDao = HurtokUPUDao.getInstance();
	private ISamHurtokUPUDao samHurtokUPUDao = SamHurtokUPUDao.getInstance();
	private IStanystyaDao stanytsyaDao = StanytsyaDao.getInstance();
	private List<Stupin> listStupin;
	private List<Ulad> listUlad;
	private List<KurinUPU> listKurinUPU;
	private List<HurtokUPU> listHurtokUPU;
	private List<SamHurtokUPU> listSamHurtokUPU;
	private List<PersonsUPUPojo> listPersonUpu;
	private List<PersonStupinUlad> listperStupUlad;
	private List<Stanytsya> listStanytsya;
	private List<PersonKurinHurtok> listPersKurHurtok;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = null;
		
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String update = request.getParameter("update");
        System.out.println("update = "+update);
        if(update.equals("")){
        	update = null;
        }
        
        String kurinid = request.getParameter("kurinid");
        System.out.println("kurinid = "+kurinid);
        Long idkurin = (long) 0;
        if(kurinid!= null){
        	idkurin = Long.parseLong(kurinid);
        }
       
        String idselhurt = request.getParameter("idselhurt");
        System.out.println("idselhurt = "+idselhurt);
        Long idhurtok = (long) 0;
        if(idselhurt!=null){
        	idhurtok = Long.parseLong(idselhurt);
        }
        
        String samhurtokid = request.getParameter("samhurtokid");
        Long idsamhurtok = (long) 0;
        if(samhurtokid!=null){
        	idsamhurtok = Long.parseLong(samhurtokid);
        }
        System.out.println("samhurtokid = "+samhurtokid);
        
        String kurinorsmhurt  = request.getParameter("kurinorsmhurt");
        System.out.println("kurinorsmhurt = "+kurinorsmhurt);
        Integer param = null;
        if(kurinorsmhurt != null){
        	param = Integer.parseInt(kurinorsmhurt);
        }
         
        listHurtokUPU = hurtokUPUDao.findHurtokUPU(idhurtok);
        for (HurtokUPU hurtok : listHurtokUPU) {
			hurtokUPU = new HurtokUPU(hurtok.getId(), hurtok.getNameHurtok(), hurtok.getDateRegister(), 
					hurtok.getDateEnd(), hurtok.getNumber(), hurtok.getVidznakaHurtka(), hurtok.getKurinUPU());
		}
        
        listKurinUPU = kurinUPUDao.findOneKurinUPU(idkurin);
        for (KurinUPU kurin : listKurinUPU) {
			kurinUPU = new KurinUPU(kurin.getId(), kurin.getNumberKurin(), 
					kurin.getSexKurin(), kurin.getNameKurin(), kurin.getDateRegister(), 
					kurin.getEmailKurin(), kurin.getHashid(), kurin.getStanytsya());
		}
        listSamHurtokUPU = samHurtokUPUDao.findOneSamHurtok(idsamhurtok);
        for (SamHurtokUPU sm : listSamHurtokUPU) {
        	samHurtokUPU = new SamHurtokUPU(sm.getId(), sm.getNameSamHurtok(), sm.getSexSamHurtok(), 
        			sm.getEmailSamHurtok(), sm.getDateFrom(), sm.getDateTo(), sm.getHashidhurtok(), 
        			sm.getVidznakaHurtka(), sm.getStanytsya());
		}
         
        
        String lastname = request.getParameter("lastname");
		System.out.println("lastname = "+lastname);
		String firstname = request.getParameter("firstname");
		System.out.println("firstname = "+firstname);
		String datebirthday = request.getParameter("datebirthday");
		System.out.println("datebirthday = "+datebirthday);
		
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	    birthdate = dateFormat.parse(datebirthday);
	    datebirth = new Date(birthdate.getTime());
		
		String phonenumber = request.getParameter("phonenumber");
		System.out.println("phonenumber = "+phonenumber);
		String email = request.getParameter("email");
		System.out.println("email = "+email);
		String dateregistr = request.getParameter("dateregistr");
		
		datereg = dateFormat.parse(dateregistr);
	    datefrom = new Date(datereg.getTime());
		
		System.out.println("dateregistr = "+dateregistr);
		String datesworn = request.getParameter("datesworn");		
		System.out.println("datesworn = "+datesworn);
		
		String address = request.getParameter("address");
		System.out.println("address = "+address);
		
		String education = request.getParameter("education");
		System.out.println("education = "+education);   
		
		String strstanid = request.getParameter("stanid");
		System.out.println("stanid = "+strstanid);
        if(update != null){
        	String personidStr = request.getParameter("personid");
        	Long personid = Long.parseLong(personidStr);
        	if(datesworn!=""){
       			dateswr = dateFormat.parse(datesworn);
       			sworndate = new Date(dateswr.getTime());
       			persons = new Persons(personid, lastname, firstname, datebirth, phonenumber, email, sworndate, address, education);            	
            	personsDao.updatePerson(persons);
       		} else {
       			persons = new Persons(personid, lastname, firstname, datebirth, phonenumber, email, null, address, education);            	
            	personsDao.updatePerson(persons);
       		}
        	
        	listPersKurHurtok = personsDao.findHurtokKurin(personid);
        	if (idsamhurtok != 0){
	        	for (PersonKurinHurtok pkh : listPersKurHurtok) {
	        		personKurinHurtok = new PersonKurinHurtok(pkh.getId(), pkh.getDatefrom(), 
	        				pkh.getDateto(), persons, samHurtokUPU);	
	        		 
				}
        	} else {
        		for (PersonKurinHurtok pkh : listPersKurHurtok) {
	        		personKurinHurtok = new PersonKurinHurtok(pkh.getId(), pkh.getDatefrom(), 
	        				pkh.getDateto(), persons, kurinUPU, hurtokUPU);	
	        		 
				}
        	}
        	personsDao.updatePersKurinHurtok(personKurinHurtok);
        	
        	
        	
        	listperStupUlad = stupinDao.findStartPerson(personid);
        	List<Date> datestart= new ArrayList<Date>();
        	for (PersonStupinUlad psu : listperStupUlad) {        		
        		datestart.add(psu.getDatefrom());        		
			}
        	String startdateSTR = Collections.min(datestart).toString();
        	String dayStart = startdateSTR.substring(8, 10);
			String monthStart = startdateSTR.substring(5, 7);
			String yearStart = startdateSTR.substring(0, 4);
			String startdate =  dayStart+"."+monthStart+"."+yearStart;
        	 
        	String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
	        		"<AlldetailYunak>\n";
        	String xml02 = "";			
        	String xml03 = "";
        	
        	if(param == 0){
        		listPersonUpu = personsDao.findOnePersonsHurtok(personid);
            	for (PersonsUPUPojo persons : listPersonUpu) {
    				System.out.println(persons.getFirstname()+"\t"+persons.getLastname());
    				String db = persons.getBirthday().toString();
    				
    				String day = db.substring(8, 10);
    				String month = db.substring(5, 7);
    				String year = db.substring(0, 4);
    				String datebirth = day+"."+month+"."+year;
    				
    				String sworndate = "";
    				if(persons.getDatesworn()!=null){
    					String dtsw = persons.getDatesworn().toString();
    					String daysw = dtsw.substring(8, 10);
    					String monthsw = dtsw.substring(5, 7);
    					String yearsw = dtsw.substring(0, 4);
    					sworndate = daysw+"."+monthsw+"."+yearsw;
    				}
    				
    				
    				xml02 = xml02 + 
    						"<person>\n"+
    						"<id>" + persons.getIdperson() + "</id>\n"+
    						"<firstname>" + persons.getFirstname() + "</firstname>\n"+
    						"<lastname>"+persons.getLastname() +"</lastname>\n"+
    						"<birthday>"+datebirth +"</birthday>\n"+
    						"<datesworn>"+sworndate +"</datesworn>\n"+
    						"<address>"+persons.getAdress() +"</address>\n"+
    						"<education>"+persons.getEducation() +"</education>\n"+
    						"<phone>"+persons.getPhonenumber() +"</phone>\n"+
    						"<email>"+persons.getEmail() +"</email>\n"+
    						"<hurtok>"+persons.getIdhurtok() +"</hurtok>\n"+
    						"<startdate>"+startdate +"</startdate>\n"+						
    						"</person>\n";
    			}
            	
           
            	
            	
        	} else if(param == 1){
        		listPersonUpu = personsDao.findOnePersonsSMHurtok(personid);
        		for (PersonsUPUPojo persons : listPersonUpu) {
        			System.out.println(persons.getFirstname()+"\t"+persons.getLastname());
    				String db = persons.getBirthday().toString();
    				
    				String day = db.substring(8, 10);
    				String month = db.substring(5, 7);
    				String year = db.substring(0, 4);
    				String datebirth = day+"."+month+"."+year;
    				
    				String sworndate = "";
    				if(persons.getDatesworn()!=null){
    					String dtsw = persons.getDatesworn().toString();
    					String daysw = dtsw.substring(8, 10);
    					String monthsw = dtsw.substring(5, 7);
    					String yearsw = dtsw.substring(0, 4);
    					sworndate = daysw+"."+monthsw+"."+yearsw;
    				}
    				
    				
    				xml02 = xml02 + 
    						"<person>\n"+
    						"<id>" + persons.getIdperson() + "</id>\n"+
    						"<firstname>" + persons.getFirstname() + "</firstname>\n"+
    						"<lastname>"+persons.getLastname() +"</lastname>\n"+
    						"<birthday>"+datebirth +"</birthday>\n"+
    						"<datesworn>"+sworndate +"</datesworn>\n"+
    						"<address>"+persons.getAdress() +"</address>\n"+
    						"<education>"+persons.getEducation() +"</education>\n"+
    						"<phone>"+persons.getPhonenumber() +"</phone>\n"+
    						"<email>"+persons.getEmail() +"</email>\n"+
    						"<hurtok>"+persons.getIdsamhurtok() +"</hurtok>\n"+
    						"<startdate>"+startdate +"</startdate>\n"+						
    						"</person>\n";
				}
        	}
        	String xml = xml01 + xml02 +  "</AlldetailYunak>";
        	//System.out.println(xml);
    		response.getWriter().println(xml);
    		response.getWriter().close();
        	
        	
        	return modelAndView;
        } else {
        	 
   		/*
   		   String selectulad = request.getParameter("selectulad");
   		   System.out.println("selectulad = "+selectulad);
   		   String idselstupin = request.getParameter("idselstupin");
   		   System.out.println("idselstupin = "+idselstupin);		
   		*/
   		persons = new Persons();
   		persons.setDatebirth(datebirth);
   	 if(datesworn.equals("")){
   		datesworn = null;
     }
   		
   	 if(datesworn!=null){
   			dateswr = dateFormat.parse(datesworn);
   			sworndate = new Date(dateswr.getTime());
   			persons.setDateSworn(sworndate);
   		}
   		
   		
   		persons.setEmail(email);
   		persons.setFirstName(firstname);
   		persons.setLastName(lastname);
   		persons.setPhoneNumber(phonenumber);
   		persons.setAddress(address);
   		persons.setJobeducation(education);
   		personsDao.addPerson(persons);
   		
   		personStupinUlad = new PersonStupinUlad();
   		personStupinUlad.setDatefrom(datefrom);
   		//personStupinUlad.setDateto(dateto);
   		personStupinUlad.setPersons(persons);
   		
   		
   		//listStupin = stupinDao.findStupin(Long.parseLong(idselstupin));
   		listStupin = stupinDao.findStupinNeim("пл.прих.");
   		for (Stupin stupin2 : listStupin) {
   			stupin = new Stupin(stupin2.getId(), stupin2.getNameStupin(), stupin2.getUlad());
   		}
   				
   		/***ПОтрібно подумати оскільки при заповнені ступені і дата вступу можуть відрізнятися*/
   		 personStupinUlad.setStupin(stupin);  
   		
   		
   		//listUlad = uladDao.findUlad(Long.parseLong(selectulad));
   		 listUlad = uladDao.findUlad("УПЮ");
   		for (Ulad ulad2 : listUlad) {
   			ulad = new Ulad(ulad2.getId(), ulad2.getNameulad());
   		}
   		personStupinUlad.setUlad(ulad);		
   		personsDao.addPersStupUlad(personStupinUlad);
   		
   		personKurinHurtok = new PersonKurinHurtok();
   		personKurinHurtok.setDatefrom(datefrom);
   		//personKurinHurtok.setDateto(dateto);
   		
   		if(param == 0){
   			listKurinUPU = kurinUPUDao.findOneKurinUPU(Long.parseLong(kurinid));
   	   		for (KurinUPU kurinUPU2 : listKurinUPU) {
   	   			kurinUPU = new KurinUPU(kurinUPU2.getId(), kurinUPU2.getNumberKurin(), kurinUPU2.getSexKurin(), 
   	   					kurinUPU2.getNameKurin(), kurinUPU2.getDateRegister(), kurinUPU2.getEmailKurin(), 
   	   					kurinUPU2.getHashid(), kurinUPU2.getStanytsya());
   	   		}
   	   		
   	   		listHurtokUPU = hurtokUPUDao.findHurtokUPU(Long.parseLong(idselhurt));
   	   		for (HurtokUPU hurtokUPU2 : listHurtokUPU) {
   	   			hurtokUPU = new HurtokUPU(hurtokUPU2.getId(), hurtokUPU2.getNameHurtok(), hurtokUPU2.getDateRegister(),
   	   					hurtokUPU2.getDateEnd(), hurtokUPU2.getNumber(), hurtokUPU2.getVidznakaHurtka(), hurtokUPU2.getKurinUPU());
   	   		}
   	   		
	   	   	personKurinHurtok.setHurtokUPU(hurtokUPU);
	   		personKurinHurtok.setKurinUPU(kurinUPU);
   		} else if(param == 1){
   			listSamHurtokUPU = samHurtokUPUDao.findOneSamHurtok(Long.parseLong(samhurtokid));
   			for (SamHurtokUPU smh : listSamHurtokUPU) {
   				samHurtokUPU = new SamHurtokUPU(smh.getId(), smh.getNameSamHurtok(), smh.getSexSamHurtok(), smh.getEmailSamHurtok(), 
   						smh.getDateFrom(), smh.getDateTo(), smh.getHashidhurtok(), smh.getVidznakaHurtka(), smh.getStanytsya());
			}
   			personKurinHurtok.setSamhurtokupu(samHurtokUPU);
   		}
   		
   		
   		
   		
   		personKurinHurtok.setPersons(persons);		
   		personsDao.addPersKurinHurtok(personKurinHurtok);
   		
   	 /** add stanytsya persons data*/
        personsStanytsya = new PersonsStanytsya();
        listStanytsya = stanytsyaDao.findStanytsya(Long.parseLong(strstanid));
        for (Stanytsya stan : listStanytsya) {
			stanytsya = new Stanytsya(stan.getId(), stan.getNamestan(), stan.getOkruha());
		}
        personsStanytsya.setStanytsya(stanytsya);
        personsStanytsya.setPersons(persons);
        personsDao.addPersonStanytsya(personsStanytsya);
        
   		return modelAndView;
        }
         
	}
}
