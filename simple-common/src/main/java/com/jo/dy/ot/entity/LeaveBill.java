package com.jo.dy.ot.entity;

import java.io.Serializable;

public class LeaveBill implements Serializable{
	
	private static final long serialVersionUID = -17580148566986199L;

	private Integer id;

    private Integer userId;

    private String reason;

    private String status;

    private Integer dayTime;
    
    

    @Override
	public String toString() {
		return "LeaveBill [id=" + id + ", userId=" + userId + ", reason=" + reason + ", status=" + status + ", dayTime="
				+ dayTime + "]";
	}

	public LeaveBill() {
		super();
	}

	public LeaveBill(Integer userId, String reason, String status, Integer dayTime) {
		super();
		this.userId = userId;
		this.reason = reason;
		this.status = status;
		this.dayTime = dayTime;
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
}