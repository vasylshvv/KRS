package plast.org.ua.upu.main;

import java.util.List;

import plast.org.ua.upu.dao.SamHurtokUPUDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.md.MD5;
import plast.org.ua.upu.sendemail.SendEmail;
import plast.org.ua.upu.table.SamHurtokUPU;
import plast.org.ua.upu.table.Stanytsya;

public class SendCodeSMHurtokUPU {
	private ISamHurtokUPUDao samHurtokUPUDao = SamHurtokUPUDao.getInstance();
	private IStanystyaDao stanystyaDao = StanytsyaDao.getInstance();
	private SamHurtokUPU samHurtokUPU;
	private List<SamHurtokUPU> listSmHurtok;
	private List<Stanytsya> listStan;
	private String hashid = null;

	public void codeSMHurtok(Long id) throws Exception {
		listSmHurtok = samHurtokUPUDao.findOneSamHurtok(id);
		for (SamHurtokUPU sm : listSmHurtok) {
			hashid = MD5.getHash(sm.getNameSamHurtok() + sm.getDateFrom());
			samHurtokUPU = new SamHurtokUPU(sm.getId(), sm.getNameSamHurtok(), sm.getSexSamHurtok(),
					sm.getEmailSamHurtok(), sm.getDateFrom(), sm.getDateTo(), hashid, sm.getVidznakaHurtka(),
					sm.getStanytsya());
			samHurtokUPUDao.updateSMhurtok(samHurtokUPU);
			listStan = stanystyaDao.findStanytsya(sm.getStanytsya().getId());
			String stan = null;
			for (Stanytsya s : listStan) {
				stan = s.getNamestan();
			}
			String first = "<h2>СКОБ!</h2>";
			String second = null;
			if(sm.getSexSamHurtok() == 0){
				second = "<p>Ти реєстрував самостійний гурток "+sm.getNameSamHurtok() + " станиці "+stan+"</p>";
			} else if(sm.getSexSamHurtok() == 1) {
				second = "<p>Ти реєструвала самостійний гурток "+sm.getNameSamHurtok() + " станиці "+stan+"</p>";
			} else if(sm.getSexSamHurtok() == 2){
				second = "<p>Ти реєстрував/ла самостійний гурток "+sm.getNameSamHurtok() + " станиці "+stan+"</p>";
			}
			
			String third = "<p>Згідно безпеки відбулося поновлення кодового слова, тому для входження потрібно використовувати кодове слово: <b>"+hashid+"</b></p>";
			
			String forth = "<p><b>ПРОХАННЯ</b> не передавайте свого коду стороннім особам. Дбай про безпеку даних</p>";
			
			String text = first + second +third+forth;
			SendEmail sendEmail = new SendEmail("techniclsuport@gmail.com", "@ctiv@tion");
			sendEmail.send("Реєстр самостійного гуртка", text, "vasylshvv@gmail.com", /*"vasylshvv@gmail.com"*/sm.getEmailSamHurtok());
		}
	}

}
