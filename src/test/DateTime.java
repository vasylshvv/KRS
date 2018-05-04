package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTime {
	public static void main(String[] args) {
		String monthStart;
		String monthEnd;
		for (int i = 0, j = i + 3;  i < 12; i=i+3, j=j+3) {
			if((i+1 >= 0 && i+1 < 10 ) ){
				monthStart = "0"+(i+1);
				//monthEnd = "0"+j;
			} else {
				monthStart = String.valueOf(i);
				//monthEnd = String.valueOf(j);
			}
			System.out.println(monthStart+"\t"+(j));
			
		}
		
	}
}
