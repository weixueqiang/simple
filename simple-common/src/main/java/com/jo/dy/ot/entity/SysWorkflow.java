package com.jo.dy.ot.entity;

import java.io.Serializable;
import java.util.Date;




public class SysWorkflow implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Long id;
    private Date createTime;
    //工作流名称
    private String name;
    //工作流描述
    private String content;
    private String key;
    private Integer skipLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSkipLevel() {
        return skipLevel;
    }

    public void setSkipLevel(Integer skipLevel) {
        this.skipLevel = skipLevel;
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
    
    
}