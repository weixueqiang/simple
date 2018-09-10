package com.jo.dy.ot.entity;

import java.io.Serializable;

public class LeaveBill implements Serializable{
    private Integer id;

    private Integer userId;

    private String reason;

    private String status;

    private Integer dayTime;

    private String proDefId;

    
    
    public LeaveBill(Integer id, Integer userId, String reason, String status, Integer dayTime, String proDefId) {
		super();
		this.id = id;
		this.userId = userId;
		this.reason = reason;
		this.status = status;
		this.dayTime = dayTime;
		this.proDefId = proDefId;
	}

	public LeaveBill() {
		super();
	}

	@Override
	public String toString() {
		return "LeaveBill [id=" + id + ", userId=" + userId + ", reason=" + reason + ", status=" + status + ", dayTime="
				+ dayTime + ", proDefId=" + proDefId + "]";
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getDayTime() {
        return dayTime;
    }

    public void setDayTime(Integer dayTime) {
        this.dayTime = dayTime;
    }

    public String getProDefId() {
        return proDefId;
    }

    public void setProDefId(String proDefId) {
        this.proDefId = proDefId == null ? null : proDefId.trim();
    }
}