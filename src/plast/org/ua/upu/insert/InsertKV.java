package plast.org.ua.upu.insert;

import java.util.List;

import plast.org.ua.upu.dao.KVDao;
import plast.org.ua.upu.idao.IKVDao;
import plast.org.ua.upu.table.KV;

public class InsertKV {
	private IKVDao kvdao = KVDao.getInstance();
	private KV kv;
	private List<KV> listkv;
	public void insert(){
		kv = new KV();
		kv.setName("ÊÂ 1");
		kvdao.addKV(kv);
				
		kv.setName("ÊÂ 2");
		kvdao.addKV(kv);
	}
	public int count(){
		listkv = kvdao.findAll();
		int count = 0;
		for (KV kv : listkv) {
			count = (int)(long)kv.getId();
		}
		return count;
	}
}
