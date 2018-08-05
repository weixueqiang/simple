package com.jo.dy.ot.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;


public class ObjectConver {

	
	private static final Logger log=Logger.getLogger(ObjectConver.class);
	
	@SuppressWarnings("unchecked")
	public static <T> T conver(Object source, Class<T> target) {
		if (source == null || target == null) {
			return null;
		}
		try {
			ClassLoader loader = ClassLoader.getSystemClassLoader();
			Class<?> loadClass = loader.loadClass(target.getName());
			T t = (T) loadClass.newInstance();
			BeanUtils.copyProperties(source, t);
			return t;
		} catch (Exception e) {
			log.error("转换出错！");
		}
		return null;
	}

	public static <T> List<T> converList(List list,Class<T> target){
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<T> ts=new ArrayList<T>();
		for(Object source : list) {
			ts.add(conver(source, target));
		}
		return ts;
	}



}
