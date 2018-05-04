package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.table.Okruha;


public interface IOkruhaDao {
	public void addOkruha(Okruha okruha);
	public List<Okruha> findAllOkruha();
	public List<Okruha> findOneOkruha(Long id);
	
}
