package plast.org.ua.upu.insert;

import java.util.List;

import plast.org.ua.upu.dao.StupinDao;
import plast.org.ua.upu.dao.UladDao;
import plast.org.ua.upu.idao.IStupinDao;
import plast.org.ua.upu.idao.IUladDao;
import plast.org.ua.upu.table.Stupin;
import plast.org.ua.upu.table.Ulad;

public class InsertStupin {
	private IStupinDao stupinDao = StupinDao.getInstance();
	private IUladDao uladDao = UladDao.getInstance();
	private Stupin stupin;
	private Ulad ulad;
	private List<Stupin> listStupin;
	private List<Ulad> listUlad;
	public void insert(){
		stupin = new Stupin();
		
		
		listUlad = uladDao.findUlad("���");
		for (Ulad ulad2 : listUlad) {
			ulad = new Ulad(ulad2.getId(), ulad2.getNameulad());
		}
				
		stupin.setNameStupin("����� ����������");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("������� �����������");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("������� ����");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("��������� ����");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("������� ������ ����");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("��������� ������ ����");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("������� ������ ����");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("��������� ������ ����");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("������� �������� ����");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("��������� �������� ����");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("������� ������ ����");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("��������� ������ ����");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		
		listUlad = uladDao.findUlad("���");
		for (Ulad ulad2 : listUlad) {
			ulad = new Ulad(ulad2.getId(), ulad2.getNameulad());
		}
				
		stupin.setNameStupin("��.����.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.��.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.����.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.����");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.���.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		
		stupin.setNameStupin("��.����-����.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.���.-����.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("��.����-���.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.���.-���.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("��.�����.����.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.�����.���.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		listUlad = uladDao.findUlad("���");
		for (Ulad ulad2 : listUlad) {
			ulad = new Ulad(ulad2.getId(), ulad2.getNameulad());
		}
		
		stupin.setNameStupin("��.��.����.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.��.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.��.����.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.��.���.");
		stupin.setUlad(ulad);
		
		
		stupin.setNameStupin("��.��.����-����.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.��.���.-����.");
		stupin.setUlad(ulad);
		
		stupin.setNameStupin("��.��.����-���.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.��.���.-���.");
		stupin.setUlad(ulad);
		
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.��.�����.����.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.��.�����.���.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		listUlad = uladDao.findUlad("���");
		for (Ulad ulad2 : listUlad) {
			ulad = new Ulad(ulad2.getId(), ulad2.getNameulad());
		}
		
		stupin.setNameStupin("��.���.����.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.���.��.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.���.���.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("��.���.���.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
	
	}
	public int count(){
		listStupin = stupinDao.findAllStupin();
		int count = 0;
		for (Stupin stupin2 : listStupin) {
			count = (int)(long)stupin2.getId();
		}
		return count;
	}
}
