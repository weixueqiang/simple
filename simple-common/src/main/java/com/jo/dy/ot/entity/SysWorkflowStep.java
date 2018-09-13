package com.jo.dy.ot.entity;

import java.util.Date;

public class SysWorkflowStep {
    private Long id;

    private Long workflowId;

    private String roleId;

    private String usersId;

    private String assignss;

    private Integer type;

    private Date createTime;

    private Integer seq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId == null ? null : usersId.trim();
    }

    public String getAssignss() {
        return assignss;
    }

    public void setAssignss(String assignss) {
        this.assignss = assignss == null ? null : assignss.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }
}