package com.jo.dy.ot.model;

import java.io.Serializable;

public class BasePushParams implements Serializable {

	private static final long serialVersionUID = 1L;
	String action;
	String studentID;
	String paramName;

	public BasePushParams() {
		super();
	}

	public BasePushParams(String action, String studentID, String paramName) {
		super();
		this.action = action;
		this.studentID = studentID;
		this.paramName = paramName;
	}

	public String getAction() {
		if (this.action == null) {
			this.action = "";
		}
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getStudentID() {
		if (this.studentID == null) {
			this.studentID = "";
		}
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getParamName() {
		if (this.paramName == null) {
			this.paramName = "";
		}
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

}