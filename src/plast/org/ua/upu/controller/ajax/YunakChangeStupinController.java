package plast.org.ua.upu.controller.ajax;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.StupinDao;
import plast.org.ua.upu.dao.UladDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.IStupinDao;
import plast.org.ua.upu.idao.IUladDao;
import plast.org.ua.upu.pojo.PersonsUPUChangeStupinPojo;
import plast.org.ua.upu.table.PersonStupinUlad;
import plast.org.ua.upu.table.Persons;
import plast.org.ua.upu.table.Stupin;
import plast.org.ua.upu.table.Ulad;

public class YunakChangeStupinController implements Controller{
	private Stupin stupin;
	private Ulad ulad;
	private Persons persons;
	private Date datebegin, enddate;
	private PersonStupinUlad personStupinUlad;
	private IPersonsDao personDao = PersonsDao.getInstance();
	private IUladDao uladDao = UladDao.getInstance();
	private IStupinDao stupinDao = StupinDao.getInstance();
	private List<Persons> listPersons;
	private List<Ulad> listUlad;
	private List<Stupin> listStupin;
	private List<PersonsUPUChangeStupinPojo> listPersonChangeStupin;
	private List<PersonStupinUlad> listPersStupUlad;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        
			String personid = request.getParameter("idperson");
			String begindateStr = request.getParameter("begindate");
			String enddateStr = request.getParameter("enddate");
			String uladid = request.getParameter("ulad");
			String stupinid = request.getParameter("stupin");
			String changestupin = request.getParameter("changestupin");
			System.out.println("changestupin = "+changestupin);
			String changebutton = request.getParameter("changebutton");
			System.out.println("changebutton = "+changebutton);
			if(Integer.parseInt(changestupin) > 0  && Integer.parseInt(changebutton) == -1){
				datebegin = new Date(dateFormat.parse(begindateStr).getTime());
				if(!enddateStr.equals("")){
					enddate = new Date(dateFormat.parse(enddateStr).getTime());					
				} else {
					enddate = null;
				}
				
				listPersons = personDao.findOnePersons(Long.parseLong(personid));
				for (Persons ps : listPersons) {
					persons = new Persons(ps.getId(), ps.getLastName(), ps.getFirstName(), 
							ps.getDatebirth(), ps.getPhoneNumber(), ps.getEmail(), ps.getDateSworn(), 
							ps.getAddress(), ps.getJobeducation());
				}	
				
				
				listUlad = uladDao.findUlad(Long.parseLong(uladid));
				for (Ulad u : listUlad) {
					ulad = new Ulad(u.getId(), u.getNameulad());
				}
				
				
				listStupin = stupinDao.findStupin(Long.parseLong(stupinid));
				for (Stupin st : listStupin) {
					stupin = new Stupin(st.getId(), st.getNameStupin(), st.getUlad());
				}
				personStupinUlad = new PersonStupinUlad(Long.parseLong(changestupin), 
						datebegin, enddate, persons, stupin, ulad);
				personDao.updatePersStupUlad(personStupinUlad);
				
				listPersonChangeStupin = stupinDao.findStupinChange(Long.parseLong(personid));
				String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
		        		"<AllStupinYunak>\n";
	        	String xml02 = "";			
	        	String xml03 = "";
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
	        	String xml = xml01 + xml02 + xml03 + "</AllStupinYunak>";
	        	System.out.println(xml);
	    		response.getWriter().println(xml);
	    		response.getWriter().close();
				return modelAndView;
			} else if(Integer.parseInt(changestupin) > 0 && Integer.parseInt(changebutton) == 0){
				String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
		        		"<OnlyOneStupinUlad>\n";
				String xml02="";
				String xml03 = "";
				String idulad="";
				listPersStupUlad = stupinDao.findOnlyOneStupin(Long.parseLong(changestupin));
				for (PersonStupinUlad personStupinUlad : listPersStupUlad) {
					idulad = personStupinUlad.getUlad().getId().toString();
					String datefrom = personStupinUlad.getDatefrom().toString();									
					String dayf = datefrom.substring(8, 10);
					String monthf = datefrom.substring(5, 7);
					String yearf = datefrom.substring(0, 4);
					String datebegin = dayf+"."+monthf+"."+yearf;
					
					Date dateto = personStupinUlad.getDateto();
					String dateend = null;
					
					if(dateto!= null){
						String dayt = dateto.toString().substring(8, 10);
						String montht = dateto.toString().substring(5, 7);
						String yeart = dateto.toString().substring(0, 4);
						dateend = dayt+"."+montht+"."+yeart;
					} else {
						dateend = "";
					}
		        				
					xml02 = xml02 +"<onlyonestupin>\n" +
							"<id>"+personStupinUlad.getId()+"</id>\n"+
							"<begindate>"+datebegin+"</begindate>\n"+
							"<enddate>"+dateend+"</enddate>\n"+
							"<ulad>"+personStupinUlad.getUlad().getId()+"</ulad>\n"+
							"<stupin>"+personStupinUlad.getStupin().getId()+"</stupin>\n"+						
							"</onlyonestupin>\n";
		        	
				}
				listStupin = stupinDao.findStupinUlad(Long.parseLong(idulad));
				for (Stupin stupin : listStupin) {
					xml03 = xml03+"<stupins>\n"+
							"<id>"+stupin.getId()+"</id>\n"+
							"<stupinname>"+stupin.getNameStupin()+"</stupinname>\n"+	
							"</stupins>\n";
				}
				String xml = xml01 + xml02 +  xml03+ "</OnlyOneStupinUlad>";
	        	//System.out.println(xml);
	    		response.getWriter().println(xml);
	    		response.getWriter().close();
			} else if(Integer.parseInt(changestupin) == -1 && Integer.parseInt(changebutton) == -1) {
				personStupinUlad = new PersonStupinUlad();
				datebegin = new Date(dateFormat.parse(begindateStr).getTime());
				personStupinUlad.setDatefrom(datebegin);

				if(!enddateStr.equals("")){
					enddate = new Date(dateFormat.parse(enddateStr).getTime());
					personStupinUlad.setDateto(enddate);
				}
				listPersons = personDao.findOnePersons(Long.parseLong(personid));
				for (Persons ps : listPersons) {
					persons = new Persons(ps.getId(), ps.getLastName(), ps.getFirstName(), 
							ps.getDatebirth(), ps.getPhoneNumber(), ps.getEmail(), ps.getDateSworn(), 
							ps.getAddress(), ps.getJobeducation());
				}			
				personStupinUlad.setPersons(persons);
				
				listUlad = uladDao.findUlad(Long.parseLong(uladid));
				for (Ulad u : listUlad) {
					ulad = new Ulad(u.getId(), u.getNameulad());
				}
				personStupinUlad.setUlad(ulad);
				
				listStupin = stupinDao.findStupin(Long.parseLong(stupinid));
				for (Stupin st : listStupin) {
					stupin = new Stupin(st.getId(), st.getNameStupin(), st.getUlad());
				}
				personStupinUlad.setStupin(stupin);
				
				personDao.addPersStupUlad(personStupinUlad);
				
				listPersonChangeStupin = stupinDao.findStupinChange(Long.parseLong(personid));
				String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
		        		"<AllStupinYunak>\n";
	        	String xml02 = "";			
	        	String xml03 = "";
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
	        	String xml = xml01 + xml02 + xml03 + "</AllStupinYunak>";
	        //	System.out.println(xml);
	    		response.getWriter().println(xml);
	    		response.getWriter().close();
			return modelAndView;
			}
			return modelAndView; 
			
			
	}
}
