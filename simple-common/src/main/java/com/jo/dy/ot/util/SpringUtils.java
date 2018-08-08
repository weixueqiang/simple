package com.jo.dy.ot.util;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring 工具类
 * 
 * @author
 * @since 1.0
 */
@Component
public class SpringUtils implements ApplicationContextAware {

	/**
	 * ApplicationContext
	 */
	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		// LOG.info("Version: "+version);
		SpringUtils.applicationContext = applicationContext;
	}

	/**
	 * @return 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 * 
	 * @param name
	 *            Bean的名称
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> clazz) {
		checkApplicationContext();
		return (T) applicationContext.getBean(clazz);
	}
	
	
	/**
	 * 清除applicationContext静态变量.
	 */
	public static void cleanApplicationContext() {
		applicationContext = null;
	}

	/**
	 * 检查是否applicaitonContext未注入
	 */
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringUtils");
		}
	}
}