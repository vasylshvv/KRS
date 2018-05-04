package plast.org.ua.upu.main;

public class MainUpdateQu {
	public static void main(String[] args) {
		
		String firstDate = "2017-09-01";
		String secondDate = "2018-03-01";
		UpdateQueryReport uor = new  UpdateQueryReport();
		uor.updateKurin(firstDate, secondDate);
		uor.updateSmHurtok(firstDate, secondDate);
		
	}
}
