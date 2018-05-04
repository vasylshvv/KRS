package plast.org.ua.upu.controller.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.StupinDao;
import plast.org.ua.upu.idao.IStupinDao;
import plast.org.ua.upu.table.KurinUSPUPS;
import plast.org.ua.upu.table.Stupin;

public class SelectStupinController implements Controller{
	private List<Stupin> listStupin;
	private IStupinDao stupinDao = StupinDao.getInstance();
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		ModelAndView modelAndView = null;
		
		String uladstr = request.getParameter("ulad");
		Long idUlad = Long.parseLong(uladstr);
		listStupin = stupinDao.findStupinUlad(idUlad);
		
		String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
        		"<AllStupin>\n";
        		String xml02 = "<stupin>\n"+ 
        					   "<id>"+0+"</id>\n"+
        				       "<namestupin>"+"¬ибер≥ть ступ≥нь"+"</namestupin>\n"+
        				       "</stupin>\n";
        		String xml03 = "";	
        for (Stupin stup: listStupin) {
        	xml03 = xml03 + 
					"<stupin>\n"+
					"<id>" + stup.getId() + "</id>\n"+
					"<namestupin>" + stup.getNameStupin() + "</namestupin>\n"+
					"</stupin>\n";
		}
        String xml = xml01 + xml02 + xml03 + "</AllStupin>";
		response.getWriter().println(xml);
		response.getWriter().close();
		
		return modelAndView;
	}
}
