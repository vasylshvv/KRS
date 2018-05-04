package plast.org.ua.upu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.FAQDao;
import plast.org.ua.upu.idao.IFAQDao;
import plast.org.ua.upu.table.FAQ;

public class FAQController implements Controller{
	private IFAQDao faqDao = FAQDao.getInstance();
	private List<FAQ> listFAQ;
		@Override
		public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView modelAndView = null;
			response.setContentType("text/html;charset=UTF-8");
	        request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
			listFAQ = faqDao.findAll();
			modelAndView = new ModelAndView("/jsp/faq.jsp");
			modelAndView.addObject("listfaq", listFAQ);
			
			return modelAndView;
		}
}
