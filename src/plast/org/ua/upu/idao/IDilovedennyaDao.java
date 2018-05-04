package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.pojo.PersonsUPUChangeDilovodHurtokPojo;
import plast.org.ua.upu.pojo.PersonsUPUChangeDilovodKurinPojo;
import plast.org.ua.upu.pojo.ProvidKurinPojo;
import plast.org.ua.upu.table.DilovedennyaHurtokUPU;
import plast.org.ua.upu.table.DilovedennyaKurinUPU;
import plast.org.ua.upu.table.PersonDilovodHurtok;
import plast.org.ua.upu.table.PersonDilovodKurin;

public interface IDilovedennyaDao {
	public void addDilovodHurtok(DilovedennyaHurtokUPU dilovedennyaHurtokUPU);
	public void addDilovodKurin(DilovedennyaKurinUPU dilovedennyaKurinUPU);
	
	public void addPersonDilovodHurtok(PersonDilovodHurtok dilovodHurtok);
	public void addPersonDilovodKurin(PersonDilovodKurin dilovodKurin);
	
	public List<DilovedennyaHurtokUPU> findAllDilovedennyaHurtok();
	public List<DilovedennyaKurinUPU> findAllDilovedennyaKurin();
	
	public List<DilovedennyaHurtokUPU> findOneDilovedennyaHurtok(Long id);
	public List<DilovedennyaKurinUPU> findOneDilovedennyaKurin(Long id);
	
	public List<PersonDilovodHurtok> findDilovodHurtok(Long personid);
	
	public List<PersonDilovodHurtok> findOnlyOneDilovodHurtok(Long id);
	public List<PersonDilovodKurin> findOnlyOneDilovodKurin(Long id);
	
	public List<PersonDilovodHurtok> findPersonKurinDilovodHurtok(Long kurinid);
	public List<PersonDilovodHurtok> findPersonSMhurtokDilovodHurtok(Long smhurtokid);
	
	public List<ProvidKurinPojo> findProvidKurin(Long kurinid);
	
	public List<PersonsUPUChangeDilovodHurtokPojo> findAllDilovodHurtokPerson(Long personid);
	public List<PersonsUPUChangeDilovodHurtokPojo> findAllDilovodSMHurtokPerson(Long personid);
	
	public List<PersonsUPUChangeDilovodKurinPojo> findAllDilovodKurinPerson(Long personid);
	
}
