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
			String first = "<h2>����!</h2>";
			String second = null;
			if(sm.getSexSamHurtok() == 0){
				second = "<p>�� ��������� ���������� ������ "+sm.getNameSamHurtok() + " ������� "+stan+"</p>";
			} else if(sm.getSexSamHurtok() == 1) {
				second = "<p>�� ���������� ���������� ������ "+sm.getNameSamHurtok() + " ������� "+stan+"</p>";
			} else if(sm.getSexSamHurtok() == 2){
				second = "<p>�� ���������/�� ���������� ������ "+sm.getNameSamHurtok() + " ������� "+stan+"</p>";
			}
			
			String third = "<p>����� ������� �������� ���������� �������� �����, ���� ��� ��������� ������� ��������������� ������ �����: <b>"+hashid+"</b></p>";
			
			String forth = "<p><b>��������</b> �� ����������� ����� ���� �������� ������. ���� ��� ������� �����</p>";
			
			String text = first + second +third+forth;
			SendEmail sendEmail = new SendEmail("techniclsuport@gmail.com", "@ctiv@tion");
			sendEmail.send("����� ����������� ������", text, "vasylshvv@gmail.com", /*"vasylshvv@gmail.com"*/sm.getEmailSamHurtok());
		}
	}

}
