package plast.org.ua.upu.controller.ajax;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.dao.SamHurtokUPUDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.md.MD5;
import plast.org.ua.upu.sendemail.SendEmail;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.SamHurtokUPU;
import plast.org.ua.upu.table.Stanytsya;



public class KurinUPUController implements Controller{
	private KurinUPU kurinUPU;
	private Stanytsya stanytsya;
	private Date dateReg;
	private java.sql.Date dateRegist;
	private IStanystyaDao stanytsyaDao = StanytsyaDao.getInstance();
	private IKurinUPUDao kurinUPUDao = KurinUPUDao.getInstance();
	private List<Stanytsya> listStanytsya;
	private List<KurinUPU> listKurinUPU;
	private ISamHurtokUPUDao samHurtokUPUDao = SamHurtokUPUDao.getInstance();	
	private List<SamHurtokUPU> listSamHurtok;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		String idselstan = request.getParameter("idselstan");
		System.out.println("idselstan = "+idselstan);
		Integer kurinORsamhurtok = Integer.parseInt(request.getParameter("kurinsh"));
		System.out.println("kurinORsamhurtok = "+kurinORsamhurtok);
		if(idselstan!=null){
/* USE when select Stanytsya*/
			
				if(kurinORsamhurtok == 0){
					listKurinUPU = kurinUPUDao.findStanytsyaKurinUPU(Long.parseLong(idselstan));
					
					String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
			        		"<AllKurinUPU>\n";
			        		String xml02 = "<kurinupu>\n"+ 
			        					   "<id>"+0+"</id>\n"+
			        				       "<namekurinupu>"+"Виберіть курінь"+"</namekurinupu>\n"+
			        				       "</kurinupu>\n";
			        		String xml03 = "";
					for (KurinUPU kurinUPU : listKurinUPU) {
						xml03 = xml03 + 
								"<kurinupu>\n"+
								"<id>" + kurinUPU.getId() + "</id>\n"+
								"<namekurinupu>" + kurinUPU.getNumberKurin() + " "+ kurinUPU.getNameKurin() + "</namekurinupu>\n"+
								"</kurinupu>\n";
					}
					String xml = xml01 + xml02 + xml03 + "</AllKurinUPU>";
					//System.out.println(xml);
					
					JSONObject obj = new JSONObject();
					JSONArray jsonArray = new JSONArray();
					String jsonText = null;
					StringWriter out = new StringWriter();
					
					for (KurinUPU kurinUPU : listKurinUPU) {
						obj.put("number", kurinUPU.getNumberKurin());					
						obj.put("namekurin", kurinUPU.getNameKurin());
						obj.put("key",  kurinUPU.getId());
						jsonArray.add(obj.toJSONString());
					      obj.writeJSONString(out);
					      
					      jsonText = out.toString();
					      
						
					}
					
					System.out.println("jsonText OUT = "+jsonArray.toJSONString().replace("\\\"", "\"").replace("[\"", "[").replace("}\"", "}").replace("\"{", "{"));
					
					String JSON = jsonArray.toJSONString().replace("\\\"", "\"").replace("[\"", "[").replace("}\"", "}").replace("\"{", "{");
					response.getWriter().println(JSON);
					response.getWriter().close();
	
				} else if(kurinORsamhurtok == 1){
					listSamHurtok = samHurtokUPUDao.findAllSamHurtok(Long.parseLong(idselstan));
					// [{"Name": "key1", "Value": "value1"}, {"Name": "key2", "Value": "value2"}]
					String JSON01 = "[";
					String JSON02 = "";
					String JSON03 = "]";		
					int i = 0;
					JSONObject obj = new JSONObject();
					JSONArray jsonArray = new JSONArray();
					
					
					String jsonText = null;
					StringWriter out = new StringWriter();
					
					for (SamHurtokUPU sh : listSamHurtok) {
						JSON02 = JSON02 + "{\"key\":"+sh.getId()+ ", \"namesamhurtok\":"+"\""+sh.getNameSamHurtok()+"\""+"}";
						obj.put("key", sh.getId());
						obj.put("namesamhurtok", sh.getNameSamHurtok());
						jsonArray.add(obj.toJSONString());
					      obj.writeJSONString(out);
					      
					      jsonText = out.toString();
					      
						System.out.println("jsonText IN = "+jsonText);
					}
					//String JSON = JSON01 + JSON02 + JSON03;
					String JSON = jsonArray.toJSONString().replace("\\\"", "\"").replace("[\"", "[").replace("}\"", "}").replace("\"{", "{");
					System.out.println("JSON = \t"+JSON);		
					 System.out.println("jsonText OUT = "+jsonArray.toJSONString().replace("\\\"", "\"").replace("[\"", "[").replace("}\"", "}").replace("\"{", "{"));
			        response.getWriter().println(JSON);
			        response.getWriter().close();
					return modelAndView;						
				}
			
		} else {
			/* USE when add new Kurin UPU*/
			Long stanytsyaID = Long.parseLong(request.getParameter("stan"));
			
			
			if(kurinORsamhurtok == 0){
				String numberKurinStr = request.getParameter("numbk");
				Integer numberKurin = Integer.parseInt(numberKurinStr);
//				if(Integer.parseInt(numberKurin) == 0){
//					numberKurin = "підг";
//				}
				System.out.println("numberKurin = "+numberKurin);
				
				Integer sexKurin = Integer.parseInt(request.getParameter("sexk"));
				System.out.println("sexKurin = "+sexKurin);
				
				String kurinName = request.getParameter("kurinname");
				System.out.println("kurinName = "+kurinName);
				
				String dateRegister = request.getParameter("dateregister");
				System.out.println("dateRegister = "+dateRegister);
				
				String emailKurin = request.getParameter("emailk");
				System.out.println("emailKurin = "+emailKurin);
				
				listStanytsya = stanytsyaDao.findStanytsya(stanytsyaID);
				for (Stanytsya stanytsya2 : listStanytsya) {
					stanytsya = new Stanytsya(stanytsya2.getId(), stanytsya2.getNamestan(), stanytsya2.getOkruha());
				}
				
				DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
				dateReg = dateFormat.parse(dateRegister);
				dateRegist = new java.sql.Date(dateReg.getTime());
				String hashid = MD5.getHash(numberKurin+kurinName);
				
				kurinUPU = new KurinUPU();
				kurinUPU.setDateRegister(dateRegist);
				kurinUPU.setEmailKurin(emailKurin);
				kurinUPU.setHashid(hashid);
				kurinUPU.setNameKurin(kurinName);
				kurinUPU.setNumberKurin(numberKurin);
				kurinUPU.setSexKurin(sexKurin);
				kurinUPU.setStanytsya(stanytsya);
				kurinUPUDao.addKurinUPU(kurinUPU);
				
				String first = "<h2>СКОБ!</h2>";
				String second = null;
				String numbKurStr = null;
				if(numberKurin == 0){
					numbKurStr = "підг. ";
				} else {
					numbKurStr = numberKurin.toString();
				}
				if(sexKurin == 0){
					
					second = "<p>Ти зареєстрував курінь "+numbKurStr +" ім. "+kurinName+ " станиці "+stanytsya.getNamestan()+"</p>";
				} else if(sexKurin == 1) {
					second = "<p>Ти зареєструвала курінь "+numbKurStr +" ім. "+kurinName+ " станиці "+stanytsya.getNamestan()+"</p>";
				} else if(sexKurin == 2){
					second = "<p>Ти зареєстрував/ла курінь "+numbKurStr +" ім. "+kurinName+ " станиці "+stanytsya.getNamestan()+"</p>";
				}
				
				String third = "<p>Для продовження реєстрації членів куреня та гуртків потрібно використовувати кодове слово: <b>"+hashid+"</b></p>";
				
				String forth = "<p><b>ПРОХАННЯ</b> не передавайте свого коду стороннім особам. Дбай про безпеку даних</p>";
				
				String text = first + second +third+forth;
				SendEmail sendEmail = new SendEmail("techniclsuport@gmail.com", "@ctiv@tion");
				sendEmail.send("Реєстр куреня", text, "vasylshvv@gmail.com", emailKurin);
				
				listKurinUPU = kurinUPUDao.findStanytsyaKurinUPU(stanytsyaID);
				String xml01 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+
		        		"<AllKurinUPU>\n";
		        		String xml02 = "<kurinupu>\n"+ 
		        					   "<id>"+0+"</id>\n"+
		        				       "<namekurinupu>"+"Виберіть курінь"+"</namekurinupu>\n"+
		        				       "</kurinupu>\n";
		        		String xml03 = "";
				for (KurinUPU kurinUPU : listKurinUPU) {
					xml03 = xml03 + 
							"<kurinupu>\n"+
							"<id>" + kurinUPU.getId() + "</id>\n"+
							"<namekurinupu>" + kurinUPU.getNumberKurin() + " "+ kurinUPU.getNameKurin() + "</namekurinupu>\n"+
							"</kurinupu>\n";
				}
				String xml = xml01 + xml02 + xml03 + "</AllKurinUPU>";
				
				
				JSONObject obj = new JSONObject();
				JSONArray jsonArray = new JSONArray();
				String jsonText = null;
				StringWriter out = new StringWriter();
				
				for (KurinUPU kurinUPU : listKurinUPU) {
					obj.put("number", kurinUPU.getNumberKurin());					
					obj.put("namekurin", kurinUPU.getNameKurin());
					obj.put("key",  kurinUPU.getId());
					jsonArray.add(obj.toJSONString());
				      obj.writeJSONString(out);
				      
				      jsonText = out.toString();
				      
				      
				}
				String JSON = jsonArray.toJSONString().replace("\\\"", "\"").replace("[\"", "[").replace("}\"", "}").replace("\"{", "{");
				System.out.println("jsonText OUT = "+jsonArray.toJSONString().replace("\\\"", "\"").replace("[\"", "[").replace("}\"", "}").replace("\"{", "{"));
				
				
				
				
				response.getWriter().println(JSON);
				response.getWriter().close();
				
			} else if(kurinORsamhurtok == 1){
				
			}
		}
		return modelAndView;
	}
}
