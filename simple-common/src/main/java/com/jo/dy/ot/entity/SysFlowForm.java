package com.jo.dy.ot.entity;

public class SysFlowForm {
    private Integer id;

    private String serviceName;

    private String customeId;

    private Long workflowId;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getCustomeId() {
        return customeId;
    }

    public void setCustomeId(String customeId) {
        this.customeId = customeId == null ? null : customeId.trim();
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}