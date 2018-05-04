package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class LastDayInMonth {
	public static void main(String[] args) {
		//DateTime.DaysInMonth(int year, int month);
		int ANY_MONTH = 1;
		int ANY_YEAR = 2018;
		  Calendar cal = Calendar.getInstance();
		    cal.set(Calendar.MONTH, ANY_MONTH);
		    cal.set(Calendar.YEAR, ANY_YEAR);
		    cal.set(Calendar.DAY_OF_MONTH, 1);// This is necessary to get proper results
		    System.out.println(cal.getActualMaximum(Calendar.DATE));
		   
	}
}
