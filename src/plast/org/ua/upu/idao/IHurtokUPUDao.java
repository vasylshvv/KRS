package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.table.HurtokUPU;


public interface IHurtokUPUDao {
	public void addHurtokUPU(HurtokUPU hurtokUPU);
	public List<HurtokUPU> findAllHurtokUPU();
	public List<HurtokUPU> findHurtokUPU(Long id);
	public List<HurtokUPU> findHurtokINKurinUPU(Long id);
	
	
}
