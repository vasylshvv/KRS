package plast.org.ua.upu.controller.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.DilovedennyaDao;
import plast.org.ua.upu.idao.IDilovedennyaDao;
import plast.org.ua.upu.table.DilovedennyaHurtokUPU;
import plast.org.ua.upu.table.DilovedennyaKurinUPU;

public class DilovedennyaController implements Controller{
	private DilovedennyaHurtokUPU dilovodHurtok;
	private DilovedennyaKurinUPU dilovodKurin;
	private IDilovedennyaDao dilovedennyaDao = DilovedennyaDao.getInstance();
	private List<DilovedennyaHurtokUPU> listDilovodHurtok;
	private List<DilovedennyaKurinUPU> listDilovodKurin;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String namehurtokdil = request.getParameter("namehurtokdil");
        System.out.println("namehurtokdil = "+namehurtokdil);
        String namekurindil = request.getParameter("namekurindil");
        System.out.println("namekurindil = "+namekurindil);
        if(namehurtokdil!=null){
        	dilovodHurtok = new DilovedennyaHurtokUPU();
        	dilovodHurtok.setNameDilovodHurt(namehurtokdil);
        	dilovedennyaDao.addDilovodHurtok(dilovodHurtok);
        	
        	listDilovodHurtok = dilovedennyaDao.findAllDilovedennyaHurtok();
        	String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
	        		"<AllDilovedennya>\n";
        	String xml02 = "<dilovod>\n"+ 
					   "<id>"+0+"</id>\n"+
				       "<namedilovod>"+"Виберіть діловедення"+"</namedilovod>\n"+
				       "</dilovod>\n";
        	String xml03 = "";	
        	for (DilovedennyaHurtokUPU dilovedennyaHurtokUPU : listDilovodHurtok) {
        		xml03 = xml03 + 
						"<dilovod>\n"+
						"<id>" + dilovedennyaHurtokUPU.getId() + "</id>\n"+
						"<namedilovod>" + dilovedennyaHurtokUPU.getNameDilovodHurt() + "</м>\n"+
						"</dilovod>\n";
			}
        	  String xml = xml01 + xml02 + xml03 + "</AllDilovedennya>";
	    		response.getWriter().println(xml);
	    		response.getWriter().close();
	    		return modelAndView;
        } else if(namekurindil!=null){
        	dilovodKurin = new DilovedennyaKurinUPU();
        	dilovodKurin.setNameDilovod(namekurindil);
        	dilovedennyaDao.addDilovodKurin(dilovodKurin);
        	
        	listDilovodKurin = dilovedennyaDao.findAllDilovedennyaKurin();
        			String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
        	        		"<AllDilovedennyaKurin>\n";
                	String xml02 = "<dilovodkurennya>\n"+ 
        					   "<id>"+0+"</id>\n"+
        				       "<namedilovodkurin>"+"Виберіть діловедення"+"</namedilovodkurin>\n"+
        				       "</dilovodkurennya>\n";
                	String xml03 = "";
                	for (DilovedennyaKurinUPU dilovedennyaKurinUPU : listDilovodKurin) {
                		xml03 = xml03 +"<dilovodkurennya>\n"+ 
         					   "<id>"+dilovedennyaKurinUPU.getId()+"</id>\n"+
         				       "<namedilovodkurin>"+dilovedennyaKurinUPU.getNameDilovod()+"</namedilovodkurin>\n"+
         				       "</dilovodkurennya>\n";
					}
                	 String xml = xml01 + xml02 + xml03 + "</AllDilovedennyaKurin>";
     	    		response.getWriter().println(xml);
     	    		response.getWriter().close();
        	return modelAndView;
        }
		return modelAndView;
	}
}
