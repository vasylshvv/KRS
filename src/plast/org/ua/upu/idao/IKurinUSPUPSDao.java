package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.table.KurinUSPUPS;

public interface IKurinUSPUPSDao {
	public void addKurin(KurinUSPUPS kurinUSPUPS);
	public List<KurinUSPUPS> findAllKurin();
	public List<KurinUSPUPS> findOneKurin(Long id);
}
