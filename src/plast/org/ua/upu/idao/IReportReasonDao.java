package plast.org.ua.upu.idao;

import java.util.List;


import plast.org.ua.upu.table.ReportReason;

public interface IReportReasonDao {
	public void saveReason(ReportReason reportReason);
	public List<ReportReason> findReason(Long idQuarterlyReport);
	
}
