package plast.org.ua.upu.controller.ajax;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.KVDao;
import plast.org.ua.upu.dao.KurinUSPUPSDao;
import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.dao.StupinDao;
import plast.org.ua.upu.dao.UladDao;
import plast.org.ua.upu.dao.VyshkilDao;
import plast.org.ua.upu.dao.VyshkilTabirDao;
import plast.org.ua.upu.idao.IKVDao;
import plast.org.ua.upu.idao.IKurinUSPUPSDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.idao.IStupinDao;
import plast.org.ua.upu.idao.IUladDao;
import plast.org.ua.upu.idao.IVyshkilDao;
import plast.org.ua.upu.idao.IVyshkilTabirDao;
import plast.org.ua.upu.pojo.PersonsUSPUPSPojo;
import plast.org.ua.upu.table.KV;
import plast.org.ua.upu.table.KurinUSPUPS;
import plast.org.ua.upu.table.PersonKurinUSPUPS;
import plast.org.ua.upu.table.PersonStupinUlad;
import plast.org.ua.upu.table.PersonVyshkilTabir;
import plast.org.ua.upu.table.Persons;
import plast.org.ua.upu.table.PersonsKV;
import plast.org.ua.upu.table.PersonsStanytsya;
import plast.org.ua.upu.table.PersonsVyshkil;
import plast.org.ua.upu.table.Stanytsya;
import plast.org.ua.upu.table.Stupin;
import plast.org.ua.upu.table.Ulad;
import plast.org.ua.upu.table.Vyshkil;
import plast.org.ua.upu.table.VyshkilTabir;

public class PersonsUSPController implements Controller{
	private Persons persons;
	private PersonsStanytsya personsStanytsya;
	private PersonStupinUlad personStupinUlad;
	private PersonKurinUSPUPS personKurinUSPUPS;	
	private PersonsVyshkil personsVyshkil;
	private PersonVyshkilTabir personVyshkilTabir;
	private PersonsKV personsKV;
	private Stanytsya stanytsya;
	private Ulad ulad;
	private Stupin stupin;
	private KurinUSPUPS kurinUSPUPS;
	private KV kv;
	private Vyshkil vyshkil;
	private VyshkilTabir vyshkilTabir;
	private Date datebirth, datesworn, datereg;
	private IPersonsDao personsDao = PersonsDao.getInstance();
	private IStanystyaDao stanytsyaDao = StanytsyaDao.getInstance();
	private IUladDao uladDao = UladDao.getInstance();
	private IStupinDao stupinDao = StupinDao.getInstance();
	private IKurinUSPUPSDao kurinUSPUPSDao = KurinUSPUPSDao.getInstance();
	private IKVDao kvDao = KVDao.getInstance(); 
	private IVyshkilDao vyshkilDao = VyshkilDao.getInstance();
	private IVyshkilTabirDao vyshkilTabirDao = VyshkilTabirDao.getInstance();
	private List<Stanytsya> listStanytsya;
	private List<Ulad> listUlad;
	private List<Stupin> listStupin;
	private List<KurinUSPUPS> listKurinUSPUPS;
	private List<KV> listKV;
	private List<Vyshkil> listVyshkil;
	private List<VyshkilTabir> listVyshkilTabir;
	private List<PersonsUSPUPSPojo> listPersonUspUps;
	private List<PersonsStanytsya> listPersStanytsya;
	private List<PersonStupinUlad> listPersStupUlad;
	private List<PersonKurinUSPUPS> listPersKurinUSPUPS;
	private List<PersonsKV> listPersKV;
	private List<PersonsVyshkil> listPersVyshkil;
	private List<PersonVyshkilTabir> listPersVyshkilTabir;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        
        String stridperson = request.getParameter("idperson");
        System.out.println("stridperson = "+stridperson);
        if(stridperson.equals("")){
        	stridperson = "0";
        }
        
        String lastname = request.getParameter("lastname");
        System.out.println("lastname = "+lastname);
		
        String firstname = request.getParameter("firstname");
		System.out.println("firstname = "+firstname);
		
		String datebirthstr = request.getParameter("datebirth");
		System.out.println("datebirthstr = "+datebirthstr);
		datebirth = new Date(dateFormat.parse(datebirthstr).getTime());
						
		String uladstr = request.getParameter("selectulad");
		System.out.println("uladstr = "+uladstr);
		
		String stupinstr = request.getParameter("selectstupin");
		System.out.println("stupinstr = "+stupinstr);
		
		String stanytsyastr = request.getParameter("stanytsya");
		System.out.println("stanytsyastr = "+stanytsyastr);
		
		String kurinusp = request.getParameter("selkurinusp");
		System.out.println("kurinusp = "+kurinusp);
		
		String stupinkvstr = request.getParameter("stupinkv");
		System.out.println("stupinkvstr = "+stupinkvstr);
		
		String vyshkilstr = request.getParameter("vyshkil");
		System.out.println("vyshkilstr = "+vyshkilstr);
		if(vyshkilstr == null){
			vyshkilstr = "";
		}
		
		String selvyshkiltabirstr = request.getParameter("selvyshkiltabir");
		System.out.println("selvyshkiltabirstr = "+selvyshkiltabirstr);
		
		String address = request.getParameter("address");
		System.out.println("address = "+address);
		
		String phonenumber = request.getParameter("phonenumber");
		System.out.println("phonenumber = "+phonenumber);
		
		String email = request.getParameter("email");
		System.out.println("email = "+email);
		
		String dateregistr = request.getParameter("dateregistr");
		System.out.println("dateregistr = "+dateregistr);
		datereg = new Date(dateFormat.parse(dateregistr).getTime());
		
		String dateswornstr = request.getParameter("datesworn");
		System.out.println("dateswornstr = "+dateswornstr);
		
		String jobeducation = request.getParameter("jobeducat");
		if(stridperson.equals("undefined")){stridperson = "0";}
		
		Long idperson = Long.parseLong(stridperson);
		if(idperson == 0){
			/** add new persons data*/
	        persons = new Persons();
	        persons.setLastName(lastname);
	        persons.setFirstName(firstname);
	        persons.setDatebirth(datebirth);
	        persons.setAddress(address);
	        persons.setPhoneNumber(phonenumber);
	        persons.setJobeducation(jobeducation);
	        persons.setEmail(email);
	        if(dateswornstr!=""){
	        	datesworn = new Date(dateFormat.parse(dateswornstr).getTime());
	        	persons.setDateSworn(datesworn);
	        }        
	        personsDao.addPerson(persons);
	        
	        /** add stanytsya persons data*/
	        personsStanytsya = new PersonsStanytsya();
	        listStanytsya = stanytsyaDao.findStanytsya(Long.parseLong(stanytsyastr));
	        for (Stanytsya stan : listStanytsya) {
				stanytsya = new Stanytsya(stan.getId(), stan.getNamestan(), stan.getOkruha());
			}
	        personsStanytsya.setStanytsya(stanytsya);
	        personsStanytsya.setPersons(persons);
	        personsDao.addPersonStanytsya(personsStanytsya);
	        
	        /** add new ulad and stupin data*/
	        // ÃŒ∆≈ Õ≈ —œ≤¬œ¿ƒ¿“» ƒ¿“¿ –≈™—“–¿÷≤Ø «≤ —“”œ≈Õ≈Ã
	        personStupinUlad = new PersonStupinUlad();
	        personStupinUlad.setDatefrom(datereg);
	        
	        listUlad = uladDao.findUlad(Long.parseLong(uladstr));
	        for (Ulad ulad2 : listUlad) {
				ulad = new Ulad(ulad2.getId(), ulad2.getNameulad());
			}
	        personStupinUlad.setUlad(ulad);
	        
	        listStupin = stupinDao.findStupin(Long.parseLong(stupinstr));
	        for (Stupin stp : listStupin) {
				stupin = new Stupin(stp.getId(), stp.getNameStupin(), stp.getUlad());
			}
	        personStupinUlad.setStupin(stupin);        
	        personStupinUlad.setPersons(persons);
	        personsDao.addPersStupUlad(personStupinUlad);
	        
	        
	        if(Integer.parseInt(kurinusp)!= 0){
	        	personKurinUSPUPS = new PersonKurinUSPUPS();
	        	listKurinUSPUPS = kurinUSPUPSDao.findOneKurin(Long.parseLong(kurinusp));
	        	for (KurinUSPUPS kurinuspups : listKurinUSPUPS) {
					kurinUSPUPS = new KurinUSPUPS(kurinuspups.getId(), kurinuspups.getNamekurin(), kurinuspups.getUlad());
				}
	        	personKurinUSPUPS.setKurinUSPUPS(kurinUSPUPS);
	            personKurinUSPUPS.setPersons(persons);
	            personsDao.addPersonKurinUspUps(personKurinUSPUPS);
	        }
	        
	        if(Integer.parseInt(stupinkvstr)!= 0){
	        	personsKV = new PersonsKV();
	        	listKV = kvDao.findKV(Long.parseLong(stupinkvstr));
	        	for (KV kv1 : listKV) {
					kv = new KV(kv1.getId(), kv1.getName());
				}
	        	personsKV.setKv(kv);
	        	personsKV.setPersons(persons);
	        	personsDao.addPersonKV(personsKV);
	        }
	        
	       
	        if(vyshkilstr!=""){
	        	List<String> list = new ArrayList<String>(Arrays.asList(vyshkilstr.split(",")));
	        	String count;
	        	personsVyshkil = new PersonsVyshkil();
	        	for(int i=0; i<list.size(); i++){
	    			count = list.get(i);
	    			listVyshkil = vyshkilDao.findOneVyshkil(Long.parseLong(count));
	    			for (Vyshkil v : listVyshkil) {
						vyshkil = new Vyshkil(v.getId(), v.getNamevyshkil(), v.getDescription());
					}
	    			personsVyshkil.setVyshkil(vyshkil);
	    			personsVyshkil.setPersons(persons);
	    			personsDao.addPersonVyshkil(personsVyshkil);
	    			
	    		}
	        }
	        
	        if(Integer.parseInt(selvyshkiltabirstr)!=0){
	        	personVyshkilTabir = new PersonVyshkilTabir();
	        	listVyshkilTabir = vyshkilTabirDao.findOneVyshkilTabir(Long.parseLong(selvyshkiltabirstr));
	        	for (VyshkilTabir vt : listVyshkilTabir) {
	        		vyshkilTabir = new VyshkilTabir(vt.getId(), vt.getNamevyshkiltabir());
				}
	        	personVyshkilTabir.setVyshkilTabir(vyshkilTabir);
	        	personVyshkilTabir.setPersons(persons);
	        	personsDao.addPersonVyshkilTabir(personVyshkilTabir);
	        }
	        
	        listPersonUspUps = personsDao.findPersonUSPUPS(Long.parseLong(stanytsyastr));
	        
	        String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
	        		"<AllPersonsUSPUPS>\n";
	        		String xml02 = "<personsuspups>\n"+ 
	        					   "<id>"+0+"</id>\n"+
	        					   "<stupin>"+""+"</stupin>\n"+
	        				       "<fullnamepersons>"+"¬Ë·Â≥Ú¸ Á ÒÔËÒÍÛ"+"</fullnamepersons>\n"+
	        				       "</personsuspups>\n";
	        		String xml03 = "";	
	        for (PersonsUSPUPSPojo personsUSPUPSPojo : listPersonUspUps) {
	        	xml03 = xml03 + 
						"<personsuspups>\n"+
						"<id>" + personsUSPUPSPojo.getIdperson() + "</id>\n"+
						"<stupin>"+personsUSPUPSPojo.getStupin()+"</stupin>\n"+
						"<fullnamepersons>" + personsUSPUPSPojo.getLastname() + " " + personsUSPUPSPojo.getFirstname() + "</fullnamepersons>\n"+
						"</personsuspups>\n";
			}
	        String xml = xml01 + xml02 + xml03 + "</AllPersonsUSPUPS>";
	        System.out.println(xml);
			response.getWriter().println(xml);
			response.getWriter().close();
			return modelAndView;
		} else {
			System.out.println("UPDATE");
			if(dateswornstr!=""){
	        	datesworn = new Date(dateFormat.parse(dateswornstr).getTime());	        	
	        } else {
	        	datesworn = null;
	        }
			persons = new Persons(idperson, lastname, firstname, datebirth, phonenumber, email, datesworn, address, jobeducation);
			personsDao.updatePerson(persons);
			
			 /** add update persons data*/

	        listStanytsya = stanytsyaDao.findStanytsya(Long.parseLong(stanytsyastr));
	        for (Stanytsya stan : listStanytsya) {
				stanytsya = new Stanytsya(stan.getId(), stan.getNamestan(), stan.getOkruha());
			}
	        
	        listPersStanytsya = personsDao.findPersonStanytsya(idperson);
	        
	        if(listPersStanytsya.size() == 0){
	        	personsStanytsya = new PersonsStanytsya();
	        	personsStanytsya.setStanytsya(stanytsya);
		        personsStanytsya.setPersons(persons);
		        personsDao.addPersonStanytsya(personsStanytsya);
	        } else {
	        	for (PersonsStanytsya ps : listPersStanytsya) {
		        	personsStanytsya = new PersonsStanytsya(ps.getId(), null, null, persons, stanytsya);
				}
		        personsDao.updatePersonStanytsya(personsStanytsya);
	        }
	        
	        /** update new ulad and stupin data*/
	        // ÃŒ∆≈ Õ≈ —œ≤¬œ¿ƒ¿“» ƒ¿“¿ –≈™—“–¿÷≤Ø «≤ —“”œ≈Õ≈Ã
	       
	        
	        listUlad = uladDao.findUlad(Long.parseLong(uladstr));
	        for (Ulad ulad2 : listUlad) {
				ulad = new Ulad(ulad2.getId(), ulad2.getNameulad());
			}
	       
	        
	        listStupin = stupinDao.findStupin(Long.parseLong(stupinstr));
	        for (Stupin stp : listStupin) {
				stupin = new Stupin(stp.getId(), stp.getNameStupin(), stp.getUlad());
			}
	        
	        listPersStupUlad = personsDao.findPersStupUlad(idperson);
	        for (PersonStupinUlad psu : listPersStupUlad) {
	        	personStupinUlad = new PersonStupinUlad(psu.getId(), datereg, null, persons, stupin, ulad);
			}
	        
	        personsDao.updatePersStupUlad(personStupinUlad);
	        	        
	        
	        if(Integer.parseInt(kurinusp)!= 0){
	        		        	
	        	listKurinUSPUPS = kurinUSPUPSDao.findOneKurin(Long.parseLong(kurinusp));
	        	for (KurinUSPUPS kurinuspups : listKurinUSPUPS) {
					kurinUSPUPS = new KurinUSPUPS(kurinuspups.getId(), kurinuspups.getNamekurin(), kurinuspups.getUlad());
				}
	        	
	        	listPersKurinUSPUPS = personsDao.findPersKurinUSPUPS(idperson);
	        	if(listPersKurinUSPUPS.size() == 0){
	        		personKurinUSPUPS = new PersonKurinUSPUPS();		        	
		        	personKurinUSPUPS.setKurinUSPUPS(kurinUSPUPS);
		            personKurinUSPUPS.setPersons(persons);
		            personsDao.addPersonKurinUspUps(personKurinUSPUPS);
	        	} else {
	        		for (PersonKurinUSPUPS pk : listPersKurinUSPUPS) {
		        		personKurinUSPUPS = new PersonKurinUSPUPS(pk.getId(), null, null, persons, kurinUSPUPS);
					}	        	
		            personsDao.updatePersonKurinUSPUPS(personKurinUSPUPS);
	        	}
	        } else {
	        	listPersKurinUSPUPS = personsDao.findPersKurinUSPUPS(idperson);
	        	if(listPersKurinUSPUPS.size() == 0){
	        		personKurinUSPUPS = new PersonKurinUSPUPS();		        	
		        	personKurinUSPUPS.setKurinUSPUPS(kurinUSPUPS);
		            personKurinUSPUPS.setPersons(persons);
		            personsDao.addPersonKurinUspUps(personKurinUSPUPS);
	        	} else {
	        		for (PersonKurinUSPUPS pk : listPersKurinUSPUPS) {
		        		personKurinUSPUPS = new PersonKurinUSPUPS(pk.getId(), null, null, persons, null);
					}	        	
		            personsDao.updatePersonKurinUSPUPS(personKurinUSPUPS);
	        	}
	        }
	        
	        if(Integer.parseInt(stupinkvstr)!= 0){
	        	
	        	listKV = kvDao.findKV(Long.parseLong(stupinkvstr));
	        	for (KV kv1 : listKV) {
					kv = new KV(kv1.getId(), kv1.getName());
				}
	        	
	        	listPersKV = personsDao.findPersKV(idperson);
	        	if(listPersKV.size() == 0){
	        		personsKV = new PersonsKV();
		        	personsKV.setKv(kv);
		        	personsKV.setPersons(persons);
		        	personsDao.addPersonKV(personsKV);
	        	} else {
		        	for (PersonsKV pkv : listPersKV) {
		        		personsKV = new PersonsKV(pkv.getId(), null, null, persons, kv);
		        		personsDao.updatePersonKV(personsKV);
					}	        		
	        	}	        	
	        } else {
	        	listPersKV = personsDao.findPersKV(idperson);
	        	if(listPersKV.size() == 0){
	        		personsKV = new PersonsKV();
		        	personsKV.setKv(kv);
		        	personsKV.setPersons(persons);
		        	personsDao.addPersonKV(personsKV);
	        	} else {
	        		kv = new KV();
		        	for (PersonsKV pkv : listPersKV) {
		        		personsKV = new PersonsKV(pkv.getId(), null, null, persons, null);
		        		personsDao.updatePersonKV(personsKV);
					}	        		
	        	}	        	
	        }
	        
	        
	       // if(!vyshkilstr.equals("")){
	        if(vyshkilstr!= null){
	        	listPersVyshkil = personsDao.findPersVyshkil(idperson);
	        	
	        	if(listPersVyshkil.size() != 0) {
	    			for (PersonsVyshkil pv : listPersVyshkil) {	    				
	    				personsVyshkil = new PersonsVyshkil(pv.getId());
	    				personsDao.deletePersonVyshkil(personsVyshkil);   				
					}
	        	}
	        	
	        	List<String> list = new ArrayList<String>(Arrays.asList(vyshkilstr.split(",")));
	        	String count;
	        	personsVyshkil = new PersonsVyshkil();
	        	for(int i=0; i<list.size(); i++){
	    			count = list.get(i);
	    			if(count==""){
	    				count = "0";
	    			}
	    			listVyshkil = vyshkilDao.findOneVyshkil(Long.parseLong(count));
	    			for (Vyshkil v : listVyshkil) {
						vyshkil = new Vyshkil(v.getId(), v.getNamevyshkil(), v.getDescription());
					}
	    			personsVyshkil.setVyshkil(vyshkil);
	    			personsVyshkil.setPersons(persons);
	    			personsDao.addPersonVyshkil(personsVyshkil);
	    			
	    		}
	        } 
	        
	        if(Integer.parseInt(selvyshkiltabirstr)!=0){
	        	
	        	listVyshkilTabir = vyshkilTabirDao.findOneVyshkilTabir(Long.parseLong(selvyshkiltabirstr));
	        	for (VyshkilTabir vt : listVyshkilTabir) {
	        		vyshkilTabir = new VyshkilTabir(vt.getId(), vt.getNamevyshkiltabir());
				}
	        	listPersVyshkilTabir = personsDao.findPersVyshkilTabir(idperson);
	        	for (PersonVyshkilTabir pvt : listPersVyshkilTabir) {
	        		personVyshkilTabir = new PersonVyshkilTabir(pvt.getId(), null, null, persons, vyshkilTabir);	  
				}
	        	      	
	        	personsDao.updatePersonVyshkilTabir(personVyshkilTabir);
	        } else {
	        	listPersVyshkilTabir = personsDao.findPersVyshkilTabir(idperson);
	        	if(listPersVyshkilTabir.size()!=0){
	        		for (PersonVyshkilTabir pvt : listPersVyshkilTabir) {
		        		personVyshkilTabir = new PersonVyshkilTabir(pvt.getId(), null, null, persons, null);
		        		personsDao.updatePersonVyshkilTabir(personVyshkilTabir);
					}
	        	} 
	        	
	        }
	        
	        listPersonUspUps = personsDao.findPersonUSPUPS(Long.parseLong(stanytsyastr));
	        
	        String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
	        		"<AllPersonsUSPUPS>\n";
	        		String xml02 = "<personsuspups>\n"+ 
	        					   "<id>"+0+"</id>\n"+
	        					   "<stupin>"+""+"</stupin>\n"+
	        				       "<fullnamepersons>"+"¬Ë·Â≥Ú¸ Á ÒÔËÒÍÛ"+"</fullnamepersons>\n"+
	        				       "</personsuspups>\n";
	        		String xml03 = "";	
	        for (PersonsUSPUPSPojo personsUSPUPSPojo : listPersonUspUps) {
	        	xml03 = xml03 + 
						"<personsuspups>\n"+
						"<id>" + personsUSPUPSPojo.getIdperson() + "</id>\n"+
						"<stupin>"+personsUSPUPSPojo.getStupin()+"</stupin>\n"+
						"<fullnamepersons>" + personsUSPUPSPojo.getLastname() + " " + personsUSPUPSPojo.getFirstname() + "</fullnamepersons>\n"+
						"</personsuspups>\n";
			}
	        String xml = xml01 + xml02 + xml03 + "</AllPersonsUSPUPS>";
			response.getWriter().println(xml);
			response.getWriter().close();
	        
			return modelAndView;
		}
		
	}
}
