package log.test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.jo.dy.ot.util.SHA1;

public class AllTest {

	@Test
	public void BigDecimalToString() {
		BigDecimal bigDecimal = new BigDecimal(1.100);
		double doubleValue = bigDecimal.doubleValue();
		System.out.println(doubleValue);
		String num = doubleValue + "";
		while (num.endsWith("0")) {
			num = num.substring(0, num.length() - 1);
		}
		if (num.endsWith(".")) {
			num = num.substring(0, num.length() - 1);
		}
		System.out.println(num);
	}

	@Test
	public void assertTest() {
		String id = null;
		assert StringUtils.isBlank(id) : "测试报错!";
		assert StringUtils.isNotBlank(id) : "测试报错!";
	}

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
	public void lower() {
		String s = "SELECT ESG.ID,ESG.SCHOOL_ID,ESG.STATUS,ESG.GRADE,ESG.GRADE_ID\r\n"
				+ "  		FROM EDU_SCHOOL_GRADE ESG \r\n"
				+ "  		LEFT JOIN EDU_GRADE_TEACHER EGT ON EGT.GRADE_ID = ESG.ID \r\n"
				+ "				AND  EGT.TYPE in(' ') AND EGT.STATUS='NORMAL'\r\n"
				+ "		INNER JOIN EDU_GRADE EG ON EG.ID = ESG.GRADE_ID  \r\n" + "	WHERE ESG.`STATUS` = 'NORMAL'\r\n"
				+ "	GROUP BY ESG.GRADE_ID";
		System.out.println(s.toLowerCase());

	}

	@Test
	public void toUp() {
		String s = "CREATE TABLE `biz_examination_score` (\r\n" + "  `id` varchar(24) NOT NULL COMMENT '考试成绩标识',\r\n"
				+ "  `examination_record_id` varchar(24) NOT NULL COMMENT '考试记录标识',\r\n"
				+ "  `school_id` varchar(24) NOT NULL COMMENT '学校标识',\r\n"
				+ "  `class_id` varchar(24) NOT NULL COMMENT '班级标识',\r\n"
				+ "  `student_id` varchar(24) NOT NULL COMMENT '学生标识',\r\n"
				+ "  `score` decimal(5,1) DEFAULT NULL COMMENT '分数',\r\n"
				+ "  `create_time` datetime DEFAULT NULL COMMENT '创建时间',\r\n"
				+ "  `update_time` datetime DEFAULT NULL COMMENT '更新时间',\r\n"
				+ "  `creator` varchar(24) DEFAULT NULL COMMENT '创建者',\r\n"
				+ "  `status` char(1) DEFAULT NULL COMMENT '状态',\r\n" + "  PRIMARY KEY (`id`)\r\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试成绩';\r\n" + "\r\n" + "";
		System.out.println(s.toUpperCase());
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
		// Function<List<String>, Stream<String>> fun=ArrayList::stream;
		Function<List<String>, Stream<String>> fun = s -> s.stream();
	}

}
