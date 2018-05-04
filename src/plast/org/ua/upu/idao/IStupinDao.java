package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.pojo.PersonsCountStupinPojo;
import plast.org.ua.upu.pojo.PersonsUPUChangeStupinPojo;
import plast.org.ua.upu.table.PersonStupinUlad;
import plast.org.ua.upu.table.Stupin;

public interface IStupinDao {
	public void addStupin(Stupin stupin);	
	public List<Stupin> findAllStupin();
	public List<Stupin> findStupin(Long id);
	public List<Stupin> findStupinNeim(String nameStupin);
	public List<Stupin> findStupinUlad(Long idUlad);
	public List<PersonsUPUChangeStupinPojo> findStupinChange(Long personid);
	public List<PersonStupinUlad> findStartPerson(Long personid);
	public List<PersonStupinUlad> findOnlyOneStupin(Long id);
	public List<PersonsCountStupinPojo> findCountStupin(Long idkurin, String namestupin, String ondate);
	public List<PersonsCountStupinPojo> findCountStupinSMHurt(Long idsmHurt, String namestupin, String ondate);

}
