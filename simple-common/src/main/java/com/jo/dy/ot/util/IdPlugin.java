package com.jo.dy.ot.util;

import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class IdPlugin implements CommentGenerator {

	@Override
	public void addConfigurationProperties(Properties properties) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if ("id".equals(field.getName())) {
			field.addAnnotation("@com.jo.ot.dy.ot.mybatis.Id");
		}
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {

	}

	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {

	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addComment(XmlElement xmlElement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addRootComment(XmlElement rootElement) {
		// TODO Auto-generated method stub

	}

}
