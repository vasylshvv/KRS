package plast.org.ua.upu.insert;

import java.util.List;

import plast.org.ua.upu.dao.UladDao;
import plast.org.ua.upu.idao.IUladDao;
import plast.org.ua.upu.table.Ulad;

public class InsertUlad {
	private IUladDao uladDao = UladDao.getInstance();
	private Ulad ulad;
	private List<Ulad> listUlad;
	public void insert(){
		ulad = new Ulad();
		ulad.setNameulad("”œÕ");
		uladDao.addUlad(ulad);
		ulad.setNameulad("”œﬁ");
		uladDao.addUlad(ulad);
		ulad.setNameulad("”—œ");
		uladDao.addUlad(ulad);
		ulad.setNameulad("”œ—");
		uladDao.addUlad(ulad);
		ulad.setNameulad("œÚ‡¯‡Ú‡");
		uladDao.addUlad(ulad);
	}
	public int count(){
		listUlad = uladDao.findAllUlad();
		int count = 0;
		for (Ulad ulad : listUlad) {
			count =(int)(long)ulad.getId();
		}
		return count;
	}
}
