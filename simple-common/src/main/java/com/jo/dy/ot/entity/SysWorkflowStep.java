package com.jo.dy.ot.entity;

import java.io.Serializable;
import java.util.Date;

public class SysWorkflowStep implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date createTime;
    private Long workflowId;
    //该步骤审核的角色
    private String roleId;
    //指定的组人员ids,以逗号分割
    private String usersId;
    //普通流转办理人
    private String assignss;
    /**
     * type==1 会签
     * type==2 或签 
     * type==3 普通流转
     */
    private Integer type;

    
    
    public String getUsersId() {
		return usersId;
	}

	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}

	public String getAssignss() {
		return assignss;
	}

	public void setAssignss(String assignss) {
		this.assignss = assignss;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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



    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
    
    
}