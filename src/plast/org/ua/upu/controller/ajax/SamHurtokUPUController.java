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

import plast.org.ua.upu.dao.SamHurtokUPUDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.md.MD5;
import plast.org.ua.upu.sendemail.SendEmail;
import plast.org.ua.upu.table.SamHurtokUPU;
import plast.org.ua.upu.table.Stanytsya;

public class SamHurtokUPUController implements Controller{
	private SamHurtokUPU samHurtokUPU;
	private Date dateFrom;
	private Stanytsya stanytsya;
	private IStanystyaDao stanytsyaDao = StanytsyaDao.getInstance();
	private ISamHurtokUPUDao samHurtokUPUDao = SamHurtokUPUDao.getInstance();
	private List<Stanytsya> listStanytsya;
	private List<SamHurtokUPU> listSamHurtok;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        
        String stridstan = request.getParameter("idstan");
        Long idstan = Long.parseLong(stridstan);
        System.out.println("idstan = "+idstan);
        
        String nameSamHhurtok = request.getParameter("namesamhurtok");
        System.out.println("namesamhurtok = "+nameSamHhurtok);
        
//        String numbSamHurtok = request.getParameter("numbsamhurtok");
//        System.out.println("numbSamHurtok = "+numbSamHurtok);
        
        String sexSamHurtok = request.getParameter("sexsamhurtok");
        Integer sexSamHurtokInt = Integer.parseInt(sexSamHurtok);
        System.out.println("sexSamHurtok = "+sexSamHurtok);
        
        String emailSamHurtok = request.getParameter("emailsamhurtok");
        System.out.println("emailSamHurtok = "+emailSamHurtok);
        
        String vidznaka;
        
        String dateRegister = request.getParameter("dateregister");
        System.out.println("dateRegister = "+dateRegister);
        dateFrom = dateFormat.parse(dateRegister);
        String hashidhurtok = MD5.getHash(nameSamHhurtok+dateRegister);
        
        listStanytsya = stanytsyaDao.findStanytsya(idstan);
        for (Stanytsya stan : listStanytsya) {
			stanytsya = new Stanytsya(stan.getId(), stan.getNamestan(), stan.getOkruha());
		}
        
        samHurtokUPU = new SamHurtokUPU();
        samHurtokUPU.setDateFrom(dateFrom);        
        samHurtokUPU.setEmailSamHurtok(emailSamHurtok);
        samHurtokUPU.setHashidhurtok(hashidhurtok);        
        samHurtokUPU.setNameSamHurtok(nameSamHhurtok);
//        samHurtokUPU.setNumbSamHurtok(Integer.parseInt(numbSamHurtok));
        samHurtokUPU.setSexSamHurtok(Integer.parseInt(sexSamHurtok));
        samHurtokUPU.setStanytsya(stanytsya);
        samHurtokUPUDao.addsamhurtok(samHurtokUPU);
        
        
        String first = "<h2>СКОБ!</h2>";
		String second = null;
		if(sexSamHurtokInt == 0){
			second = "<p>Ти зареєстрував самостійний гурток "+ nameSamHhurtok +"  станиці "+stanytsya.getNamestan()+"</p>";
		} else if(sexSamHurtokInt == 1) {
			second = "<p>Ти зареєстрував самостійний гурток "+ nameSamHhurtok + " станиці "+stanytsya.getNamestan()+"</p>";
		} else if(sexSamHurtokInt == 2){
			second = "<p>Ти зареєстрував/ла самостійний гурток "+ nameSamHhurtok + " станиці "+stanytsya.getNamestan()+"</p>";
		}
		
		String third = "<p>Для продовження реєстрації членів гуртка потрібно використовувати кодове слово: <b>"+hashidhurtok+"</b></p>";
		
		String forth = "<p><b>ПРОХАННЯ</b> не передавайте свого коду стороннім особам. Дбай про безпеку даних</p>";
		
		String text = first + second +third+forth;
        
        

		SendEmail sendEmail = new SendEmail("techniclsuport@gmail.com", "@ctiv@tion");
		sendEmail.send("Реєстр самостійного гуртка", text, "vasylshvv@gmail.com", emailSamHurtok);
        
		listSamHurtok = samHurtokUPUDao.findAllSamHurtok(idstan);
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
		String JSON = JSON01 + JSON02 + JSON03;		
		System.out.println("JSON = \t"+JSON);		
		 System.out.println("jsonText OUT = "+jsonArray.toJSONString().replace("\\\"", "\"").replace("[\"", "[").replace("}\"", "}").replace("\"{", "{"));
        response.getWriter().println(jsonArray.toJSONString().replace("\\\"", "\"").replace("[\"", "[").replace("}\"", "}").replace("\"{", "{"));
        response.getWriter().close();
		return modelAndView;
	}
}
