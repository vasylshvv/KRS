package plast.org.ua.upu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.OkruhaDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.idao.IOkruhaDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.insert.InsertCampStatus;
import plast.org.ua.upu.insert.InsertKV;
import plast.org.ua.upu.insert.InsertStupin;
import plast.org.ua.upu.insert.InsertUlad;
import plast.org.ua.upu.insert.InsertVyshkil;
import plast.org.ua.upu.insert.InsertVyshkilTabir;
import plast.org.ua.upu.table.Okruha;
import plast.org.ua.upu.table.Stanytsya;

public class KurinRegistrationSystemController implements Controller{
//	private Stanytsya stanytsya;
	private List<Stanytsya> listStanytsya;
	private List<Okruha> listOkruha;
	private IStanystyaDao stanytsyaDao = StanytsyaDao.getInstance();
	private IOkruhaDao okruhaDao = OkruhaDao.getInstance();
	private InsertUlad insertUlad = new InsertUlad();
	private InsertStupin insertStupin = new InsertStupin();
	private InsertKV insertKV = new InsertKV();
	private InsertVyshkil insertVyshkil = new InsertVyshkil();
	private InsertVyshkilTabir insetrVyshkilTabir = new InsertVyshkilTabir();
	private InsertCampStatus insertCampStatus = new InsertCampStatus();
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
		ModelAndView modelAndView = new ModelAndView("index.jsp");
		
		if(insertUlad.count() == 0 ){
			insertUlad.insert();
		}
		if(insertStupin.count() == 0){
			insertStupin.insert();
		}
		if(insertKV.count() == 0){
			insertKV.insert();
		}
		if(insertVyshkil.count() == 0){
			insertVyshkil.insert();
		}
		if(insetrVyshkilTabir.count() == 0){
			insetrVyshkilTabir.insert();
		}
		
		if(insertCampStatus.count() == 0){
			insertCampStatus.insert();
		}
		
		listStanytsya = stanytsyaDao.findAllStanytsya();
		listOkruha = okruhaDao.findAllOkruha();
		modelAndView.addObject("liststanytsya", listStanytsya);
		modelAndView.addObject("listokruha", listOkruha);
		return modelAndView;
	}
}
