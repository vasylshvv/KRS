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

		campStatus.setNameCampStatus("�������");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("���������");
		campStatusDao.addCampStatus(campStatus);
		
		campStatus.setNameCampStatus("��������");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("����������");
		campStatusDao.addCampStatus(campStatus);
		
		
		campStatus.setNameCampStatus("���������");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("�����������");
		campStatusDao.addCampStatus(campStatus);
		
		campStatus.setNameCampStatus("�������");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("���������");
		
		
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("���������");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("�����������");
		campStatusDao.addCampStatus(campStatus);
		
		
		campStatus.setNameCampStatus("��������");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("����������");
		campStatusDao.addCampStatus(campStatus);
		
		campStatus.setNameCampStatus("�������� ������������");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("��������");
		campStatusDao.addCampStatus(campStatus);
		campStatus.setNameCampStatus("����������");
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
