package com.jo.dy.ot.entity;

import java.io.Serializable;
import java.util.Date;

import com.jo.ot.dy.ot.mybatis.Id;

public class Permission implements Serializable {
	/**
	 * @date 2018年10月24日 上午10:38:38
	 * @author weixueqiang
	 */
	private static final long serialVersionUID = -3008112954832829242L;

	@Id
	private String id;

	private String name;

	private String code;

	private String status;

	private Date createTime;

	public String getId() {
		return id;
	}

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Permission(String id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public Permission(String id, String name, String code, String status, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.status = status;
		this.createTime = createTime;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
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