package plast.org.ua.upu.controller.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.pojo.PersonsUPUPojo;
import plast.org.ua.upu.pojo.PersonsUSPUPSPojo;

public class SelectStanytsyaPersonsController implements Controller{
	private List<PersonsUSPUPSPojo> listPersonUspUps;
	private IPersonsDao personsDao = PersonsDao.getInstance();
	private List<PersonsUPUPojo> listPerson;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView modelAndView = null;
			response.setContentType("text/html;charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
			String strstan = request.getParameter("idselstan");
			String kurinid = request.getParameter("kurinid");
			
			if(strstan!=null){
				 listPersonUspUps = personsDao.findPersonUSPUPS(Long.parseLong(strstan));
			        
			        String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
			        		"<AllPersonsUSPUPS>\n";
			        		String xml02 = "<personsuspups>\n"+ 
			        					   "<id>"+0+"</id>\n"+
			        					   "<stupin>"+""+"</stupin>\n"+
			        				       "<fullnamepersons>"+"Виберіть з списку"+"</fullnamepersons>\n"+
			        				       "</personsuspups>\n";
			        		String xml03 = "";	
			        for (PersonsUSPUPSPojo personsUSPUPSPojo : listPersonUspUps) {
			        	xml03 = xml03 + 
								"<personsuspups>\n"+
								"<id>" + personsUSPUPSPojo.getIdperson() + "</id>\n"+
								"<stupin>"+personsUSPUPSPojo.getStupin()+"</stupin>\n"+
								"<fullnamepersons>" + personsUSPUPSPojo.getLastname() + " " + personsUSPUPSPojo.getFirstname() + "</fullnamepersons>\n"+
								"</personsuspups>\n";
					}
			        String xml = xml01 + xml02 + xml03 + "</AllPersonsUSPUPS>";
					response.getWriter().println(xml);
					response.getWriter().close();
			} else if(kurinid!= null){
				listPerson = personsDao.findPerson(Long.parseLong(kurinid));
				String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
		        		"<AllPersonsUSPUPS>\n";
		        		String xml02 = "<personsuspups>\n"+ 
		        					   "<id>"+0+"</id>\n"+
		        					   "<stupin>"+""+"</stupin>\n"+
		        				       "<fullnamepersons>"+"Виберіть з списку"+"</fullnamepersons>\n"+
		        				       "</personsuspups>\n";
		        		String xml03 = "";	
		        for (PersonsUPUPojo personsUPUPojo : listPerson) {
		        	xml03 = xml03 + 
							"<personsuspups>\n"+
							"<id>" + personsUPUPojo.getIdperson() + "</id>\n"+
							"<stupin>"+personsUPUPojo.getNamestupin()+"</stupin>\n"+
							"<fullnamepersons>" + personsUPUPojo.getLastname() + " " + personsUPUPojo.getFirstname() + "</fullnamepersons>\n"+
							"</personsuspups>\n";
				}
		        String xml = xml01 + xml02 + xml03 + "</AllPersonsUSPUPS>";
				response.getWriter().println(xml);
				response.getWriter().close();
			} else {
				response.getWriter().println("BED PAGE");
				response.getWriter().close();
			}
			
			
		return modelAndView;
	}
}
