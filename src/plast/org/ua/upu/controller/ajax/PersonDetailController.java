package plast.org.ua.upu.controller.ajax;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.pojo.PersonDeatailPojo;
import plast.org.ua.upu.pojo.PersonKVPojo;
import plast.org.ua.upu.pojo.PersonKurinUSPPojo;
import plast.org.ua.upu.pojo.PersonStanytsyaPojo;
import plast.org.ua.upu.pojo.PersonUladStupinPojo;
import plast.org.ua.upu.pojo.PersonVyshkilPojo;
import plast.org.ua.upu.pojo.PersonVyshkilTabirPojo;
import plast.org.ua.upu.table.KurinUSPUPS;
import plast.org.ua.upu.table.Persons;

public class PersonDetailController implements Controller{
	private IPersonsDao persondao = PersonsDao.getInstance();
	private List<Persons> listPersons;
	private List<PersonStanytsyaPojo> liststanpers;
	private List<PersonUladStupinPojo> listUladStupin;
	private List<PersonKVPojo> listPersonKV;
	private List<PersonKurinUSPPojo> listKurinUSP;
	private List<PersonVyshkilTabirPojo> listVyshkilTab;
	private List<PersonDeatailPojo> listDetailPerson;
	private List<PersonVyshkilPojo> listVyshkilPerson;
	private PersonDeatailPojo persDetail;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	ModelAndView modelAndView = null;
		
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String strperson = request.getParameter("personid");
        System.out.println(getClass()+"\t"+"person id = "+strperson);
        
              
		persDetail = new PersonDeatailPojo();
		// ADD VYSHKIL KVV UPU and other

		listPersons = persondao.findOnePersons(Long.parseLong(strperson));
        for (Persons pers : listPersons) {
        	persDetail.setIdperson(pers.getId());
        	persDetail.setFirstname(pers.getFirstName());
        	persDetail.setLastname(pers.getLastName());
        	persDetail.setBirthday(pers.getDatebirth());
        	persDetail.setAdress(pers.getAddress());
    		persDetail.setJobeducation(pers.getJobeducation());    		
    		persDetail.setMobilephone(pers.getPhoneNumber());
    		persDetail.setEmail(pers.getEmail());
    		persDetail.setDatesworn(pers.getDateSworn());
		}
        

		liststanpers = persondao.findOnePersonStanytsya(Long.parseLong(strperson));
		for (PersonStanytsyaPojo personStanytsya : liststanpers) {
			persDetail.setStanytsyaid(personStanytsya.getStanid());
			persDetail.setStanytsya(personStanytsya.getStanname());			
		}
		
		listUladStupin = persondao.findOnePersonUladStupin(Long.parseLong(strperson));
		for (PersonUladStupinPojo personUladStupin : listUladStupin) {
			
			persDetail.setStupinid(personUladStupin.getStupinid());
			persDetail.setStupinname(personUladStupin.getNamestupin());
			persDetail.setUladid(personUladStupin.getUladid());
			persDetail.setUladname(personUladStupin.getNameulad());
			persDetail.setDatestartplast(personUladStupin.getDatestart());
			
		}
		
		listPersonKV = persondao.findOnePersonKV(Long.parseLong(strperson));
		for (PersonKVPojo perskv : listPersonKV) {
			persDetail.setKvid(perskv.getIdkv());
			persDetail.setKvname(perskv.getNamekv());			
		}
		
		listKurinUSP = persondao.findOnePersonKurinUSP(Long.parseLong(strperson));
		for (PersonKurinUSPPojo perskurusp : listKurinUSP) {
			persDetail.setKurinusp(perskurusp.getNamekurinusp());
			persDetail.setKurinuspid(perskurusp.getIdkurinusp());
		}
		
		listVyshkilTab = persondao.findOnePersonVyshkilTabir(Long.parseLong(strperson));
		for (PersonVyshkilTabirPojo personVyshkilTabir : listVyshkilTab) {
			persDetail.setVyshkiltabir(personVyshkilTabir.getVyshkiltabirname());
			persDetail.setVyshkiltabirid(personVyshkilTabir.getVyshkiltabirid());
		}
		
		listDetailPerson = new ArrayList<PersonDeatailPojo>();
		listDetailPerson.add(persDetail);
		
		
		
		String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
        		"<AllDetailPerson>\n";
		String xml02 = "";
		for (PersonDeatailPojo personDeatailPojo : listDetailPerson) {
			
			String db = personDeatailPojo.getBirthday().toString();			
			String day = db.substring(8, 10);
			String month = db.substring(5, 7);
			String year = db.substring(0, 4);
			String datebirth = day+"."+month+"."+year;
			
			String sworndate = "";
			if(personDeatailPojo.getDatesworn()!=null){
				String dtsw = personDeatailPojo.getDatesworn().toString();
				String daysw = dtsw.substring(8, 10);
				String monthsw = dtsw.substring(5, 7);
				String yearsw = dtsw.substring(0, 4);
				sworndate = daysw+"."+monthsw+"."+yearsw;
			}
			
			String datestart = "";
			if(personDeatailPojo.getDatestartplast()!=null){
				String dtstr = personDeatailPojo.getDatestartplast().toString();
				String daystr = dtstr.substring(8, 10);
				String monthstr = dtstr.substring(5, 7);
				String yearstr = dtstr.substring(0, 4);
				datestart = daystr+"."+monthstr+"."+yearstr;
			}
			
			xml02 = xml02 +"<person>\n"
					+ "<id>"+personDeatailPojo.getIdperson()+"</id>\n"
					+ "<lastname>"+personDeatailPojo.getLastname()+"</lastname>\n"
					+ "<firstname>"+personDeatailPojo.getFirstname()+"</firstname>\n"
					+ "<datebirth>"+datebirth+"</datebirth>\n"
					+ "<uladid>"+personDeatailPojo.getUladid()+"</uladid>\n"
					+ "<ulad>"+personDeatailPojo.getUladname()+"</ulad>\n"
					+ "<stupinid>"+personDeatailPojo.getStupinid()+"</stupinid>\n"
					+ "<stupin>"+personDeatailPojo.getStupinname()+"</stupin>\n"
					+ "<stanytsyaid>"+personDeatailPojo.getStanytsyaid()+"</stanytsyaid>\n"
					+ "<stanytsya>"+personDeatailPojo.getStanytsya()+"</stanytsya>\n"
					+ "<kurinuspid>"+personDeatailPojo.getKurinuspid()+"</kurinuspid>\n"
					+ "<kurinusp>"+personDeatailPojo.getKurinusp()+"</kurinusp>\n"
					+ "<kvid>"+personDeatailPojo.getKvid()+"</kvid>\n"
					+ "<kv>"+personDeatailPojo.getKvname()+"</kv>\n"
					+ "<vyshkiltabirid>"+personDeatailPojo.getVyshkiltabirid()+"</vyshkiltabirid>\n"
					+ "<vyshkiltabir>"+personDeatailPojo.getVyshkiltabir()+"</vyshkiltabir>\n"
					+ "<adress>"+personDeatailPojo.getAdress()+"</adress>\n"
					+ "<jobeduc>"+personDeatailPojo.getJobeducation()+"</jobeduc>\n"
					+ "<phone>"+personDeatailPojo.getMobilephone()+"</phone>\n"
					+ "<email>"+personDeatailPojo.getEmail()+"</email>\n"
					+ "<datestart>"+datestart+"</datestart>\n"
					+ "<datesworn>"+sworndate+"</datesworn>\n"
					+ "</person>\n";
		}
		listVyshkilPerson = persondao.findOnrPersonVyshkil(Long.parseLong(strperson));
		String xml03="";
		for (PersonVyshkilPojo personVyshkilPojo : listVyshkilPerson) {
			xml03 = xml03 +"<personvyshkil>\n"
					+ "<idvyshil>"+personVyshkilPojo.getVyshkilid()+"</idvyshil>\n"
					+ "<vyshkil>"+personVyshkilPojo.getVyshkilname()+"</vyshkil>\n"
					+ "<vyshkildesc>"+personVyshkilPojo.getVyshkildescription()+"</vyshkildesc>\n"					
					+ "</personvyshkil>\n";
		}
       
        String xml = xml01 + xml02 + xml03+ "</AllDetailPerson>";
        System.out.println(xml);
		response.getWriter().println(xml);
		response.getWriter().close();
		
		return modelAndView;
	}
}
