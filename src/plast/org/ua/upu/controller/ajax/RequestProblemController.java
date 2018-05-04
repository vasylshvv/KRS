package plast.org.ua.upu.controller.ajax;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.RequestProblemDao;
import plast.org.ua.upu.idao.IRequestProblemDao;
import plast.org.ua.upu.sendemail.SendEmail;
import plast.org.ua.upu.table.RequestProblem;

public class RequestProblemController implements Controller{
	private RequestProblem requestProblem;
	private IRequestProblemDao requestProblemDao = RequestProblemDao.getInstance();
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ModelAndView modelAndView = null;
        Enumeration<String> param = request.getParameterNames();
        while (param.hasMoreElements()) {
			String string = (String) param.nextElement();
			System.out.println("param = "+string);
		}
        String sfullname = new String(request.getParameter("sfullname").getBytes("iso-8859-1"),"UTF-8");
        System.out.println("sfullname = "+ sfullname);
        
        String email = request.getParameter("email");
        System.out.println("email = "+ email);
        
        String textrequest = new String(request.getParameter("textrequest").getBytes("iso-8859-1"),"UTF-8");
        System.out.println("textrequest = "+ textrequest);
        
        requestProblem = new RequestProblem();
        requestProblem.setSfullname(sfullname);
        requestProblem.setEmail(email);
        requestProblem.setTextrequest(textrequest);
        requestProblem.setSysdate(new Date());
        try{
        	requestProblemDao.addProblem(requestProblem);
        	String text = "Повідомлення від користувачів Звітування КБ УПЮ";
			SendEmail sendEmail = new SendEmail("techniclsuport@gmail.com", "@ctiv@tion");
			sendEmail.send("ERROR", text, "vasylshvv@gmail.com", "vasylshvv@gmail.com");
        	response.getWriter().println("success");
        	response.getWriter().close();
        } catch (Exception e) {
        	response.getWriter().println("failed");
        	response.getWriter().close();
		}
        
        
        
        
		return modelAndView;
	}
}
