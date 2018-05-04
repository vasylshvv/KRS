package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.pojo.PersonDeatailPojo;
import plast.org.ua.upu.pojo.PersonStanytsyaPojo;
import plast.org.ua.upu.pojo.PersonUladStupinPojo;
import plast.org.ua.upu.pojo.PersonVyshkilPojo;
import plast.org.ua.upu.pojo.PersonVyshkilTabirPojo;
import plast.org.ua.upu.pojo.PersonsUPUPojo;
import plast.org.ua.upu.pojo.PersonsUSPUPSPojo;
import plast.org.ua.upu.pojo.PersonKVPojo;
import plast.org.ua.upu.pojo.PersonKurinUSPPojo;
import plast.org.ua.upu.table.PersonDilovodHurtok;
import plast.org.ua.upu.table.PersonDilovodKurin;
import plast.org.ua.upu.table.PersonKurinHurtok;
import plast.org.ua.upu.table.PersonKurinUSPUPS;
import plast.org.ua.upu.table.PersonStupinUlad;
import plast.org.ua.upu.table.PersonVyshkilTabir;
import plast.org.ua.upu.table.Persons;
import plast.org.ua.upu.table.PersonsKV;
import plast.org.ua.upu.table.PersonsStanytsya;
import plast.org.ua.upu.table.PersonsVyshkil;

public interface IPersonsDao {
	public void addPerson(Persons persons);
	public void addPersStupUlad(PersonStupinUlad personStupinUlad);
	public void updatePersStupUlad(PersonStupinUlad personStupinUlad);
	public void updatePersDilovodHurtokd(PersonDilovodHurtok personDilovodHurtok);
	public void updatePersDilovodKurin(PersonDilovodKurin personDilovodKurin);
	public void updatePersonStanytsya(PersonsStanytsya personsStanytsya);
	public void updatePersonKurinUSPUPS(PersonKurinUSPUPS personKurinUSPUPS);
	public void updatePersKurinHurtok(PersonKurinHurtok personKurinHurtok);
	public void addPersKurinHurtok(PersonKurinHurtok personKurinHurtok);
	public void addPersonStanytsya(PersonsStanytsya personsStanytsya);
	
	public void addPersonKurinUspUps(PersonKurinUSPUPS personKurinUSPUPS);
	public void addPersonKV(PersonsKV personsKV);
	public void addPersonVyshkil(PersonsVyshkil personsVyshkil);
	public void addPersonVyshkilTabir(PersonVyshkilTabir personVyshkilTabir);
	public List<PersonsUPUPojo> findPerson(Long idkurin);
	public List<PersonsUPUPojo> findPersonSMHurtok(Long idsmurtok);
	public List<PersonsUSPUPSPojo> findPersonUSPUPS(Long idstan);
	public List<Persons> findOnePersons(Long id);
	public List<PersonsUPUPojo> findOnePersonsHurtok(Long personid);
	public List<PersonsUPUPojo> findOnePersonsSMHurtok(Long personid);
	public List<PersonKurinHurtok> findHurtokKurin(Long personid);
	public List<PersonDeatailPojo> findOneDetailPerson(Long personid);
	public List<PersonStanytsyaPojo> findOnePersonStanytsya(Long personid);
	public List<PersonsStanytsya> findPersonStanytsya(Long personid);
	public List<PersonUladStupinPojo> findOnePersonUladStupin(Long personid);
	public List<PersonKVPojo> findOnePersonKV(Long personid);
	public List<PersonKurinUSPPojo> findOnePersonKurinUSP(Long personid);
	public List<PersonVyshkilTabirPojo> findOnePersonVyshkilTabir(Long personid);
	public List<PersonVyshkilPojo> findOnrPersonVyshkil(Long personid);
	public List<PersonStupinUlad> findPersStupUlad(Long personid);
	public List<PersonKurinUSPUPS> findPersKurinUSPUPS(Long personid);
	public List<PersonsKV> findPersKV(Long personid);
	public List<PersonsVyshkil> findPersVyshkil(Long personid);
	public List<PersonVyshkilTabir> findPersVyshkilTabir(Long personid);
	public void updatePerson(Persons persons);
	public void updatePersonKV(PersonsKV personsKV);
	public void updatePersonVyshkil(PersonsVyshkil personsVyshkil);
	public void deletePersonVyshkil(PersonsVyshkil personsVyshkil);
	public void updatePersonVyshkilTabir(PersonVyshkilTabir personVyshkilTabir);
}
