package plast.org.ua.upu.main;

import java.util.List;

import plast.org.ua.upu.dao.KurinUPUDao;
import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.ZvyazkovyyDao;
import plast.org.ua.upu.idao.IKurinUPUDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.IZvyazkovyyDao;
import plast.org.ua.upu.md.MD5;
import plast.org.ua.upu.sendemail.SendEmail;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.Persons;
import plast.org.ua.upu.table.Zvyazkovyy;

public class SendCodeZvyazkovyy {
	private IZvyazkovyyDao zvyazkovyyDao = ZvyazkovyyDao.getInstance();
	private IPersonsDao personsDao = PersonsDao.getInstance();
	private IKurinUPUDao kurinUPUDao = KurinUPUDao.getInstance();
	private List<Zvyazkovyy> listZV;
	private List<Persons> listPerson;
	private List<KurinUPU> listKurin;
	private Zvyazkovyy zvyazkovyy;
	
	public void codeZvyazkovvy(Long id) throws Exception {
		listZV = zvyazkovyyDao.findOneZvyazkovyy(id);
		Long persid = null;
		Long kurid = null;
		for (Zvyazkovyy zv : listZV) {
			persid = zv.getPersons().getId();
			kurid = zv.getKurinupu().getId();
		}
		
		String fullnamezv = null;
		String emailzv = null;
		listPerson = personsDao.findOnePersons(persid);
		
		for (Persons prs : listPerson) {
			fullnamezv = prs.getFirstName()+prs.getLastName();
			emailzv = prs.getEmail();
		}
		
		listKurin = kurinUPUDao.findOneKurinUPU(kurid);
		String namekurin = null;
		Integer sex = null;
		Integer numberkurin = null;
		for (KurinUPU k : listKurin) {
			namekurin = k.getNameKurin();
			sex = k.getSexKurin();
			numberkurin = k.getNumberKurin();
		}
		
		
		 String hashcode = MD5.getHash(fullnamezv+namekurin);
		 
		 
		 listZV = zvyazkovyyDao.findOneZvyazkovyy(id);
		 for (Zvyazkovyy zv : listZV) {
			zvyazkovyy = new Zvyazkovyy(zv.getId(), zv.getDatebegin(), zv.getDateend(), zv.getKurinupu(), zv.getPersons(), hashcode);
		}
		zvyazkovyyDao.updateOldZvyazkovyy(zvyazkovyy);
		 
		 
		 String first = "<h2>����!</h2>";
			String second = null;
			if(sex == 0){
				second = "<p>�������� �������� ������ ����� ��� �.�."+numberkurin +" ��. "+namekurin+ /*" ������� "+stan+*/"</p>";
			} else if(sex == 1) {
				second = "<p>�������� �������� ������ ����� ��� �.�."+numberkurin +" ��. "+namekurin+ /*" ������� "+stan+*/"</p>";
			} else if(sex == 2){
				second = "<p>�������� �������� ������ ����� ��� ���.�."+" ��. "+namekurin+ /*" ������� "+stan+*/"</p>";
			}
			
			String third = "<p>����� ������� �������� ���������� �������� �����, ���� ��� ��������� ������� ��������������� ������ �����: <b>"+hashcode+"</b></p>";
			
			String forth = "<p><b>��������</b> �� ����������� ����� ���� �������� ������. ���� ��� ������� �����</p>";
			
			String text = first + second +third+forth;
			SendEmail sendEmail = new SendEmail("techniclsuport@gmail.com", "@ctiv@tion");
			sendEmail.send("����� ������", text, "vasylshvv@gmail.com", /*"vasylshvv@gmail.com"*/emailzv);
	}
}
