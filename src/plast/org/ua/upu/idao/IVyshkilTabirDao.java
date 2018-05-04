package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.table.VyshkilTabir;

public interface IVyshkilTabirDao {
	public void addVyshkilTabir(VyshkilTabir vyshkilTabir);
	public List<VyshkilTabir> findAllVyshkilTabir();
	public List<VyshkilTabir> findOneVyshkilTabir(Long id);
}
