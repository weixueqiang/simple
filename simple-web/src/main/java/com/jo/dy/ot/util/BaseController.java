package com.jo.dy.ot.util;


import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {

//	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new IntegerFormatter());
	}
	
	
}

class IntegerFormatter extends PropertyEditorSupport{

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.isBlank(text)) {
			text="0";
		}
		setValue(Integer.parseInt(text));
	}
	@Override
	public String getAsText() {
		return getValue().toString();
	}
	
}

