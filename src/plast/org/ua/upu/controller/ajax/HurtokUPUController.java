package plast.org.ua.upu.controller.ajax;

import java.io.InputStream;
import java.sql.Blob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.HurtokUPUDao;
import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.idao.IHurtokUPUDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.table.HurtokUPU;
import plast.org.ua.upu.table.KurinUPU;


@MultipartConfig(maxFileSize = 10000000)
public class HurtokUPUController implements Controller{
	private HurtokUPU hurtokUPU;
	private KurinUPU kurinUPU;
	private IKurinUPUDao kurinUpuDao = KurinUPUDao.getInstance();
	private IHurtokUPUDao hurtokUPUDao = HurtokUPUDao.getInstance();
	private List<KurinUPU> listKurinUPU;
	private List<HurtokUPU> listHurtokUPU;
	private Date dateReg;
	private java.sql.Date dateRegist;
	private Date dateEnd;
	private java.sql.Date dateEND;
	private MultipartHttpServletRequest multi;
	private MultipartFile mfile;
	private InputStream inputStream = null;
	private Blob blob;
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = null;
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
       
		String strnumbhurt = request.getParameter("numbhurt");
		Integer numbhurt = Integer.parseInt(strnumbhurt);
		String strkurinid = request.getParameter("kurinid");
		Long kurinid = Long.parseLong(strkurinid);
		String datereg = request.getParameter("datereg");
		String dateend = request.getParameter("dateend");
		String hurtokname = new String(request.getParameter("hurtokname"));
		
		
		multi = (MultipartHttpServletRequest)request;
		mfile = multi.getFile("vidznaka");
		if(mfile!=null) {
			inputStream = mfile.getInputStream();
			blob = Hibernate.createBlob(inputStream);
		}
		
		listKurinUPU = kurinUpuDao.findOneKurinUPU(kurinid);
		for (KurinUPU kurinUPU2 : listKurinUPU) {
			kurinUPU = new KurinUPU(kurinUPU2.getId(), 
					kurinUPU2.getNumberKurin(), kurinUPU2.getSexKurin(), 
					kurinUPU2.getNameKurin(), kurinUPU2.getDateRegister(), 
					kurinUPU2.getEmailKurin(), kurinUPU2.getHashid(), kurinUPU2.getStanytsya());
		}
		
		hurtokUPU = new HurtokUPU();
		
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		dateReg = dateFormat.parse(datereg);
		dateRegist = new java.sql.Date(dateReg.getTime());
		if(dateend!=""){
			dateEnd = dateFormat.parse(dateend);
			dateEND = new java.sql.Date(dateEnd.getTime());
			hurtokUPU.setDateEnd(dateEND);
		}
		
		hurtokUPU.setDateRegister(dateRegist);
		hurtokUPU.setKurinUPU(kurinUPU);
		hurtokUPU.setNameHurtok(hurtokname);
		hurtokUPU.setNumber(numbhurt);
		hurtokUPU.setVidznakaHurtka(blob);
		hurtokUPUDao.addHurtokUPU(hurtokUPU);		
		return modelAndView;
	}
}
