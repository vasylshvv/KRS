package plast.org.ua.upu.main;

import java.util.List;

import plast.org.ua.upu.dao.PersonsDao;
import plast.org.ua.upu.dao.SamHurtokUPUDao;
import plast.org.ua.upu.dao.VporyadnykDao;
import plast.org.ua.upu.idao.IPersonsDao;
import plast.org.ua.upu.idao.ISamHurtokUPUDao;
import plast.org.ua.upu.idao.IVporyadnykDao;
import plast.org.ua.upu.md.MD5;
import plast.org.ua.upu.sendemail.SendEmail;
import plast.org.ua.upu.table.Persons;
import plast.org.ua.upu.table.SamHurtokUPU;
import plast.org.ua.upu.table.Vporyadnyk;

public class SendCodeSMVporyadnyk {
	private IVporyadnykDao vporyadnykDao = VporyadnykDao.getInstance();
	private ISamHurtokUPUDao hurtokUPUDao = SamHurtokUPUDao.getInstance();
	private IPersonsDao personsDao = PersonsDao.getInstance();
	
	private List<Vporyadnyk> listVporyadnyk;
	private List<SamHurtokUPU> listSmHurtokUPU;
	private List<Persons> listPerson;
	
	private Vporyadnyk vporyadnyk;
	
	
	public void codeVporyadnyk(Long idSmHurtok) throws Exception {
		Long idPerson = null;
		String hashidVpor = null;
		String namehurtok = null;
		String email = null;
		String sfullname = null;
		Integer sex = null;
		listSmHurtokUPU = hurtokUPUDao.findOneSamHurtok(idSmHurtok);
		for (SamHurtokUPU sm : listSmHurtokUPU) {
			namehurtok = sm.getNameSamHurtok();
			sex = sm.getSexSamHurtok();
		}
		
		listVporyadnyk = vporyadnykDao.findVporyadnykForSamHurtok(idSmHurtok);
		for (Vporyadnyk vp : listVporyadnyk) {
				idPerson = vp.getPersons().getId();
		}
		
		listPerson = personsDao.findOnePersons(idPerson);
			
		for (Persons ps : listPerson) {
				email = ps.getEmail();
				sfullname = ps.getFirstName()+ps.getLastName();
			}
			
			hashidVpor =  MD5.getHash(namehurtok+sfullname);
			listVporyadnyk = vporyadnykDao.findVporyadnykForSamHurtok(idSmHurtok);
			for (Vporyadnyk vp : listVporyadnyk) {
					vporyadnyk = new Vporyadnyk(vp.getId(), vp.getDatebegin(), vp.getDateend(), vp.getSamhurtokupu(), vp.getPersons(), hashidVpor);
			}
			vporyadnykDao.update(vporyadnyk);
			
			 String first = "<h2>����!</h2>";
				String second = null;
				if(sex == 0){
					second = "<p>�������� �������� ������ ����� ��� ������"+namehurtok +/*" ��. "+namekurin+ /*" ������� "+stan+*/"</p>";
				} else if(sex == 1) {
					second = "<p>�������� �������� ������ ����� ��� ������"+namehurtok +/*" ��. "+namekurin+ /*" ������� "+stan+*/"</p>";
				} else if(sex == 2){
					second = "<p>�������� �������� ������ ����� ��� ������"+namehurtok +/*" ��. "+namekurin+ /*" ������� "+stan+*/"</p>";
				}
				
				String third = "<p>����� ������� �������� ���������� �������� �����, ���� ��� ��������� ������� ��������������� ������ �����: <b>"+hashidVpor+"</b></p>";
				
				String forth = "<p><b>��������</b> �� ����������� ����� ���� �������� ������. ���� ��� ������� �����</p>";
				
				String text = first + second +third+forth;
				SendEmail sendEmail = new SendEmail("techniclsuport@gmail.com", "@ctiv@tion");
				sendEmail.send("�������� ���������� ����", text, "vasylshvv@gmail.com", email);
			
		}
}
