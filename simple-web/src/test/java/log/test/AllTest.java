package log.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class AllTest {

	@Test
	public void annoClassTest() {
		Calendar cal=Calendar.getInstance();
    	cal.setTime(new Date());
    	int day = cal.get(Calendar.DAY_OF_WEEK);
    	System.out.println(day);
	}
	
	@Test
	public void timeTest() {
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, 2100);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
    	System.out.println(cal.getTime().getTime());
    	cal.set(Calendar.YEAR, 2000);
    	cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
    	System.out.println(cal.getTime().getTime());
	}
	
	
}
