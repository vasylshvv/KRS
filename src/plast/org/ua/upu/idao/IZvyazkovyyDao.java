package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.pojo.ZvyazkovyyPojo;
import plast.org.ua.upu.table.Zvyazkovyy;

public interface IZvyazkovyyDao {
	public void addZvyazkovyy(Zvyazkovyy zvyazkovyy);
	public void updateOldZvyazkovyy(Zvyazkovyy zvyazkovyy);
	public List<ZvyazkovyyPojo> findZvyazkovyyKurin(Long idkurin);
	public List<Zvyazkovyy> findOneZvyazkovyy(Long id);	
	public List<Zvyazkovyy> findOneZvyazkovyyInKurin(Long idkurin);
}
