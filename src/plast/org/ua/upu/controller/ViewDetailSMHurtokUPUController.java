package plast.org.ua.upu.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.DilovedennyaDao;
import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.SamHurtokUPUDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.dao.VporyadnykDao;
import plast.org.ua.upu.idao.IDilovedennyaDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.idao.IVporyadnykDao;
import plast.org.ua.upu.pojo.PersonsUPUPojo;
import plast.org.ua.upu.pojo.VporyadnykSamurtokPojo;
import plast.org.ua.upu.table.DilovedennyaHurtokUPU;
import plast.org.ua.upu.table.PersonDilovodHurtok;
import plast.org.ua.upu.table.SamHurtokUPU;
import plast.org.ua.upu.table.Stanytsya;

public class ViewDetailSMHurtokUPUController implements Controller {
	private ISamHurtokUPUDao samHurtokUPUDao = SamHurtokUPUDao.getInstance();
	private IStanystyaDao stanytsyaDao = StanytsyaDao.getInstance();
	private IVporyadnykDao vporyadnykDao = VporyadnykDao.getInstance();
	private IPersonsDao personsDao = PersonsDao.getInstance();
	private IDilovedennyaDao dilovedennyaDao = DilovedennyaDao.getInstance();
	private List<SamHurtokUPU> listSmHurtok;
	private List<Stanytsya> listStanytsya;
	private List<VporyadnykSamurtokPojo> vporyadnyk;
	private List<PersonsUPUPojo> listPerson;
	private List<PersonDilovodHurtok> listPersonDilovodHurt;
	private List<DilovedennyaHurtokUPU> listDilovodHurtok;
	@SuppressWarnings("unused")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/jsp/viewdetailsamhurt.jsp");
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String string = (String) params.nextElement();
			System.out.println("param = " + string);
			System.out.println("value = " + request.getParameter(string));

		}

		String idHurtStr = request.getParameter("detail");
		Long idsamhurtok = Long.parseLong(idHurtStr);
		if (idsamhurtok != null) {
			Long selstan = null;
			listSmHurtok = samHurtokUPUDao.findOneSamHurtok(idsamhurtok);
			for (SamHurtokUPU smh : listSmHurtok) {
				System.out.println("Hurtok -> "+smh.getNameSamHurtok()+ "\t"+smh.getEmailSamHurtok());
				selstan = smh.getStanytsya().getId();
			}
			
			listStanytsya = stanytsyaDao.findStanytsya(selstan);
			for (Stanytsya stanytsya : listStanytsya) {
				System.out.println(stanytsya.getNamestan());
			}
			
			
			vporyadnyk = vporyadnykDao.findVporyadnykSamHurtok(idsamhurtok);
			
			listPerson = personsDao.findPersonSMHurtok(idsamhurtok);
			
			
			listPersonDilovodHurt = dilovedennyaDao.findPersonSMhurtokDilovodHurtok(idsamhurtok);
			listDilovodHurtok = dilovedennyaDao.findAllDilovedennyaHurtok();
			modelAndView.addObject("liststanytsya", listStanytsya);
			modelAndView.addObject("listsamhurtok", listSmHurtok);
			modelAndView.addObject("vporyadnyk", vporyadnyk);
			modelAndView.addObject("listperson", listPerson);
			modelAndView.addObject("personsdilovodhurt", listPersonDilovodHurt);
			modelAndView.addObject("listdilovodhurtok", listDilovodHurtok);
			
			return modelAndView;
		} else {
			return new ModelAndView("redirect:commonreport.html");
		}
	}
}
