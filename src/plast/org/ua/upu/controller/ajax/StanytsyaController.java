package plast.org.ua.upu.controller.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.OkruhaDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.idao.IOkruhaDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.table.Okruha;
import plast.org.ua.upu.table.Stanytsya;

public class StanytsyaController implements Controller{
	private Stanytsya stanytsya;
	private Okruha okruha;
	private IStanystyaDao standao = StanytsyaDao.getInstance();
	private IOkruhaDao okruhaDao = OkruhaDao.getInstance();
	private List<Stanytsya> liststan;
	private List<Okruha> listOkruha;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
			String namestan = request.getParameter("namestan");
			System.out.println("namestan = "+namestan);
			
			String idokruhaStr = request.getParameter("idokruha");
			System.out.println("idokruhaStr = "+idokruhaStr);
			Long idokruha = null;
			if(idokruhaStr!=null){
				idokruha = Long.parseLong(idokruhaStr);
			}
			listOkruha = okruhaDao.findOneOkruha(idokruha);
			for (Okruha o : listOkruha) {
				okruha = new Okruha(o.getId(), o.getNameokruha());
			}
				
				stanytsya = new Stanytsya();
				stanytsya.setNamestan(namestan);
				stanytsya.setOkruha(okruha);
				standao.addStan(stanytsya);
				liststan = standao.findAllStanytsya();
				String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
		        		"<AllStanytsya>\n";
		        		String xml02 = "<stanytsya>\n"+ 
		        					   "<id>"+0+"</id>\n"+
		        				       "<namestan>"+"Виберіть станицю"+"</namestan>\n"+
		        				       "</stanytsya>\n";
		        		String xml03 = "";	
		        for (Stanytsya stanytsya : liststan) {
		        	xml03 = xml03 + 
							"<stanytsya>\n"+
							"<id>" + stanytsya.getId() + "</id>\n"+
							"<namestan>" + stanytsya.getNamestan() + "</namestan>\n"+
							"</stanytsya>\n";
				}
		        String xml = xml01 + xml02 + xml03 + "</AllStanytsya>";
	    		response.getWriter().println(xml);
	    		response.getWriter().close();
			
		return modelAndView;
	}
}
