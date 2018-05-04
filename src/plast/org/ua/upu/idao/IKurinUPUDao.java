package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.table.KurinUPU;

public interface IKurinUPUDao {
	public void addKurinUPU(KurinUPU kurinUPU);
	public void updateKurinUPU(KurinUPU kurinUPU);
	public List<KurinUPU> findAllKurinUPU();
	public List<KurinUPU> findOneKurinUPU(Long id);
	public List<KurinUPU> findStanytsyaKurinUPU(Long stanytsyaId);
	public List<KurinUPU> findLvOkruhaStanytsyaKurinUPU();
}
