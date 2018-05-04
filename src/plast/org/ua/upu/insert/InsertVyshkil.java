package plast.org.ua.upu.insert;

import java.util.List;

import plast.org.ua.upu.dao.VyshkilDao;
import plast.org.ua.upu.idao.IVyshkilDao;
import plast.org.ua.upu.table.Vyshkil;

public class InsertVyshkil {
	private IVyshkilDao vyshkilDao = VyshkilDao.getInstance();
	private Vyshkil vyshkil;
	private List<Vyshkil> listVyshkil;

	public void insert() {
		vyshkil = new Vyshkil();
		vyshkil.setNamevyshkil("КВДЧ");
		vyshkil.setDescription("Крайовий вишкіл дійсного членства");
		vyshkilDao.addVyshkil(vyshkil);
		vyshkil.setNamevyshkil("КВВ УПЮ");
		vyshkil.setDescription("Крайовий вишкіл впорядників УПЮ");
		vyshkilDao.addVyshkil(vyshkil);
		vyshkil.setNamevyshkil("КВЗ");
		vyshkil.setDescription("Крайовий вишкіл зв'язкових");
		vyshkilDao.addVyshkil(vyshkil);
		vyshkil.setNamevyshkil("КВПВ");
		vyshkil.setDescription("Крайовий вишкіл провідників вишколів");
		vyshkilDao.addVyshkil(vyshkil);

	}
	public int count() {
		listVyshkil = vyshkilDao.findAllVyshkil();
		int count = 0;
		for (Vyshkil vysh : listVyshkil) {
			count = (int)(long)vysh.getId();
		}
		return count;
	}
}
