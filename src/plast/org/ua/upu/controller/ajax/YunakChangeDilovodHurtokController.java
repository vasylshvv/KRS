package plast.org.ua.upu.controller.ajax;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.DilovedennyaDao;
import plast.org.ua.upu.dao.HurtokUPUDao;
import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.SamHurtokUPUDao;
import plast.org.ua.upu.idao.IDilovedennyaDao;
import plast.org.ua.upu.idao.IHurtokUPUDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.pojo.PersonsUPUChangeDilovodHurtokPojo;
import plast.org.ua.upu.table.DilovedennyaHurtokUPU;
import plast.org.ua.upu.table.HurtokUPU;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.PersonDilovodHurtok;
import plast.org.ua.upu.table.PersonKurinHurtok;
import plast.org.ua.upu.table.Persons;
import plast.org.ua.upu.table.SamHurtokUPU;

public class YunakChangeDilovodHurtokController implements Controller{
	private PersonDilovodHurtok personDilHurt;
	private PersonKurinHurtok personKurinHurtok;
	private DilovedennyaHurtokUPU dilovodHurtok;
	private Persons persons;
	private Date datebegin, dateend;
	private Long kurinid, hurtokid, smhurtokid;
	private KurinUPU kurinUPU;
	private HurtokUPU hurtokUPU;
	private SamHurtokUPU samHurtokUPU;
	private List<DilovedennyaHurtokUPU> listDilovodHurtok;
	private List<PersonDilovodHurtok> listPersonDilovodHurtok;
	private List<PersonKurinHurtok> listKurinHurtok;
	private List<PersonsUPUChangeDilovodHurtokPojo> listPersChangeDilovodHurt;
	private List<Persons> listPersons;
	private List<PersonKurinHurtok> listPersonKurinHurtok;
	private List<KurinUPU> listKurinUPU;
	private List<HurtokUPU> listHurtokUPU;
	private List<SamHurtokUPU> listSamHurtokUPU;
	private IDilovedennyaDao dilovedennyaDao = DilovedennyaDao.getInstance();
	private IPersonsDao personsDao = PersonsDao.getInstance();
	private IKurinUPUDao kurinUPUDao = KurinUPUDao.getInstance();
	private IHurtokUPUDao hurtokUPUDao = HurtokUPUDao.getInstance();
	private ISamHurtokUPUDao smHurtokUPUDao = SamHurtokUPUDao.getInstance();
	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = null;
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        	
    
        
        	String changedilovod = request.getParameter("changedilovod");
        	System.out.println("changedilovod = "+changedilovod);
        	String changebutton = request.getParameter("changebutton");
			System.out.println("changebutton = "+changebutton);
			String personid = request.getParameter("idperson");
			System.out.println("personid = "+personid);
			String begindateStr = request.getParameter("begindate");
			System.out.println("begindateStr = "+begindateStr);
			String enddateStr = request.getParameter("enddate");
			System.out.println("enddateStr = "+enddateStr);
			String seldilhurt = request.getParameter("seldilhurt");
			System.out.println("seldilhurt = "+seldilhurt);
			String kurinorsmhurt = request.getParameter("kurinorsmhurt");
			System.out.println("kurinorsmhurt = "+ kurinorsmhurt);
			String samhurtokid = request.getParameter("samhurtokid");
			System.out.println("samhurtokid = "+samhurtokid);
			
			// ADD dilovod person
			if(Integer.parseInt(changedilovod) == -1 && Integer.parseInt(changebutton) == -1){
				personDilHurt = new PersonDilovodHurtok();
				datebegin = new Date(dateFormat.parse(begindateStr).getTime());
				personDilHurt.setDatebegin(datebegin);
			if(!enddateStr.equals("")){
				dateend = new Date(dateFormat.parse(enddateStr).getTime());
				personDilHurt.setDateend(dateend);
			}
			listDilovodHurtok = dilovedennyaDao.findOneDilovedennyaHurtok(Long.parseLong(seldilhurt));
			for (DilovedennyaHurtokUPU dh : listDilovodHurtok) {
				dilovodHurtok = new DilovedennyaHurtokUPU(dh.getId(), dh.getNameDilovodHurt());
			}
			personDilHurt.setDilovodhurtok(dilovodHurtok);
			
			
			listKurinHurtok = personsDao.findHurtokKurin(Long.parseLong(personid));
			for (PersonKurinHurtok pkh : listKurinHurtok) {
				if(Integer.parseInt(kurinorsmhurt) == 0){
					personDilHurt.setHurtok(pkh.getHurtokUPU());
					personDilHurt.setKurin(pkh.getKurinUPU());
				} else if (Integer.parseInt(kurinorsmhurt) == 1){
					personDilHurt.setSmhurtok(pkh.getSamhurtokupu());
				}
				
				personDilHurt.setPersons(pkh.getPersons());
			}
			dilovedennyaDao.addPersonDilovodHurtok(personDilHurt);
			if(Integer.parseInt(kurinorsmhurt) == 0){
				listPersChangeDilovodHurt = dilovedennyaDao.findAllDilovodHurtokPerson(Long.parseLong(personid));
			} else if(Integer.parseInt(kurinorsmhurt) == 1){
				listPersChangeDilovodHurt = dilovedennyaDao.findAllDilovodSMHurtokPerson(Long.parseLong(personid));
			}
			String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
	        		"<AllDilovodHurtokYunak>\n";
	    	String xml02 = "";			
	    	String xml03 = "";
			for (PersonsUPUChangeDilovodHurtokPojo pcdh : listPersChangeDilovodHurt) {
				String datefrom = pcdh.getDatebegin().toString();									
				String dayf = datefrom.substring(8, 10);
				String monthf = datefrom.substring(5, 7);
				String yearf = datefrom.substring(0, 4);
				String datebegin = dayf+"."+monthf+"."+yearf;
				
				Date dateto = pcdh.getDateend();
				String dateend = null;
				
				if(dateto!= null){
					String dayt = dateto.toString().substring(8, 10);
					String montht = dateto.toString().substring(5, 7);
					String yeart = dateto.toString().substring(0, 4);
					dateend = dayt+"."+montht+"."+yeart;
				} else {
					dateend = null;
				}
				if(Integer.parseInt(kurinorsmhurt) == 0){
				xml03 = xml03+
						"<dilovodhurtok>\n" +
						"<id>"+pcdh.getId()+"</id>\n"+
						"<begindate>"+datebegin+"</begindate>\n"+
						"<enddate>"+dateend+"</enddate>\n"+
						"<hurtokid>"+pcdh.getIdhurtok()+"</hurtokid>\n"+
						"<namehurtok>"+pcdh.getNamehurtok()+"</namehurtok>\n"+
						"<personid>"+pcdh.getIdperson()+"</personid>\n"+
						"<kurinid>"+pcdh.getIdkurin()+"</kurinid>\n"+
						"<dilovedennyaid>"+pcdh.getIddilovod()+"</dilovedennyaid>\n"+
						"<dilovedennya>"+pcdh.getNamedilovod()+"</dilovedennya>\n"+					
						"</dilovodhurtok>\n";
				} else if(Integer.parseInt(kurinorsmhurt) == 1){
					xml03 = xml03+
							"<dilovodhurtok>\n" +
							"<id>"+pcdh.getId()+"</id>\n"+
							"<begindate>"+datebegin+"</begindate>\n"+
							"<enddate>"+dateend+"</enddate>\n"+
							"<hurtokid>"+pcdh.getIdsmhurtok()+"</hurtokid>\n"+
							"<namehurtok>"+pcdh.getNamehurtok()+"</namehurtok>\n"+
							"<personid>"+pcdh.getIdperson()+"</personid>\n"+
							"<dilovedennyaid>"+pcdh.getIddilovod()+"</dilovedennyaid>\n"+
							"<dilovedennya>"+pcdh.getNamedilovod()+"</dilovedennya>\n"+					
							"</dilovodhurtok>\n";
				}
			}
			String xml = xml01 + xml02 + xml03 + "</AllDilovodHurtokYunak>";
	    	System.out.println(xml);
			response.getWriter().println(xml);
			response.getWriter().close();
			return modelAndView;
			} else if(Integer.parseInt(changedilovod) > 0 && Integer.parseInt(changebutton) == 0){
				// one dilovodennya
				String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
		        		"<OnlyOneDilovodHurt>\n";
				String xml02="";
				String xml03 = "";
				listPersonDilovodHurtok = dilovedennyaDao.findOnlyOneDilovodHurtok(Long.parseLong(changedilovod));
				for (PersonDilovodHurtok pdh : listPersonDilovodHurtok) {
					String datefrom = pdh.getDatebegin().toString();									
					String dayf = datefrom.substring(8, 10);
					String monthf = datefrom.substring(5, 7);
					String yearf = datefrom.substring(0, 4);
					String datebegin = dayf+"."+monthf+"."+yearf;
					
					Date dateto = pdh.getDateend();
					String dateend = null;
					
					if(dateto!= null){
						String dayt = dateto.toString().substring(8, 10);
						String montht = dateto.toString().substring(5, 7);
						String yeart = dateto.toString().substring(0, 4);
						dateend = dayt+"."+montht+"."+yeart;
					} else {
						dateend = "";
					}
					
					
				xml03 = xml03+
						"<dilovodhurtok>\n" +
						"<id>"+pdh.getId()+"</id>\n"+
						"<begindate>"+datebegin+"</begindate>\n"+
						"<enddate>"+dateend+"</enddate>\n"+
						"<dilovodid>"+pdh.getDilovodhurtok().getId()+"</dilovodid>\n"+						
						"</dilovodhurtok>\n";
				}
				String xml = xml01 + xml02 + xml03 + "</OnlyOneDilovodHurt>";
	     //   	System.out.println(xml);
	    		response.getWriter().println(xml);
	    		response.getWriter().close();
				return modelAndView;
			} else if(Integer.parseInt(changedilovod) > 0 && Integer.parseInt(changebutton) == -1){
				datebegin = new Date(dateFormat.parse(begindateStr).getTime());
				if(!enddateStr.equals("")){
					dateend = new Date(dateFormat.parse(enddateStr).getTime());					
				} else {
					dateend = null;
				}
				listPersons = personsDao.findOnePersons(Long.parseLong(personid));
				for (Persons ps : listPersons) {
					persons = new Persons(ps.getId(), ps.getLastName(), ps.getFirstName(), 
							ps.getDatebirth(), ps.getPhoneNumber(), ps.getEmail(), ps.getDateSworn(), 
							ps.getAddress(), ps.getJobeducation());
				}
				listDilovodHurtok = dilovedennyaDao.findOneDilovedennyaHurtok(Long.parseLong(seldilhurt));
				for (DilovedennyaHurtokUPU dh : listDilovodHurtok) {
					dilovodHurtok = new DilovedennyaHurtokUPU(dh.getId(), dh.getNameDilovodHurt());
				}
				listPersonKurinHurtok = personsDao.findHurtokKurin(Long.parseLong(personid));
				for (PersonKurinHurtok pkh : listPersonKurinHurtok) {
					if(Integer.parseInt(kurinorsmhurt) == 0){
						kurinid = pkh.getKurinUPU().getId();
						hurtokid = pkh.getHurtokUPU().getId();
					} else if(Integer.parseInt(kurinorsmhurt) == 1){
						smhurtokid = pkh.getSamhurtokupu().getId();
					}
					
					
				}
				if(Integer.parseInt(kurinorsmhurt) == 0){
					listKurinUPU = kurinUPUDao.findOneKurinUPU(kurinid);
					for (KurinUPU k : listKurinUPU) {
						kurinUPU = new KurinUPU(k.getId(), k.getNumberKurin(), k.getSexKurin(), k.getNameKurin(),k.getDateRegister(), k.getEmailKurin(), k.getHashid(), k.getStanytsya());
					}
					listHurtokUPU = hurtokUPUDao.findHurtokUPU(hurtokid);
					for (HurtokUPU h : listHurtokUPU) {
						hurtokUPU = new HurtokUPU(h.getId(), h.getNameHurtok(), h.getDateRegister(), h.getDateEnd(), h.getNumber(), h.getVidznakaHurtka(),h.getKurinUPU());
					}
					personDilHurt = new PersonDilovodHurtok(Long.parseLong(changedilovod), datebegin, dateend, 
							persons, hurtokUPU, kurinUPU, dilovodHurtok);
				} else if(Integer.parseInt(kurinorsmhurt) == 1){
					listSamHurtokUPU = smHurtokUPUDao.findOneSamHurtok(smhurtokid);
					for (SamHurtokUPU smh : listSamHurtokUPU) {
						samHurtokUPU = new SamHurtokUPU(smh.getId(), smh.getNameSamHurtok(), smh.getSexSamHurtok(), smh.getEmailSamHurtok(), smh.getDateFrom(), 
								smh.getDateTo(), smh.getHashidhurtok(), smh.getVidznakaHurtka(), smh.getStanytsya());
					}
					personDilHurt = new PersonDilovodHurtok(Long.parseLong(changedilovod), datebegin, dateend, persons, samHurtokUPU, dilovodHurtok);
				}
				
				personsDao.updatePersDilovodHurtokd(personDilHurt);
				if(Integer.parseInt(kurinorsmhurt) == 0){
					listPersChangeDilovodHurt = dilovedennyaDao.findAllDilovodHurtokPerson(Long.parseLong(personid));
				} else if(Integer.parseInt(kurinorsmhurt) == 1){
					listPersChangeDilovodHurt = dilovedennyaDao.findAllDilovodSMHurtokPerson(Long.parseLong(personid));
				}
				
				String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
		        		"<AllDilovodHurtokYunak>\n";
		    	String xml02 = "";			
		    	String xml03 = "";
				for (PersonsUPUChangeDilovodHurtokPojo pcdh : listPersChangeDilovodHurt) {
					String datefrom = pcdh.getDatebegin().toString();									
					String dayf = datefrom.substring(8, 10);
					String monthf = datefrom.substring(5, 7);
					String yearf = datefrom.substring(0, 4);
					String datebegin = dayf+"."+monthf+"."+yearf;
					
					Date dateto = pcdh.getDateend();
					String dateend = null;
					
					if(dateto!= null){
						String dayt = dateto.toString().substring(8, 10);
						String montht = dateto.toString().substring(5, 7);
						String yeart = dateto.toString().substring(0, 4);
						dateend = dayt+"."+montht+"."+yeart;
					} else {
						dateend = null;
					}
					if(Integer.parseInt(kurinorsmhurt) == 0){
						xml03 = xml03+
								"<dilovodhurtok>\n" +
								"<id>"+pcdh.getId()+"</id>\n"+
								"<begindate>"+datebegin+"</begindate>\n"+
								"<enddate>"+dateend+"</enddate>\n"+
								"<hurtokid>"+pcdh.getIdhurtok()+"</hurtokid>\n"+
								"<namehurtok>"+pcdh.getNamehurtok()+"</namehurtok>\n"+
								"<personid>"+pcdh.getIdperson()+"</personid>\n"+
								"<kurinid>"+pcdh.getIdkurin()+"</kurinid>\n"+
								"<dilovedennyaid>"+pcdh.getIddilovod()+"</dilovedennyaid>\n"+
								"<dilovedennya>"+pcdh.getNamedilovod()+"</dilovedennya>\n"+					
								"</dilovodhurtok>\n";
					} else if(Integer.parseInt(kurinorsmhurt) == 1){
						xml03 = xml03+
								"<dilovodhurtok>\n" +
								"<id>"+pcdh.getId()+"</id>\n"+
								"<begindate>"+datebegin+"</begindate>\n"+
								"<enddate>"+dateend+"</enddate>\n"+
								"<hurtokid>"+pcdh.getIdsmhurtok()+"</hurtokid>\n"+
								"<namehurtok>"+pcdh.getNamehurtok()+"</namehurtok>\n"+
								"<personid>"+pcdh.getIdperson()+"</personid>\n"+
								"<dilovedennyaid>"+pcdh.getIddilovod()+"</dilovedennyaid>\n"+
								"<dilovedennya>"+pcdh.getNamedilovod()+"</dilovedennya>\n"+					
								"</dilovodhurtok>\n";
					}
					
				}
				String xml = xml01 + xml02 + xml03 + "</AllDilovodHurtokYunak>";
	//	    	System.out.println(xml);
				response.getWriter().println(xml);
				response.getWriter().close();
			}
			return modelAndView; 
	}
}
