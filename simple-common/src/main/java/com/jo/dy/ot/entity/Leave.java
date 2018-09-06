package com.jo.dy.ot.entity;

import java.io.Serializable;

public class Leave implements Serializable{
    private Integer id;

    private Integer userId;

    private String reason;

    private String status;

    private Integer dayTime;
    
    

    public Leave() {
		super();
	}

	public Leave(Integer id, Integer userId, String reason, String status, Integer dayTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.reason = reason;
		this.status = status;
		this.dayTime = dayTime;
	}

	public Leave( Integer userId, String reason, String status, Integer dayTime) {
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