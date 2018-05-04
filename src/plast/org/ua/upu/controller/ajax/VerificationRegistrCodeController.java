package plast.org.ua.upu.controller.ajax;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.dao.SamHurtokUPUDao;
import plast.org.ua.upu.dao.VporyadnykDao;
import plast.org.ua.upu.dao.ZvyazkovyyDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.idao.IVporyadnykDao;
import plast.org.ua.upu.idao.IZvyazkovyyDao;
import plast.org.ua.upu.pojo.VporyadnykSamurtokPojo;
import plast.org.ua.upu.pojo.ZvyazkovyyPojo;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.SamHurtokUPU;
import plast.org.ua.upu.table.Zvyazkovyy;

public class VerificationRegistrCodeController implements Controller{
	private IKurinUPUDao kurinUPUDao = KurinUPUDao.getInstance();
	private ISamHurtokUPUDao samHurtokUPUDao = SamHurtokUPUDao.getInstance();
	private IZvyazkovyyDao zvyazkovyyDao = ZvyazkovyyDao.getInstance();
	private IVporyadnykDao vporyadnykDao = VporyadnykDao.getInstance();
	private List<KurinUPU> listKurin;
	private List<SamHurtokUPU> listSamHurtUPU;
	private List<ZvyazkovyyPojo> listZvyazkovyy;
	private List<VporyadnykSamurtokPojo> listVporyadnykSM;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		ModelAndView modelAndView = null;
		String selkur = request.getParameter("selkur");
		System.out.println("selkur = "+selkur);
		
		String samhurtok = request.getParameter("selsamhurt");
		System.out.println("samhurtok = "+samhurtok);
		
		String registrcode= request.getParameter("registrcode");
		System.out.println("registrcode = "+registrcode);
		
		String registretion = "";
		Long kurinID = null;
		Long samHurtID = null;
		if(selkur != null){
			listKurin = kurinUPUDao.findOneKurinUPU(Long.parseLong(selkur));
			
			for (KurinUPU kurinUPU : listKurin) {
				registretion = kurinUPU.getHashid();
				kurinID = kurinUPU.getId();
			}
		System.out.println("registretion = "+registretion);
			/*Перевірка звязкового*/
		String registrationzv = "";
		
		listZvyazkovyy = zvyazkovyyDao.findZvyazkovyyKurin(kurinID);
		for (ZvyazkovyyPojo zv : listZvyazkovyy) {
				registrationzv = zv.getHashcode();
				
			}
		
			if(registrationzv == null){
				if(registretion.equals(registrcode)){
					response.getWriter().println("1");
					response.getWriter().close();
				} else {
					response.getWriter().println("0");
					response.getWriter().close();
				}
			} else {
				if(registrationzv.equals(registrcode)){
					response.getWriter().println("3");
					response.getWriter().close();
				} else if(registretion.equals(registrcode)){
					response.getWriter().println("1");
					response.getWriter().close();
				} else {
					response.getWriter().println("0");
					response.getWriter().close();
				}
			}
		
			
		} else if (samhurtok!=null){
			listSamHurtUPU = samHurtokUPUDao.findOneSamHurtok(Long.parseLong(samhurtok));
			
			for (SamHurtokUPU smh : listSamHurtUPU) {
				registretion = smh.getHashidhurtok();
				samHurtID = smh.getId();
			}
			System.out.println("registretion = "+registretion);
			/*Перевірка впорядника*/
			String registrationvpsm = null;
			
			listVporyadnykSM = vporyadnykDao.findVporyadnykSamHurtok(samHurtID);
			for (VporyadnykSamurtokPojo vpsm : listVporyadnykSM) {
				registrationvpsm = vpsm.getHashcode();
			}
			if(registrationvpsm == null){
				if(registretion.equals(registrcode)){
					response.getWriter().println("2");
					response.getWriter().close();
				} else {
					response.getWriter().println("0");
					response.getWriter().close();
				}
			} else {
				if(registrationvpsm.equals(registrcode)){
					response.getWriter().println("4");
					response.getWriter().close();
				} else if(registretion.equals(registrcode)){
					response.getWriter().println("2");
					response.getWriter().close();
				} else {
					response.getWriter().println("0");
					response.getWriter().close();
				}
			}
			
		} else {
			response.getWriter().println("0");
			response.getWriter().close();
		}		
	return modelAndView;
	}
}
