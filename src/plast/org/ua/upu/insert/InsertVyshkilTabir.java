package plast.org.ua.upu.insert;

import java.util.List;

import plast.org.ua.upu.dao.VyshkilTabirDao;
import plast.org.ua.upu.idao.IVyshkilTabirDao;
import plast.org.ua.upu.table.VyshkilTabir;

public class InsertVyshkilTabir {
	private IVyshkilTabirDao vyshkilTabirDao = VyshkilTabirDao.getInstance();
	private VyshkilTabir vyshkilTabir;
	private List<VyshkilTabir> listVyshkilTabir;
	public void insert(){
		vyshkilTabir = new VyshkilTabir();
		
		vyshkilTabir.setNamevyshkiltabir("������� ����� �������� ��");
		vyshkilTabirDao.addVyshkilTabir(vyshkilTabir);
		
		vyshkilTabir.setNamevyshkiltabir("������� ����� �������� ��");
		vyshkilTabirDao.addVyshkilTabir(vyshkilTabir);
		
		vyshkilTabir.setNamevyshkiltabir("������� ����� ��������� ��");
		vyshkilTabirDao.addVyshkilTabir(vyshkilTabir);
		
		vyshkilTabir.setNamevyshkiltabir("������� ����� ��������� ��");
		vyshkilTabirDao.addVyshkilTabir(vyshkilTabir);
		
		vyshkilTabir.setNamevyshkiltabir("���������� ��");
		vyshkilTabirDao.addVyshkilTabir(vyshkilTabir);
		
		vyshkilTabir.setNamevyshkiltabir("���������� ��");
		vyshkilTabirDao.addVyshkilTabir(vyshkilTabir);
		
		vyshkilTabir.setNamevyshkiltabir("����� ��");
		vyshkilTabirDao.addVyshkilTabir(vyshkilTabir);
		
		vyshkilTabir.setNamevyshkiltabir("����� ��");
		vyshkilTabirDao.addVyshkilTabir(vyshkilTabir);
	}
	public int count(){
		listVyshkilTabir = vyshkilTabirDao.findAllVyshkilTabir();
		int count = 0;
		for (VyshkilTabir vyshktabir : listVyshkilTabir) {
			count = (int)(long)vyshktabir.getId();
		}
		return count;
	}
}
