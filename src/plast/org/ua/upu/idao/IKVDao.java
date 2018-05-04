package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.table.KV;

public interface IKVDao {
	public void addKV(KV kv);
	public List<KV> findAll();
	public List<KV> findKV(Long id);
}
