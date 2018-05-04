package plast.org.ua.upu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.RequestProblemDao;
import plast.org.ua.upu.idao.IRequestProblemDao;
import plast.org.ua.upu.table.RequestProblem;

public class ComplaintsReqProblemController implements Controller{
	private IRequestProblemDao problemdao = RequestProblemDao.getInstance();
	private List<RequestProblem> listProblem;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
		ModelAndView modelAndView = new ModelAndView("/jsp/complaints.jsp");
		listProblem = problemdao.findAllProblem();
		modelAndView.addObject("listproblem", listProblem);
		return modelAndView;
	}
}
