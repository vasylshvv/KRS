package plast.org.ua.upu.controller.ajax;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

import plast.org.ua.upu.dao.DilovedennyaDao;
import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.idao.IDilovedennyaDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.pojo.PersonsUPUChangeDilovodKurinPojo;
import plast.org.ua.upu.table.DilovedennyaKurinUPU;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.PersonDilovodHurtok;
import plast.org.ua.upu.table.PersonDilovodKurin;
import plast.org.ua.upu.table.Persons;

public class YunakChangeDilovodKurinController implements Controller{
	private Date datebegin, dateend;
	private PersonDilovodKurin personDilKur;
	private DilovedennyaKurinUPU dilovodKurin;
	private Persons persons;
	private KurinUPU kurinUPU;
	private List<Persons> listPersons;
	private List<DilovedennyaKurinUPU> listDilovedennyaKurin;
	private List<KurinUPU> listKurinUPU;
	private List<PersonsUPUChangeDilovodKurinPojo> listPersChangeDilovodKurin;
	private List<PersonDilovodKurin> listPersonDilovodKurin;
	private IPersonsDao personDao = PersonsDao.getInstance();
	private IDilovedennyaDao dilovedennyaDao = DilovedennyaDao.getInstance();
	private IKurinUPUDao kurinUPUDao = KurinUPUDao.getInstance();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        	
        
        	String changedilovod = request.getParameter("changedilovod");
        	System.out.println("changedilovod = "+changedilovod);
        	String changebutton = request.getParameter("changebutton");
			System.out.println("changebutton = "+changebutton);
			String personid = request.getParameter("idperson");
			System.out.println("personid = "+personid);
			String begindateStr = request.getParameter("begindate");
			System.out.println("begindateStr = "+begindateStr);
			String enddateStr = request.getParameter("enddate");
			System.out.println("enddateStr = "+enddateStr);
			String seldilkurin = request.getParameter("seldilkurin");
			System.out.println("seldilkurin = "+seldilkurin);
			String kurinid = request.getParameter("kurinid");
			System.out.println("kurinid = "+kurinid);
			
			if(Integer.parseInt(changedilovod) == -1 && Integer.parseInt(changebutton) == -1){				
				System.out.println("ADD");
				personDilKur = new PersonDilovodKurin();
				datebegin = new Date(dateFormat.parse(begindateStr).getTime());
				personDilKur.setDatebegin(datebegin);
				if(!enddateStr.equals("")){
					dateend = new Date(dateFormat.parse(enddateStr).getTime());
					personDilKur.setDateend(dateend);
				}
				listDilovedennyaKurin = dilovedennyaDao.findOneDilovedennyaKurin(Long.parseLong(seldilkurin));
				for (DilovedennyaKurinUPU dk : listDilovedennyaKurin) {
					dilovodKurin = new DilovedennyaKurinUPU(dk.getId(),dk.getNameDilovod());
				}
				personDilKur.setDilovodkurin(dilovodKurin);
				
				listKurinUPU = kurinUPUDao.findOneKurinUPU(Long.parseLong(kurinid));
				for (KurinUPU kUPU : listKurinUPU) {
					kurinUPU = new KurinUPU(kUPU.getId(), kUPU.getNumberKurin(), kUPU.getSexKurin(), 
							kUPU.getNameKurin(), kUPU.getDateRegister(), kUPU.getEmailKurin(), kUPU.getHashid(), kUPU.getStanytsya());
				}
				personDilKur.setKurin(kurinUPU);
				
				listPersons = personDao.findOnePersons(Long.parseLong(personid));
				for (Persons pers : listPersons) {
					persons = new Persons(pers.getId(), pers.getLastName(), pers.getFirstName(), pers.getDatebirth(), 
							pers.getPhoneNumber(), pers.getEmail(), pers.getDateSworn(), pers.getAddress(), pers.getJobeducation());
				}
				personDilKur.setPersons(persons);
				dilovedennyaDao.addPersonDilovodKurin(personDilKur);
				
				listPersChangeDilovodKurin = dilovedennyaDao.findAllDilovodKurinPerson(Long.parseLong(personid));
				String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
		        		"<AllDilovodKurinYunak>\n";
		    	String xml02 = "";			
		    	String xml03 = "";
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
					
					xml03 = xml03+
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
				String xml = xml01 + xml02 + xml03 + "</AllDilovodKurinYunak>";
		  //  	System.out.println(xml);
				response.getWriter().println(xml);
				response.getWriter().close();
				return modelAndView;
			} else if(Integer.parseInt(changedilovod) > 0 && Integer.parseInt(changebutton) == 0){
				System.out.println("SELECT DILOVOD");
				String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
		        		"<OnlyOneDilovodKurin>\n";
				String xml02="";
				String xml03 = "";
				listPersonDilovodKurin = dilovedennyaDao.findOnlyOneDilovodKurin(Long.parseLong(changedilovod));
				for (PersonDilovodKurin pdk : listPersonDilovodKurin) {
					String datefrom = pdk.getDatebegin().toString();									
					String dayf = datefrom.substring(8, 10);
					String monthf = datefrom.substring(5, 7);
					String yearf = datefrom.substring(0, 4);
					String datebegin = dayf+"."+monthf+"."+yearf;
					
					Date dateto = pdk.getDateend();
					String dateend = null;
					
					if(dateto!= null){
						String dayt = dateto.toString().substring(8, 10);
						String montht = dateto.toString().substring(5, 7);
						String yeart = dateto.toString().substring(0, 4);
						dateend = dayt+"."+montht+"."+yeart;
					} else {
						dateend = "";
					}
					
					
				xml03 = xml03+
						"<dilovodkurin>\n" +
						"<id>"+pdk.getId()+"</id>\n"+
						"<begindate>"+datebegin+"</begindate>\n"+
						"<enddate>"+dateend+"</enddate>\n"+
						"<dilovodid>"+pdk.getDilovodkurin().getId()+"</dilovodid>\n"+						
						"</dilovodkurin>\n";
				}
				String xml = xml01 + xml02 + xml03 + "</OnlyOneDilovodKurin>";
	     //   	System.out.println(xml);
	    		response.getWriter().println(xml);
	    		response.getWriter().close();
				return modelAndView;
			} else if(Integer.parseInt(changedilovod) > 0 && Integer.parseInt(changebutton) == -1){
				System.out.println("UPDATE");
				datebegin = new Date(dateFormat.parse(begindateStr).getTime());
				if(!enddateStr.equals("")){
					dateend = new Date(dateFormat.parse(enddateStr).getTime());					
				} else {
					dateend = null;
				}
				listPersons = personDao.findOnePersons(Long.parseLong(personid));
				for (Persons ps : listPersons) {
					persons = new Persons(ps.getId(), ps.getLastName(), ps.getFirstName(), 
							ps.getDatebirth(), ps.getPhoneNumber(), ps.getEmail(), ps.getDateSworn(), 
							ps.getAddress(), ps.getJobeducation());
				}
				listKurinUPU = kurinUPUDao.findOneKurinUPU(Long.parseLong(kurinid));
				for (KurinUPU kUPU : listKurinUPU) {
					kurinUPU = new KurinUPU(kUPU.getId(), kUPU.getNumberKurin(), kUPU.getSexKurin(), 
							kUPU.getNameKurin(), kUPU.getDateRegister(), kUPU.getEmailKurin(), kUPU.getHashid(), kUPU.getStanytsya());
				}
				listDilovedennyaKurin = dilovedennyaDao.findOneDilovedennyaKurin(Long.parseLong(seldilkurin));
				for (DilovedennyaKurinUPU dk : listDilovedennyaKurin) {
					dilovodKurin = new DilovedennyaKurinUPU(dk.getId(),dk.getNameDilovod());
				}
				personDilKur = new PersonDilovodKurin(Long.parseLong(changedilovod), datebegin, dateend, persons, kurinUPU, dilovodKurin);
				personDao.updatePersDilovodKurin(personDilKur);
				listPersChangeDilovodKurin = dilovedennyaDao.findAllDilovodKurinPerson(Long.parseLong(personid));
				String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
		        		"<AllDilovodKurinYunak>\n";
		    	String xml02 = "";			
		    	String xml03 = "";
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
					
					xml03 = xml03+
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
				String xml = xml01 + xml02 + xml03 + "</AllDilovodKurinYunak>";
		//    	System.out.println(xml);
				response.getWriter().println(xml);
				response.getWriter().close();
				return modelAndView;
			}
			
		return modelAndView;
	}
}
