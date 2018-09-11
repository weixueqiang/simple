package com.jo.dy.ot.enums;

public enum StatusEnum {

	DELETE(0,"删除"),NORMAL(1,"正常"),DISABLE(2,"不可用"),CANCEL(3,"取消"),APPLY(4,"审核中"),FAIL(5,"失败"),BE_SUBMIT(6,"待提交"),
	SUBMITED(7,"已提交"), ACCPECT(8,"同意");
	
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
