package com.jo.dy.ot.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.StringUtils;

/**
 * 自定义BeanUtils
 * 主要是用于在局部使用自定义转化时间类型的转换器
 * 
 * @author mengyango
 * @version 1.0
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
	
//	private static final Logger logger = Logger.getLogger(BeanUtils.class);
	
	private static final DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static BeanUtilsBean beanUtilsBean;
	public static ConvertUtilsBean convertUtilsBean;
	
	static{
		convertUtilsBean = new ConvertUtilsBean();
		convertUtilsBean.register(new Converter(){

			public Object convert(Class arg0, Object arg1) {
				
				String p = (String)arg1;

				if(p== null || p.trim().length()==0){
					return null;
				}
				p = p.trim();
				try{
					if(p.length() == 10){
						return df1.parse(p);
					}else if(p.length() == 16){
						return df2.parse(p);
					}else if(p.length() == 19){
						return df3.parse(p);
					}else{
						return null;
					}
				}catch(Exception e){
					//logger.error("把"+arg1+"转换成时间类型出错", e);
					return null;
				}
				
			}
			
		}, Date.class);
		
		convertUtilsBean.register(new Converter() {
			
			public Object convert(Class arg0, Object arg1) {
				
				BigDecimalConverter converter = new BigDecimalConverter();
				
				if(StringUtils.isEmpty(arg1.toString())){
					return converter.convert(BigDecimal.class, arg1);
				}else{
					return new BigDecimal(0);
				}
				
			}
		}, BigDecimal.class);
		
		beanUtilsBean = new BeanUtilsBean(convertUtilsBean, new PropertyUtilsBean());
	}
	

	public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
	
	public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
}
