package plast.org.ua.upu.controller.ajax;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.DilovedennyaDao;
import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.StupinDao;
import plast.org.ua.upu.idao.IDilovedennyaDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.IStupinDao;
import plast.org.ua.upu.pojo.PersonsUPUChangeDilovodHurtokPojo;
import plast.org.ua.upu.pojo.PersonsUPUChangeDilovodKurinPojo;
import plast.org.ua.upu.pojo.PersonsUPUChangeStupinPojo;
import plast.org.ua.upu.pojo.PersonsUPUPojo;
import plast.org.ua.upu.table.PersonStupinUlad;
import plast.org.ua.upu.table.Persons;

public class YunakDetailController implements Controller{
	private List<Persons> listPerson;
	private List<PersonsUPUChangeStupinPojo> listPersonChangeStupin;
	private List<PersonsUPUChangeDilovodKurinPojo> listPersChangeDilovodKurin;
	private List<PersonsUPUPojo> listPersonUpu;
	private List<PersonStupinUlad> listperStupUlad;
	private IPersonsDao personsDao = PersonsDao.getInstance();
	private IStupinDao stupinDao = StupinDao.getInstance();
	private List<PersonsUPUChangeDilovodHurtokPojo> listPersChangeDilovodHurt;
	private IDilovedennyaDao dilovedennyaDao = DilovedennyaDao.getInstance();
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String personidstr = request.getParameter("idperson");
        String kurinorsmhurt  = request.getParameter("kurinorsmhurt");
        System.out.println("kurinorsmhurt = "+kurinorsmhurt);
        
        if(personidstr != null){
        	Long personid = Long.parseLong(personidstr);
        	//listPerson = personDao.findOnePersons(personid);
        	
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
        	if(Integer.parseInt(kurinorsmhurt) == 0){
        		listPersonUpu = personsDao.findOnePersonsHurtok(personid);
        	} else if(Integer.parseInt(kurinorsmhurt) == 1){
        		listPersonUpu = personsDao.findOnePersonsSMHurtok(personid);
        	}
        	
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
				
				if(Integer.parseInt(kurinorsmhurt) == 0){
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
	        	} else if(Integer.parseInt(kurinorsmhurt) == 1){
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
        	
        	listPersonChangeStupin = stupinDao.findStupinChange(personid);
        	for (PersonsUPUChangeStupinPojo pchst : listPersonChangeStupin) {
					String datefrom = pchst.getBegindate().toString();									
					String dayf = datefrom.substring(8, 10);
					String monthf = datefrom.substring(5, 7);
					String yearf = datefrom.substring(0, 4);
					String datebegin = dayf+"."+monthf+"."+yearf;
					
					Date dateto = pchst.getEnddate();
					String dateend = null;
					
					if(dateto!= null){
						String dayt = dateto.toString().substring(8, 10);
						String montht = dateto.toString().substring(5, 7);
						String yeart = dateto.toString().substring(0, 4);
						dateend = dayt+"."+montht+"."+yeart;
					} else {
						dateend = null;
					}
					
					
				xml03 = xml03+
						"<changestupin>\n" +
						"<id>"+pchst.getId()+"</id>\n"+
						"<begindate>"+datebegin+"</begindate>\n"+
						"<enddate>"+dateend+"</enddate>\n"+
						"<stupin>"+pchst.getNamestupin()+"</stupin>\n"+						
						"</changestupin>\n";
			}
        	
        	if(Integer.parseInt(kurinorsmhurt) == 0){
        		listPersChangeDilovodHurt = dilovedennyaDao.findAllDilovodHurtokPerson(personid);
        	} else if(Integer.parseInt(kurinorsmhurt) == 1){
        		listPersChangeDilovodHurt = dilovedennyaDao.findAllDilovodSMHurtokPerson(personid);
        	}
        	
        	
        				
        	String xml04 = "";
    		for (PersonsUPUChangeDilovodHurtokPojo pcdh : listPersChangeDilovodHurt) {
    			String datefrom = pcdh.getDatebegin().toString();									
    			String dayf = datefrom.substring(8, 10);
    			String monthf = datefrom.substring(5, 7);
    			String yearf = datefrom.substring(0, 4);
    			String datebegin = dayf+"."+monthf+"."+yearf;
    			
    			Date dateto = pcdh.getDateend();
    			String dateend = null;
    			
    			if(dateto!= null){
    				String dayt = dateto.toString().substring(8, 10);
    				String montht = dateto.toString().substring(5, 7);
    				String yeart = dateto.toString().substring(0, 4);
    				dateend = dayt+"."+montht+"."+yeart;
    			} else {
    				dateend = null;
    			}
    			if(Integer.parseInt(kurinorsmhurt) == 0){
    				xml04 = xml04+
        					"<dilovodhurtok>\n" +
        					"<id>"+pcdh.getId()+"</id>\n"+
        					"<begindate>"+datebegin+"</begindate>\n"+
        					"<enddate>"+dateend+"</enddate>\n"+
        					"<hurtokid>"+pcdh.getIdhurtok()+"</hurtokid>\n"+
        					"<namehurtok>"+pcdh.getNamehurtok()+"</namehurtok>\n"+
        					"<personid>"+pcdh.getIdperson()+"</personid>\n"+
        					"<kurinid>"+pcdh.getIdkurin()+"</kurinid>\n"+
        					"<dilovedennyaid>"+pcdh.getIddilovod()+"</dilovedennyaid>\n"+
        					"<dilovedennya>"+pcdh.getNamedilovod()+"</dilovedennya>\n"+					
        					"</dilovodhurtok>\n";
    			} else if(Integer.parseInt(kurinorsmhurt) == 1){
    				xml04 = xml04+
        					"<dilovodhurtok>\n" +
        					"<id>"+pcdh.getId()+"</id>\n"+
        					"<begindate>"+datebegin+"</begindate>\n"+
        					"<enddate>"+dateend+"</enddate>\n"+
        					"<hurtokid>"+pcdh.getIdsmhurtok()+"</hurtokid>\n"+
        					"<namehurtok>"+pcdh.getNamehurtok()+"</namehurtok>\n"+
        					"<personid>"+pcdh.getIdperson()+"</personid>\n"+
        					"<dilovedennyaid>"+pcdh.getIddilovod()+"</dilovedennyaid>\n"+
        					"<dilovedennya>"+pcdh.getNamedilovod()+"</dilovedennya>\n"+					
        					"</dilovodhurtok>\n";
    			}
    			
    		}
        	
    		listPersChangeDilovodKurin = dilovedennyaDao.findAllDilovodKurinPerson(personid);
					
	    	String xml05 = "";
			for (PersonsUPUChangeDilovodKurinPojo pchdk : listPersChangeDilovodKurin) {
				String datefrom = pchdk.getDatebegin().toString();									
				String dayf = datefrom.substring(8, 10);
				String monthf = datefrom.substring(5, 7);
				String yearf = datefrom.substring(0, 4);
				String datebegin = dayf+"."+monthf+"."+yearf;
				
				Date dateto = pchdk.getDateend();
				String dateend = null;
				
				if(dateto!= null){
					String dayt = dateto.toString().substring(8, 10);
					String montht = dateto.toString().substring(5, 7);
					String yeart = dateto.toString().substring(0, 4);
					dateend = dayt+"."+montht+"."+yeart;
				} else {
					dateend = null;
				}
				
				xml05 = xml05+
						"<dilovodkurin>\n" +
						"<id>"+pchdk.getId()+"</id>\n"+
						"<begindate>"+datebegin+"</begindate>\n"+
						"<enddate>"+dateend+"</enddate>\n"+
						"<personid>"+pchdk.getIdperson()+"</personid>\n"+
						"<kurinid>"+pchdk.getIdkurin()+"</kurinid>\n"+
						"<dilovedennyaid>"+pchdk.getIddilovodkur()+"</dilovedennyaid>\n"+
						"<dilovedennya>"+pchdk.getNamedilovodkur()+"</dilovedennya>\n"+					
						"</dilovodkurin>\n";					
			}
    		
        	String xml = xml01 + xml02 + xml03 + xml04 + xml05 + "</AlldetailYunak>";
        	System.out.println(xml);
    		response.getWriter().println(xml);
    		response.getWriter().close();
        }
		
		return modelAndView;
	}
}
