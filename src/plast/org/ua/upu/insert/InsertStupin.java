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
		
		
		listUlad = uladDao.findUlad("УПН");
		for (Ulad ulad2 : listUlad) {
			ulad = new Ulad(ulad2.getId(), ulad2.getNameulad());
		}
				
		stupin.setNameStupin("новак прихильник");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("новачка прихильниця");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("пластун орля");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("пластунка орля");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("пластун сильне орля");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("пластунка сильне орля");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("пластун красне орля");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("пластунка красне орля");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("пластун обережне орля");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("пластунка обережне орля");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("пластун бистре орля");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("пластунка бистре орля");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		
		listUlad = uladDao.findUlad("УПЮ");
		for (Ulad ulad2 : listUlad) {
			ulad = new Ulad(ulad2.getId(), ulad2.getNameulad());
		}
				
		stupin.setNameStupin("пл.прих.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("пл.уч.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("пл.розв.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("пл.скоб");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("пл.вірл.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		
		stupin.setNameStupin("пл.скоб-греб.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("пл.вірл.-греб.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("пл.скоб-обс.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("пл.вірл.-обс.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		stupin.setNameStupin("пл.гетьм.скоб.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("пл.гетьм.вірл.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		listUlad = uladDao.findUlad("УСП");
		for (Ulad ulad2 : listUlad) {
			ulad = new Ulad(ulad2.getId(), ulad2.getNameulad());
		}
		
		stupin.setNameStupin("ст.пл.прих.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("ст.пл.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("ст.пл.скоб.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("ст.пл.вірл.");
		stupin.setUlad(ulad);
		
		
		stupin.setNameStupin("ст.пл.скоб-греб.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("ст.пл.вірл.-греб.");
		stupin.setUlad(ulad);
		
		stupin.setNameStupin("ст.пл.скоб-обс.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("ст.пл.вірл.-обс.");
		stupin.setUlad(ulad);
		
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("ст.пл.гетьм.скоб.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("ст.пл.гетьм.вірл.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		
		listUlad = uladDao.findUlad("УПС");
		for (Ulad ulad2 : listUlad) {
			ulad = new Ulad(ulad2.getId(), ulad2.getNameulad());
		}
		
		stupin.setNameStupin("пл.сен.прих.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("пл.сен.пр.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("пл.сен.дов.");
		stupin.setUlad(ulad);
		stupinDao.addStupin(stupin);
		stupin.setNameStupin("пл.сен.кер.");
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
