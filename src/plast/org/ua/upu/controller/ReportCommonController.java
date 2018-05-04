package plast.org.ua.upu.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.dao.OkruhaDao;
import plast.org.ua.upu.dao.QuarterlyReportsUPUDao;
import plast.org.ua.upu.dao.SamHurtokUPUDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.dao.StupinDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.IOkruhaDao;
import plast.org.ua.upu.idao.IQuarterlyReportsUPUDao;
import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.idao.IStupinDao;
import plast.org.ua.upu.pojo.PersonsCountStupinPojo;
import plast.org.ua.upu.pojo.ReportCommonPojo;
import plast.org.ua.upu.pojo.ReportCommonSmHurtokPojo;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.Okruha;
import plast.org.ua.upu.table.QuarterlyReportsUPU;
import plast.org.ua.upu.table.SamHurtokUPU;
import plast.org.ua.upu.table.Stanytsya;

public class ReportCommonController implements Controller{
	private List<Okruha> listOkruha;
	private List<Stanytsya> listStanytsya;
	private List<KurinUPU> listKurinUPU;
	private List<SamHurtokUPU> listSmHurtokUPU;
	private List<ReportCommonPojo> listReportCommon;
	private List<ReportCommonSmHurtokPojo> listReportSmHurtok;
	private List<PersonsCountStupinPojo> listCount;
	private List<QuarterlyReportsUPU> listReport;
	private IOkruhaDao okruhaDao = OkruhaDao.getInstance();
	private IStanystyaDao stanytsyaDao = StanytsyaDao.getInstance();
	private IKurinUPUDao kurinUPUDao = KurinUPUDao.getInstance();
	private ISamHurtokUPUDao samHurtokUPUDao = SamHurtokUPUDao.getInstance();
	private IStupinDao stupinDao = StupinDao.getInstance();
	private IQuarterlyReportsUPUDao quarterlyReportsUPUDao = QuarterlyReportsUPUDao.getInstance();
	private ReportCommonPojo reportCommon;
	private ReportCommonSmHurtokPojo reportCommonSmHurtok;
	private DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	private Calendar cal = Calendar.getInstance();
	private Date today = Calendar.getInstance().getTime();
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        ModelAndView modelAndView = new ModelAndView("/jsp/commonreport.jsp");
		String submit = request.getParameter("submitreport");
		if(submit!=null){
			String ondate = request.getParameter("ondatereport");
			System.out.println("ondate = "+ondate);
			
			
			listOkruha = new ArrayList<Okruha>();
			listStanytsya = new ArrayList<Stanytsya>();
			listKurinUPU = new ArrayList<KurinUPU>();
			listSmHurtokUPU = new ArrayList<SamHurtokUPU>();
			
			
				listOkruha = okruhaDao.findAllOkruha();
				listStanytsya = stanytsyaDao.findAllStanytsya();
				listKurinUPU = kurinUPUDao.findAllKurinUPU();
				listSmHurtokUPU = samHurtokUPUDao.findAllSamHurtok();
			
			listReportCommon = new ArrayList<ReportCommonPojo>();
			
			for (KurinUPU kurinUPU : listKurinUPU) {
				int prykh, uch, rozv, skbvirl, htskbvil, allcount;
			///	System.out.println(kurinUPU.getId()+"\t"+kurinUPU.getNumberKurin());
				listReport = quarterlyReportsUPUDao.findQuartReportKurin(kurinUPU.getId(), toDate());
				Integer approved = 0;
				for (QuarterlyReportsUPU rep : listReport) {
					approved = rep.getApproved();
				}
				reportCommon = new ReportCommonPojo();
				reportCommon.setIdkurin(kurinUPU.getId());
				reportCommon.setIdstan(kurinUPU.getStanytsya().getId());
				reportCommon.setNumberkurin(kurinUPU.getNumberKurin());
				reportCommon.setNamekurin(kurinUPU.getNameKurin());
				reportCommon.setApproved(approved);
				
				listCount = stupinDao.findCountStupin(kurinUPU.getId(), "пл.прих.", ondate);
				prykh = listCount.size();
				reportCommon.setPrykhylnyk(prykh);
				
				listCount = stupinDao.findCountStupin(kurinUPU.getId(), "пл.уч.", ondate);
				uch = listCount.size();
				reportCommon.setUchansnyk(uch);
				
				listCount = stupinDao.findCountStupin(kurinUPU.getId(), "пл.розв.", ondate);
				rozv = listCount.size();
				reportCommon.setRozviduvach(rozv);
				
				if(kurinUPU.getSexKurin() == 0){
					listCount = stupinDao.findCountStupin(kurinUPU.getId(), "пл.скоб.", ondate);
					skbvirl = listCount.size();
					reportCommon.setSkobvirlytsya(skbvirl);
					
					listCount = stupinDao.findCountStupin(kurinUPU.getId(), "пл.гетьм.скоб.", ondate);
					htskbvil = listCount.size();
					reportCommon.setHetmanskobvirlytsa(htskbvil);
					
				} else if(kurinUPU.getSexKurin() == 1){
					listCount = stupinDao.findCountStupin(kurinUPU.getId(), "пл.вірл.", ondate);
					skbvirl = listCount.size();
					reportCommon.setSkobvirlytsya(skbvirl);
					
					listCount = stupinDao.findCountStupin(kurinUPU.getId(), "пл.гетьм.вірл.", ondate);
					htskbvil = listCount.size();
					reportCommon.setHetmanskobvirlytsa(htskbvil);
				} else {
					int boy, girl;
					listCount = stupinDao.findCountStupin(kurinUPU.getId(), "пл.скоб.", ondate);
					boy = listCount.size();
					listCount = stupinDao.findCountStupin(kurinUPU.getId(), "пл.вірл.", ondate);
					girl = listCount.size();
					skbvirl = boy+girl;
					reportCommon.setSkobvirlytsya(skbvirl);
					
					listCount = stupinDao.findCountStupin(kurinUPU.getId(), "пл.гетьм.скоб.", ondate);
					boy = listCount.size();
					listCount = stupinDao.findCountStupin(kurinUPU.getId(), "пл.гетьм.вірл.", ondate);
					girl = listCount.size();
					htskbvil = boy+girl;
					reportCommon.setHetmanskobvirlytsa(htskbvil);
					
				}
				allcount = prykh + uch + rozv + skbvirl + htskbvil;
				reportCommon.setAllcount(allcount);
				listReportCommon.add(reportCommon);
				
				
				
				
			}
//			for (SamHurtokUPU samHurtokUPU : listSmHurtokUPU) {
//				int prykh, uch, rozv, skbvirl, htskbvil, allcount;
//				reportCommon = new ReportCommonPojo();
//				reportCommon.setIdkurin(samHurtokUPU.getId());
//				reportCommon.setIdstan(samHurtokUPU.getStanytsya().getId());
//				//reportCommon.setNumbersmhurtok(samHurtokUPU.get);
//				reportCommon.setNamekurin(samHurtokUPU.getNameSamHurtok());
//				
//				listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.прих.", ondate);
//				prykh = listCount.size();
//				reportCommon.setPrykhylnyk(prykh);
//				
//				listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.уч.", ondate);
//				uch = listCount.size();
//				reportCommon.setUchansnyk(uch);
//				
//				listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.розв.", ondate);
//				rozv = listCount.size();
//				reportCommon.setRozviduvach(rozv);
//				
//				if(samHurtokUPU.getSexSamHurtok() == 0){
//					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.скоб.", ondate);
//					skbvirl = listCount.size();
//					reportCommon.setSkobvirlytsya(skbvirl);
//					
//					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.гетьм.скоб.", ondate);
//					htskbvil = listCount.size();
//					reportCommon.setHetmanskobvirlytsa(htskbvil);
//					
//				} else if(samHurtokUPU.getSexSamHurtok() == 1){
//					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.вірл.", ondate);
//					skbvirl = listCount.size();
//					reportCommon.setSkobvirlytsya(skbvirl);
//					
//					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.гетьм.вірл.", ondate);
//					htskbvil = listCount.size();
//					reportCommon.setHetmanskobvirlytsa(htskbvil);
//				} else {
//					int boy, girl;
//					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.скоб.", ondate);
//					boy = listCount.size();
//					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.вірл.", ondate);
//					girl = listCount.size();
//					skbvirl = boy+girl;
//					reportCommon.setSkobvirlytsya(skbvirl);
//					
//					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.гетьм.скоб.", ondate);
//					boy = listCount.size();
//					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.гетьм.вірл.", ondate);
//					girl = listCount.size();
//					htskbvil = boy+girl;
//					reportCommon.setHetmanskobvirlytsa(htskbvil);
//					
//				}
//				allcount = prykh + uch + rozv + skbvirl + htskbvil;
//				reportCommon.setAllcount(allcount);
//				listReportCommon.add(reportCommon);
//			}
			
			listReportSmHurtok = new ArrayList<ReportCommonSmHurtokPojo>();
			
			for (SamHurtokUPU samHurtokUPU : listSmHurtokUPU) {
				int prykh, uch, rozv, skbvirl, htskbvil, allcount;
				
//				Long idstan = samHurtokUPU.getStanytsya().getId();
//				Long idokruha = null;
//				listStanytsya = stanytsyaDao.findStanytsya(idstan);
//				
//					for (Stanytsya stan: listStanytsya) {						
//						if(stan.getOkruha()!=null){
//							idokruha = stan.getOkruha().getId();
//						}
//					}
				
				listReport = quarterlyReportsUPUDao.findQuartReportSamHurtok(samHurtokUPU.getId(), toDate());
				Integer approved = 0;
				for (QuarterlyReportsUPU rep : listReport) {
					approved = rep.getApproved();
				}
				
				reportCommonSmHurtok = new ReportCommonSmHurtokPojo();
				reportCommonSmHurtok.setIdsmhurtok(samHurtokUPU.getId());
				reportCommonSmHurtok.setIdstan(samHurtokUPU.getStanytsya().getId());
				reportCommonSmHurtok.setNamesmhurtok(samHurtokUPU.getNameSamHurtok());
				reportCommonSmHurtok.setApproved(approved);
				listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.прих.", ondate);
				prykh = listCount.size();
				reportCommonSmHurtok.setPrykhylnyk(prykh);
				
				listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.уч.", ondate);
				uch = listCount.size();
				reportCommonSmHurtok.setUchansnyk(uch);
				
				listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.розв.", ondate);
				rozv = listCount.size();
				reportCommonSmHurtok.setRozviduvach(rozv);
				
				if(samHurtokUPU.getSexSamHurtok() == 0){
					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.скоб.", ondate);
					skbvirl = listCount.size();
					reportCommonSmHurtok.setSkobvirlytsya(skbvirl);
					
					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.гетьм.скоб.", ondate);
					htskbvil = listCount.size();
					reportCommonSmHurtok.setHetmanskobvirlytsa(htskbvil);
					
				} else if(samHurtokUPU.getSexSamHurtok() == 1){
					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.вірл.", ondate);
					skbvirl = listCount.size();
					reportCommonSmHurtok.setSkobvirlytsya(skbvirl);
					
					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.гетьм.вірл.", ondate);
					htskbvil = listCount.size();
					reportCommonSmHurtok.setHetmanskobvirlytsa(htskbvil);
				} else {
					int boy, girl;
					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.скоб.", ondate);
					boy = listCount.size();
					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.вірл.", ondate);
					girl = listCount.size();
					skbvirl = boy+girl;
					reportCommonSmHurtok.setSkobvirlytsya(skbvirl);
					
					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.гетьм.скоб.", ondate);
					boy = listCount.size();
					listCount = stupinDao.findCountStupinSMHurt(samHurtokUPU.getId(), "пл.гетьм.вірл.", ondate);
					girl = listCount.size();
					htskbvil = boy+girl;
					reportCommonSmHurtok.setHetmanskobvirlytsa(htskbvil);
					
				}
				allcount = prykh + uch + rozv + skbvirl + htskbvil;
				reportCommonSmHurtok.setAllcount(allcount);
				listReportSmHurtok.add(reportCommonSmHurtok);
			}
			
//			for (ReportCommonPojo r : listReportCommon) {
//				System.out.println("idstan = "+r.getIdstan());
//				System.out.println("kurin = "+r.getNamekurin());				
//			}
			List<String> listondate = new ArrayList<String>();
			listondate.add(ondate);
			modelAndView.addObject("liststanytsya", listStanytsya);
			modelAndView.addObject("listreportcommon", listReportCommon);
			modelAndView.addObject("listreportsmhurtok", listReportSmHurtok);
			modelAndView.addObject("listondate", listondate);
			modelAndView.addObject("listokruha", listOkruha);
			return modelAndView;
		} else {			
			return modelAndView;
		}
		
	}
	private String toDate() {

		cal.setTime(today);
		// Using DateFormat format method we can create a string
		// representation of a date with the defined format.
		String sysdate = df.format(today);

		// Print what date is today!
		System.out.println("Report Date: " + sysdate);
		return sysdate;
	}
}
