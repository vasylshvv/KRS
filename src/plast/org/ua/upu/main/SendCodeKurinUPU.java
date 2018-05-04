package plast.org.ua.upu.main;

import java.util.List;

import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.dao.StanytsyaDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.IStanystyaDao;
import plast.org.ua.upu.md.MD5;
import plast.org.ua.upu.sendemail.SendEmail;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.Stanytsya;

public class SendCodeKurinUPU {
	private IKurinUPUDao kurinUPUDao = KurinUPUDao.getInstance();
	private IStanystyaDao stanystyaDao = StanytsyaDao.getInstance();
	
	private KurinUPU kurinUPU;
	private List<KurinUPU> listKurin;
	private List<Stanytsya> listStan;
	private String hashid = null;
	
	public void codeKurin(Long id) throws Exception {
		listKurin = kurinUPUDao.findOneKurinUPU(id);
		for (KurinUPU k : listKurin) {
			hashid = MD5.getHash(k.getNumberKurin()+k.getNameKurin());
			kurinUPU = new KurinUPU(k.getId(), k.getNumberKurin(), k.getSexKurin(), k.getNameKurin(), k.getDateRegister(), 
					k.getEmailKurin(), hashid, k.getStanytsya());
			kurinUPUDao.updateKurinUPU(kurinUPU);	
		
			listStan = stanystyaDao.findStanytsya(k.getStanytsya().getId());
			String stan = null;
			for (Stanytsya s : listStan) {
				stan = s.getNamestan();
			}
		
			String first = "<h2>����!</h2>";
			String second = null;
			if(k.getSexKurin() == 0){
				second = "<p>�� ��������� ����� "+k.getNumberKurin() +" ��. "+k.getNameKurin()+ " ������� "+stan+"</p>";
			} else if(k.getSexKurin() == 1) {
				second = "<p>�� ���������� ����� "+k.getNumberKurin() +" ��. "+k.getNameKurin()+ " ������� "+stan+"</p>";
			} else if(k.getSexKurin() == 2){
				second = "<p>�� ���������/�� ����� "+k.getNumberKurin() +" ��. "+k.getNameKurin()+ " ������� "+stan+"</p>";
			}
			
			String third = "<p>����� ������� �������� ���������� �������� �����, ���� ��� ��������� ������� ��������������� ������ �����: <b>"+hashid+"</b></p>";
			
			String forth = "<p><b>��������</b> �� ����������� ����� ���� �������� ������. ���� ��� ������� �����</p>";
			
			String text = first + second +third+forth;
			SendEmail sendEmail = new SendEmail("techniclsuport@gmail.com", "@ctiv@tion");
			sendEmail.send("����� ������", text, "vasylshvv@gmail.com", /*"vasylshvv@gmail.com"*/k.getEmailKurin());
			
		
		}
		
	}
}
