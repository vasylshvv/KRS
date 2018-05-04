package plast.org.ua.upu.controller.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.KurinUSPUPSDao;
import plast.org.ua.upu.dao.UladDao;
import plast.org.ua.upu.idao.IKurinUSPUPSDao;
import plast.org.ua.upu.idao.IUladDao;
import plast.org.ua.upu.table.KurinUSPUPS;
import plast.org.ua.upu.table.Stanytsya;
import plast.org.ua.upu.table.Ulad;

public class KurinUSPUPUController implements Controller{
	private KurinUSPUPS kurinUspUps;
	private Ulad ulad;
	private List<Ulad> listUlad;
	private List<KurinUSPUPS> listKurinUspUps;
	private IUladDao uladDao = UladDao.getInstance();
	private IKurinUSPUPSDao kurinUspUpsDao = KurinUSPUPSDao.getInstance();
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		ModelAndView modelAndView = null;
		
			String kurinNameUSPUPS = request.getParameter("namekurin");
			String uladstr = request.getParameter("idulad");
		
			kurinUspUps = new KurinUSPUPS();
			kurinUspUps.setNamekurin(kurinNameUSPUPS);
			if(uladstr != null){
				Long uladid = Long.parseLong(uladstr);
				listUlad = uladDao.findUlad(uladid);
				for (Ulad uld : listUlad) {
					ulad = new Ulad(uld.getId(), uld.getNameulad());
				}
				kurinUspUps.setUlad(ulad);
			}
			kurinUspUpsDao.addKurin(kurinUspUps);
			
			listKurinUspUps = kurinUspUpsDao.findAllKurin();
			
			String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
	        		"<AllKurinUSPUPS>\n";
	        		String xml02 = "<kurinuspups>\n"+ 
	        					   "<id>"+0+"</id>\n"+
	        				       "<namekurin>"+"¬ибер≥ть кур≥нь"+"</namekurin>\n"+
	        				       "</kurinuspups>\n";
	        		String xml03 = "";	
	        for (KurinUSPUPS kurinUSPUPS: listKurinUspUps) {
	        	xml03 = xml03 + 
						"<kurinuspups>\n"+
						"<id>" + kurinUSPUPS.getId() + "</id>\n"+
						"<namekurin>" + kurinUSPUPS.getNamekurin() + "</namekurin>\n"+
						"</kurinuspups>\n";
			}
	        String xml = xml01 + xml02 + xml03 + "</AllKurinUSPUPS>";
    		response.getWriter().println(xml);
    		response.getWriter().close();
			
		return modelAndView;
	}
}
