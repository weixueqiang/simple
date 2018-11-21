package com.jo.dy.ot.util;

import java.util.Iterator;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.PrimitiveTypeWrapper;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class FieldsPlugin extends PluginAdapter {
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		this.addfields(topLevelClass, introspectedTable, "fields");
		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}

	public boolean sqlMapBaseColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		XmlElement isNullElement = new XmlElement("if");
		isNullElement.addAttribute(new Attribute("test", "fields == null"));
		Iterator var4 = element.getElements().iterator();

		while (var4.hasNext()) {
			Element e = (Element) var4.next();
			isNullElement.addElement(e);
		}

		element.getElements().clear();
		element.addElement(isNullElement);
		XmlElement isNotNullElement = new XmlElement("if");
		isNotNullElement.addAttribute(new Attribute("test", "fields != null"));
		isNotNullElement.addElement(new TextElement("${fields}"));
		element.addElement(isNotNullElement);
		return super.sqlMapBaseColumnListElementGenerated(element, introspectedTable);
	}

	private void addfields(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
		CommentGenerator commentGenerator = this.context.getCommentGenerator();
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(PrimitiveTypeWrapper.getStringInstance());
		field.setName(name);
		commentGenerator.addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(PrimitiveTypeWrapper.getStringInstance(), name));
		method.addBodyLine("this." + name + "=" + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(PrimitiveTypeWrapper.getStringInstance());
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
	}

	public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		List<Element> elements = element.getElements();
		StringBuilder columns = new StringBuilder();
		List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
		Iterator var6 = allColumns.iterator();

		while (var6.hasNext()) {
			IntrospectedColumn introspectedColumn = (IntrospectedColumn) var6.next();
			columns.append(",").append(introspectedColumn.getActualColumnName());
		}

		columns.deleteCharAt(0);
		elements.set(1, new TextElement(columns.toString()));
		return super.sqlMapSelectByPrimaryKeyElementGenerated(element, introspectedTable);
	}

	public boolean validate(List<String> warnings) {
		return true;
	}
}