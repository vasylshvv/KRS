package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.table.Ulad;

public interface IUladDao {
	public void addUlad(Ulad ulad);
	public List<Ulad> findAllUlad();
	public List<Ulad> findUlad(Long id);
	public List<Ulad> findUlad(String nameUlad);
}
