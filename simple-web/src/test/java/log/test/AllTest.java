package log.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.jo.dy.ot.util.SHA1;

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
	
	@Test
	public void test() {
		int index=16 >> 4;
		System.out.println(index);//1
		
		System.out.println(Byte.MIN_VALUE);//
		System.out.println(Byte.MAX_VALUE);//0111 1111
		
		System.out.println(Integer.toBinaryString(Byte.MIN_VALUE));
		System.out.println(Integer.toBinaryString(Byte.MAX_VALUE));
	}

	@Test
	public void digestTest() {
		String[] passwords= {"zs123","ls123","ww123"};
		String salt="salt";
		for(String pw:passwords) {
			System.out.println(SHA1.digest(pw+salt));
		}
		
		
	}
	
	
}
