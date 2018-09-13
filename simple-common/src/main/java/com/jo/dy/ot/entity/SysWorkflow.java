package com.jo.dy.ot.entity;

import java.util.Date;

public class SysWorkflow {
    private Long id;

    private String name;

    private String content;

    private String processKey;

    private Date createTime;

    private String customerId;

    private String proDefId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey == null ? null : processKey.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getProDefId() {
        return proDefId;
    }

    public void setProDefId(String proDefId) {
        this.proDefId = proDefId == null ? null : proDefId.trim();
    }
}