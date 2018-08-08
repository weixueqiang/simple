package com.jo.dy.ot.enums;


public enum NoticeChannelEnum {

	ALIYUN_NOTICATION("通知"), ALIYUN_MESSAGE("消息"), SMS("短信"), EMAIL("邮件");

	private String name;

	// 构造方法
	private NoticeChannelEnum(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}