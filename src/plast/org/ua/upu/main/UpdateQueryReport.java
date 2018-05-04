package plast.org.ua.upu.main;

import java.util.List;

import plast.org.ua.upu.dao.QuarterlyReportsUPUDao;
import plast.org.ua.upu.idao.IQuarterlyReportsUPUDao;
import plast.org.ua.upu.table.QuarterlyReportsUPU;

public class UpdateQueryReport {
	private IQuarterlyReportsUPUDao quarterlyReportsUPUDao = QuarterlyReportsUPUDao.getInstance();
	private List<QuarterlyReportsUPU> listquery;
	private List<QuarterlyReportsUPU> listOnequery;
	private QuarterlyReportsUPU quarterlyReportsUPU;

	public void updateKurin(String firstDate, String secondDate) {
		listquery = quarterlyReportsUPUDao.findOnlyDateKurin(firstDate);
		for (QuarterlyReportsUPU q : listquery) {
			System.out.println(q.getApproved() + "\t" + q.getId());
			System.out.println("q.getKurinUPU().getId() = " + q.getKurinUPU().getId());
			if (q.getKurinUPU().getId() != null) {
				listOnequery = quarterlyReportsUPUDao.findOnlyDateEachKurin(secondDate, q.getKurinUPU().getId());
				for (QuarterlyReportsUPU qu : listOnequery) {
					quarterlyReportsUPU = new QuarterlyReportsUPU(qu.getId(), qu.getDatebegin(), qu.getDateend(),
							q.getDateApproveKurin(), q.getDateApproveZvyazkovyy(), q.getApproved(), q.getZvyazkovyy(),
							qu.getKurinUPU(), qu.getSamHurtokUPU(), qu.getVporyadnyksamhurtid(), q.getNeed());
				}
				quarterlyReportsUPUDao.updateQuartReportKurin(quarterlyReportsUPU);
			}
		}

	}

	public void updateSmHurtok(String firstDate, String secondDate) {
		listquery = quarterlyReportsUPUDao.findOnlyDatesmHurtok(firstDate);
		for (QuarterlyReportsUPU q : listquery) {
			System.out.println(q.getApproved() + "\t" + q.getId());
			System.out.println("q.getSamHurtokUPU().getId() = " + q.getSamHurtokUPU().getId());
			if (q.getSamHurtokUPU().getId() != null) {
				listOnequery = quarterlyReportsUPUDao.findOnlyDateEachSMHurtok(secondDate, q.getSamHurtokUPU().getId());
				for (QuarterlyReportsUPU qu : listOnequery) {
					quarterlyReportsUPU = new QuarterlyReportsUPU(qu.getId(), qu.getDatebegin(), qu.getDateend(),
							q.getDateApproveKurin(), q.getDateApproveZvyazkovyy(), q.getApproved(), qu.getZvyazkovyy(),
							qu.getKurinUPU(), q.getSamHurtokUPU(), q.getVporyadnyksamhurtid(), q.getNeed());
				}
				quarterlyReportsUPUDao.updateQuartReportKurin(quarterlyReportsUPU);
			}
		}
	}
}
