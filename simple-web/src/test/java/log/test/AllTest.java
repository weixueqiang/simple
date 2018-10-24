package log.test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.jo.dy.ot.util.SHA1;

public class AllTest {

	@Test
	public void readTitle() {
		System.out.println(1);
		File file = new File("D://b.sql");
		System.out.println(2);
		System.out.println(file);
		String readFileToString;
		try {
			readFileToString = FileUtils.readFileToString(file, "gbk");
			readFileToString = readFileToString.toUpperCase();
			FileUtils.writeStringToFile(new File("D://c.sql"), readFileToString, "gbk");
			System.out.println(readFileToString);
			System.out.println(3);
		} catch (IOException e) {
			System.out.println(4);
			//
			e.printStackTrace();
		}
	}

	@Test
	public void time() {
		Date date = new Date();
		date.setTime(1539619200000L);
		System.out.println(date);
		System.out.println("O_s_aIJklV6hTCCHnJu-_j0t5HVqVeYViszP7qqmG1J7OMBNIGci9jUmGnOXZSsv".length());

	}

	@Test
	public void annoClassTest() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int day = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println(day);
	}

	@Test
	public void annoClassTest1() throws ParseException {
		String beginTime = "2018-08-08";
		String endTime = "2018-09-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = sdf.parse(beginTime);
		Date end = sdf.parse(endTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		List<Date> list = new ArrayList<>();
		while (true) {
			list.add(cal.getTime());
			cal.add(Calendar.DAY_OF_YEAR, 1);
			if (cal.getTime().compareTo(end) > 0) {
				break;
			}
		}
		for (Date d : list) {
			System.out.println(sdf.format(d));
		}
	}

	@Test
	public void timeTest() {
		Calendar cal = Calendar.getInstance();
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
		int index = 16 >> 4;
		System.out.println(index);// 1

		System.out.println(Byte.MIN_VALUE);//
		System.out.println(Byte.MAX_VALUE);// 0111 1111

		System.out.println(Integer.toBinaryString(Byte.MIN_VALUE));
		System.out.println(Integer.toBinaryString(Byte.MAX_VALUE));
	}

	@Test
	public void digestTest() {
		String[] passwords = { "zs123", "ls123", "ww123" };
		String salt = "salt";
		for (String pw : passwords) {
			System.out.println(SHA1.digest(pw + salt));
		}
	}

	@Test
	public void arrTest() {
		List<Object> list = new ArrayList<>();
		String s = null;
		list.add(s);
		String ss = (String) list.get(0);
		System.out.println(StringUtils.isNotBlank(ss));
	}

	@Test
	public void serializableUtilsTest() {

	}

}
