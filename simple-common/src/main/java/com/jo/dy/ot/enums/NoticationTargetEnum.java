package com.jo.dy.ot.enums;

public enum NoticationTargetEnum {
	TEACHER("老师"), GUARDIAN("家长");

	private String title;

	private NoticationTargetEnum(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}