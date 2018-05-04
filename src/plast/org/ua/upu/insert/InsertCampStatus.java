package plast.org.ua.upu.insert;

import java.util.List;

import plast.org.ua.upu.dao.CampStatusDao;
import plast.org.ua.upu.idao.ICampStatusDao;
import plast.org.ua.upu.table.CampStatus;

public class InsertCampStatus {
	private ICampStatusDao campStatusDao = CampStatusDao.getInstance();
	private CampStatus campStatus;
	private List<CampStatus> listCampStatus;
	public void insert(){
		campStatus = new CampStatus();

		campStatus.setNameCampStatus("ройовий");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("міжройовий");
		campStatusDao.addCampStatus(campStatus);
		
		campStatus.setNameCampStatus("гніздовий");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("міжгніздовий");
		campStatusDao.addCampStatus(campStatus);
		
		
		campStatus.setNameCampStatus("гуртковий");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("міжгуртковий");
		campStatusDao.addCampStatus(campStatus);
		
		campStatus.setNameCampStatus("курінний");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("міжкурінний");
		
		
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("станичний");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("міжстаничний");
		campStatusDao.addCampStatus(campStatus);
		
		
		campStatus.setNameCampStatus("окружний");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("міжокружний");
		campStatusDao.addCampStatus(campStatus);
		
		campStatus.setNameCampStatus("крайовий апробаційний");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("крайовий");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("міжкрайовий");
		campStatusDao.addCampStatus(campStatus);
	}
	public int count(){
		listCampStatus = campStatusDao.findAllCampStatus();
		int count = 0;
		for (CampStatus campStatus : listCampStatus) {
			count = (int)(long)campStatus.getId();
		}
		return count;
	}
}
