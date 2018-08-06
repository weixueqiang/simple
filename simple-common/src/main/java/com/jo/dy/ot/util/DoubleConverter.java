package com.jo.dy.ot.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class DoubleConverter implements Converter<String, Double>{

	
	@Override
	public Double convert(String str) {
		if(StringUtils.isEmpty(str)) {
			return 0D;
		}
		return Double.valueOf(str);
	}


}
