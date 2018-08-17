package com.jo.dy.ot.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

public class IntegerConverter implements Converter<String, Integer>{

	@Override
	public Integer convert(String str) {
		if(StringUtils.isEmpty(str)) {
			return 0;
		}
		return Integer.valueOf(str);
	}


}
