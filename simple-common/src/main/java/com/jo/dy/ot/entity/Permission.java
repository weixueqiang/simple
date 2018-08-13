package com.jo.dy.ot.entity;

import java.io.Serializable;
import java.util.Date;

public class Permission implements Serializable{
    private Integer id;

    private String name;

    private String code;

    private String status;

    private Date createTime;

    public Permission(Integer id, String name, String code, String status, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.status = status;
		this.createTime = createTime;
	}

	public Permission(Integer id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public Permission() {
		super();
		  // TODO Auto-generated constructor stub
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}