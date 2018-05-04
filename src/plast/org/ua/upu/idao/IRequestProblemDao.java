package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.table.RequestProblem;

public interface IRequestProblemDao {
	public void addProblem(RequestProblem problem);
	public List<RequestProblem> findAllProblem();
}
