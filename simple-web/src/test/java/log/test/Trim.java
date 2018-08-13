package log.test;

public class Trim {

	public static void main(String[] args) {
		String s="### set log levels ###\r\n" + 
				"log4j.rootLogger = debug ,  stdout ,  D ,  E\r\n" + 
				"\r\n" + 
				"### \\u8F93\\u51FA\\u5230\\u63A7\\u5236\\u53F0 ###\r\n" + 
				"log4j.appender.stdout = org.apache.log4j.ConsoleAppender\r\n" + 
				"log4j.appender.stdout.Target = System.out\r\n" + 
				"log4j.appender.stdout.layout = org.apache.log4j.PatternLayout\r\n" + 
				"log4j.appender.stdout.layout.ConversionPattern =  %d{ABSOLUTE} %5p %c{ 1 }:%L - %m%n\r\n" + 
				"\r\n" + 
				"### \\u8F93\\u51FA\\u5230\\u65E5\\u5FD7\\u6587\\u4EF6 ###\r\n" + 
				"log4j.appender.D = org.apache.log4j.DailyRollingFileAppender\r\n" + 
				"log4j.appender.D.File = logs/log.log\r\n" + 
				"log4j.appender.D.Append = true\r\n" + 
				"log4j.appender.D.Threshold = DEBUG \r\n" + 
				"log4j.appender.D.layout = org.apache.log4j.PatternLayout\r\n" + 
				"log4j.appender.D.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n\r\n" + 
				"\r\n" + 
				"### \\u4FDD\\u5B58\\u5F02\\u5E38\\u4FE1\\u606F\\u5230\\u5355\\u72EC\\u6587\\u4EF6 ###\r\n" + 
				"log4j.appender.D = org.apache.log4j.DailyRollingFileAppender\r\n" + 
				"log4j.appender.D.File = logs/error.log \r\n" + 
				"log4j.appender.D.Append = true\r\n" + 
				"log4j.appender.D.Threshold = ERROR \r\n" + 
				"log4j.appender.D.layout = org.apache.log4j.PatternLayout\r\n" + 
				"log4j.appender.D.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n";
		s=s.replaceAll(" ", "");
		System.out.println(s);
	}
	
}
