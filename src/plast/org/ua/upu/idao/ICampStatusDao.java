package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.table.CampStatus;

public interface ICampStatusDao {
	public List<CampStatus> findAllCampStatus();
	public List<CampStatus> findOneCampStatus(Long id);
	public void addCampStatus(CampStatus campStatus);
}
