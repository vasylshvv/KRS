package plast.org.ua.upu.idao;


import java.util.List;

import plast.org.ua.upu.table.QuarterlyReportsUPU;

public interface IQuarterlyReportsUPUDao {
	public void addQuarterlyReport(QuarterlyReportsUPU quarterlyReportsUPU);
	public void updateQuartReportKurin(QuarterlyReportsUPU quarterlyReportsUPU);
	public List<QuarterlyReportsUPU> findQuartReportKurin(Long idkurin, String sysdate);
	public List<QuarterlyReportsUPU> findQuartReportSamHurtok(Long idsmhurtok, String sysdate);
	public List<QuarterlyReportsUPU> quaterCountKurin(String ondate, Long kurinid);
	public List<QuarterlyReportsUPU> quaterCountsmHurtok(String ondate, Long smhurtokid);
	public List<QuarterlyReportsUPU> findOnlyDateKurin(String ondate);
	public List<QuarterlyReportsUPU> findOnlyDatesmHurtok(String ondate);
	public List<QuarterlyReportsUPU> findOnlyDateEachKurin(String ondate, Long id);
	public List<QuarterlyReportsUPU> findOnlyDateEachSMHurtok(String ondate, Long id);
	
}
