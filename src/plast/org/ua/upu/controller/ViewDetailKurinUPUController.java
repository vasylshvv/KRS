package plast.org.ua.upu.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.DilovedennyaDao;
import plast.org.ua.upu.dao.HurtokUPUDao;
import plast.org.ua.upu.dao.KVDao;
import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.dao.KurinUSPUPSDao;
import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.dao.StupinDao;
import plast.org.ua.upu.dao.UladDao;
import plast.org.ua.upu.dao.VporyadnykDao;
import plast.org.ua.upu.dao.VyshkilDao;
import plast.org.ua.upu.dao.VyshkilTabirDao;
import plast.org.ua.upu.dao.ZvyazkovyyDao;
import plast.org.ua.upu.idao.IDilovedennyaDao;
import plast.org.ua.upu.idao.IHurtokUPUDao;
import plast.org.ua.upu.idao.IKVDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.IKurinUSPUPSDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.idao.IStupinDao;
import plast.org.ua.upu.idao.IUladDao;
import plast.org.ua.upu.idao.IVporyadnykDao;
import plast.org.ua.upu.idao.IVyshkilDao;
import plast.org.ua.upu.idao.IVyshkilTabirDao;
import plast.org.ua.upu.idao.IZvyazkovyyDao;
import plast.org.ua.upu.pojo.PersonsUPUPojo;
import plast.org.ua.upu.pojo.PersonsUSPUPSPojo;
import plast.org.ua.upu.pojo.ProvidKurinPojo;
import plast.org.ua.upu.pojo.VporyadnykPojo;
import plast.org.ua.upu.pojo.ZvyazkovyyPojo;
import plast.org.ua.upu.table.DilovedennyaHurtokUPU;
import plast.org.ua.upu.table.DilovedennyaKurinUPU;
import plast.org.ua.upu.table.HurtokUPU;
import plast.org.ua.upu.table.KV;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.KurinUSPUPS;
import plast.org.ua.upu.table.PersonDilovodHurtok;
import plast.org.ua.upu.table.Stanytsya;
import plast.org.ua.upu.table.Stupin;
import plast.org.ua.upu.table.Ulad;
import plast.org.ua.upu.table.Vyshkil;
import plast.org.ua.upu.table.VyshkilTabir;

public class ViewDetailKurinUPUController implements Controller{
	private IStanystyaDao stanytsyaDao = StanytsyaDao.getInstance();
	private IKurinUPUDao kurinUPUDao = KurinUPUDao.getInstance();
	private IHurtokUPUDao hurtokUPUDao = HurtokUPUDao.getInstance();
	private IUladDao uladDao = UladDao.getInstance();
	private IStupinDao stupinDao = StupinDao.getInstance();
	private IPersonsDao personsDao = PersonsDao.getInstance();
	private IKurinUSPUPSDao kurinUSPUPSDao = KurinUSPUPSDao.getInstance();
	private IKVDao kvDao = KVDao.getInstance(); 
	private IVyshkilDao vyshkilDao = VyshkilDao.getInstance();
	private IVyshkilTabirDao vyshkilTabirDao = VyshkilTabirDao.getInstance();
	private IZvyazkovyyDao zvyazkovyyDao = ZvyazkovyyDao.getInstance();
	private IVporyadnykDao vporyadnykDao = VporyadnykDao.getInstance();
	private IDilovedennyaDao dilovedennyaDao = DilovedennyaDao.getInstance();
	private List<KurinUPU> listKurinUPU;
	private List<Stanytsya> listStanytsya;
	private List<Stanytsya> listStanytsyap;
	private List<HurtokUPU> listHurtokUPU;
	private List<Ulad> listUlad;
	private List<Ulad> listUladUPU;
	private List<Stupin> listStupin;
	private List<Stupin> listStupinUPU;
	private List<PersonsUPUPojo> listPerson;
	private List<KurinUSPUPS> listKurinUspUps;
	private List<KV> listKV;
	private List<Vyshkil> listVyshkil;
	private List<VyshkilTabir> listVyshkilTabir;
	private List<PersonsUSPUPSPojo> listPersonsUSPUPS;
	private List<ZvyazkovyyPojo> listZvyazkovyy;
	private List<VporyadnykPojo> listVporyadnyk;
	private List<DilovedennyaHurtokUPU> listDilovodHurtok;
	private List<DilovedennyaKurinUPU> listDilovodKurin;
	private List<PersonDilovodHurtok> listPersonDilovodHurt;
	private List<ProvidKurinPojo> listProvidKurin;
	private Ulad ulad;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("/jsp/viewdetailkurinupu.jsp");
		
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String string = (String) params.nextElement();
			System.out.println("param = "+string);
			System.out.println("value = "+request.getParameter(string));
		}
		
		String idkurin = request.getParameter("detail");
		System.out.println("id kurin for view dateil = "+idkurin);
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if(idkurin!= null){
        	Long selkur = Long.parseLong(idkurin);
        	Long selstan = null;
        	 listKurinUPU = kurinUPUDao.findOneKurinUPU(Long.parseLong(idkurin));
     		for (KurinUPU kurinUPU : listKurinUPU) {
     			System.out.println(kurinUPU.getNameKurin());
     			selstan = kurinUPU.getStanytsya().getId();
     		}
     		
     		listStanytsya = stanytsyaDao.findStanytsya(selstan);
			for (Stanytsya stanytsya : listStanytsya) {
				System.out.println(stanytsya.getNamestan());
			}
     		
			listHurtokUPU = hurtokUPUDao.findHurtokINKurinUPU(selkur);
			listUlad = uladDao.findAllUlad();
			listStupin = stupinDao.findAllStupin();
			
			listUladUPU = uladDao.findUlad("соч");
			Long uladId = null;
			for (Ulad uladUPU : listUladUPU) {
				uladId = uladUPU.getId();
			}
			listStupinUPU = stupinDao.findStupinUlad(uladId);
			
			listPerson = personsDao.findPerson(selkur);
			for (PersonsUPUPojo pers : listPerson) {
				System.out.println(pers.getNamestupin()+"\t"+pers.getPhonenumber()+" \t"+pers.getFirstname() + " "+pers.getLastname());
			}
			
			listStanytsyap = stanytsyaDao.findAllStanytsya();
			
			listKurinUspUps = kurinUSPUPSDao.findAllKurin();
			
			listKV = kvDao.findAll();
			listVyshkil = vyshkilDao.findAllVyshkil();
			listVyshkilTabir = vyshkilTabirDao.findAllVyshkilTabir();
			listPersonsUSPUPS = personsDao.findPersonUSPUPS(selstan);
			listZvyazkovyy = zvyazkovyyDao.findZvyazkovyyKurin(selkur);
			listVporyadnyk = vporyadnykDao.findAllVporyadnyk(selkur);
			listDilovodHurtok = dilovedennyaDao.findAllDilovedennyaHurtok();
			listDilovodKurin = dilovedennyaDao.findAllDilovedennyaKurin();
			listPersonDilovodHurt = dilovedennyaDao.findPersonKurinDilovodHurtok(selkur);
			listProvidKurin = dilovedennyaDao.findProvidKurin(selkur);
			
			modelAndView.addObject("liststanystya", listStanytsya);
			modelAndView.addObject("listkurinupu", listKurinUPU);
			modelAndView.addObject("listhurtokupu", listHurtokUPU);
			modelAndView.addObject("listulad", listUlad);
			modelAndView.addObject("liststupin", listStupin);
			modelAndView.addObject("liststupinupu", listStupinUPU);
			modelAndView.addObject("listperson", listPerson);
			modelAndView.addObject("listdilovodhurtok", listDilovodHurtok);
			modelAndView.addObject("listdilovodkurin", listDilovodKurin);
			modelAndView.addObject("listprovidkurin",listProvidKurin);
			
			modelAndView.addObject("liststanp", listStanytsyap);
			modelAndView.addObject("listkurinuspups", listKurinUspUps);
			modelAndView.addObject("listkv", listKV);
			modelAndView.addObject("listvyshkil", listVyshkil);
			modelAndView.addObject("listvyshkiltabir", listVyshkilTabir);
			modelAndView.addObject("listpersonsuspups", listPersonsUSPUPS);
			modelAndView.addObject("zvyazkovyy", listZvyazkovyy);
			modelAndView.addObject("vporyadnyk", listVporyadnyk);
			modelAndView.addObject("personsdilovodhurt", listPersonDilovodHurt);
			
			modelAndView.addObject("liststanystya", listStanytsya);
     		modelAndView.addObject("listkurinupu", listKurinUPU);
     		return modelAndView;
        } else {
        	return new ModelAndView("redirect:commonreport.html");
        }
       
	}
}
