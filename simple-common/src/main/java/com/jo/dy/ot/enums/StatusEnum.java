package com.jo.dy.ot.enums;

public enum StatusEnum {

	DELETE(0,"删除"),NORMAL(1,"正常"),DISABLE(2,"不可用");
	
	private int index;
	private String desc;
	private StatusEnum(int index, String desc) {
		this.index = index;
		this.desc = desc;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	
	
	
}
