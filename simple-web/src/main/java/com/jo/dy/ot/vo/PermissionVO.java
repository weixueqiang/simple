package com.jo.dy.ot.vo;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;

public class PermissionVO implements Serializable{

	 	private Integer id;
	 	
	 	@NotBlank(message="名称不能为空！")
	    private String name;
	 	@NotBlank(message="code不能为空！")
	    private String code;

	    private String status;

	    private Date createTime;

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
