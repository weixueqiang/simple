package com.jo.dy.ot.entity;

import java.io.Serializable;
import java.util.Date;

public class SysWorkflowStep implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date createTime;
    private Long workflowId;
    //该步骤审核的角色
    private String rolePkno;
    /**
     * type==1  会签
     * type==2  普通流转
     */
    private Integer type;

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

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public String getRolePkno() {
        return rolePkno;
    }

    public void setRolePkno(String rolePkno) {
        this.rolePkno = rolePkno;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
    
}