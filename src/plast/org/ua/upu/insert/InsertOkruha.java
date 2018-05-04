package plast.org.ua.upu.insert;

import java.util.List;

import plast.org.ua.upu.dao.OkruhaDao;
import plast.org.ua.upu.idao.IOkruhaDao;
import plast.org.ua.upu.table.Okruha;

public class InsertOkruha {
	private IOkruhaDao okruhaDao = OkruhaDao.getInstance();
	private Okruha okruha;
	private List<Okruha> listOkruha;
	
	public void insert(String nameOkruha){
		okruha = new Okruha(nameOkruha);
		okruhaDao.addOkruha(okruha);
	}
}
