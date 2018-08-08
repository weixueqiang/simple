package com.jo.dy.ot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.jo.dy.ot.enums.NoticationTargetEnum;
import com.jo.dy.ot.enums.NoticeChannelEnum;


public class PushModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private Set<String> receiverSet = new HashSet<String>();// 接受对象
	private String title;
	private String content;
	private NoticeChannelEnum channel;// 通知渠道方式
	private BasePushParams basePushParams;// 自定义类型
	private NoticationTargetEnum target;
	private Date effectDate;

	public NoticationTargetEnum getTarget() {
		return target;
	}

	public void setTarget(NoticationTargetEnum target) {
		this.target = target;
	}

	public BasePushParams getBasePushParams() {
		return basePushParams;
	}

	public void setBasePushParams(BasePushParams basePushParams) {
		this.basePushParams = basePushParams;
	}

	public NoticeChannelEnum getChannel() {
		return channel;
	}

	public void setChannel(NoticeChannelEnum channel) {
		this.channel = channel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void addReceiver(String receiver) {
		if (receiver == null)
			return;
		this.receiverSet.add(receiver);
	}

	public String getReceivers() {
		if (receiverSet != null && !receiverSet.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (String receiver : receiverSet) {
				sb.append(",").append(receiver);
			}
			return sb.toString().substring(1);
		}
		return null;
	}

	public Set<String> getReceiverSet() {
		return receiverSet;
	}

	public void setReceiverSet(Set<String> receiverSet) {
		this.receiverSet = receiverSet;
	}

	public Date getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

}