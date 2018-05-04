package plast.org.ua.upu.main;

public class MainTest {
	public static void main(String[] args) {
		FindStupinKurin stupinKurin = new FindStupinKurin();
		String ondate = "03.04.2018";
		Long idkurin = (long) 113;
		Long idsmhurtok = null;
		stupinKurin.countReport(ondate, idkurin, idsmhurtok);
	}
}
