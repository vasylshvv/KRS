package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.table.SamHurtokUPU;

public interface ISamHurtokUPUDao {
	public void addsamhurtok(SamHurtokUPU samHurtokUPU);
	public void updateSMhurtok(SamHurtokUPU samHurtokUPU);
	public List<SamHurtokUPU> findAllSamHurtok();
	public List<SamHurtokUPU> findAllSamHurtok(Long stanid);
	public List<SamHurtokUPU> findOneSamHurtok(Long idhurtok);
	public List<SamHurtokUPU> findLvOkruhaStanytsyaSamHurtok();
}
