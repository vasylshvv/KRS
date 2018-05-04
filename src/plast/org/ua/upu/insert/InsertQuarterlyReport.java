package plast.org.ua.upu.insert;

import plast.org.ua.upu.dao.QuarterlyReportsUPUDao;
import plast.org.ua.upu.idao.IQuarterlyReportsUPUDao;
import plast.org.ua.upu.table.KurinUPU;
import plast.org.ua.upu.table.QuarterlyReportsUPU;

public class InsertQuarterlyReport {
	private IQuarterlyReportsUPUDao quarterlyReportsUPUDao = QuarterlyReportsUPUDao.getInstance();
	private QuarterlyReportsUPU reportsUPU;
	public void addQuarter(KurinUPU kurinUPU){
		
		//reportsUPU = new QuarterlyReportsUPU(datebegin, dateend, null, null, 0, null, kurinUPU, null, null);
		quarterlyReportsUPUDao.addQuarterlyReport(reportsUPU);
	}
}
