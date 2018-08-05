package com.jo.dy.ot.util;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date>{

	private static final String[] FORMATS= {"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm","yyyy-MM-dd"};
	
	@Override
	public Date convert(String arg0) {
		arg0=arg0.trim();
		try {
			return getLongDate(Long.valueOf(arg0));
		} catch (Exception e) {
			for(String format:FORMATS) {
				Date date = getStringDate(arg0,format);
				if(date!=null) {
					return date;
				}
			}
		}
		return null;
	}

	private Date getStringDate(String arg0,String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(arg0);
		} catch (Exception e) {
			return null;
		}
	}

	private Date getLongDate(Long time) {
		Date date = new Date();
		date.setTime(time);
  		return date;
	}

}
