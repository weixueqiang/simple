package com.jo.dy.ot.dao;

import com.jo.dy.ot.entity.SysWorkflow;
import com.jo.dy.ot.entity.SysWorkflowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysWorkflowMapper {
    int countByExample(SysWorkflowExample example);

    int deleteByExample(SysWorkflowExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysWorkflow record);

    int insertSelective(SysWorkflow record);

    List<SysWorkflow> selectByExample(SysWorkflowExample example);

    SysWorkflow selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysWorkflow record, @Param("example") SysWorkflowExample example);

    int updateByExample(@Param("record") SysWorkflow record, @Param("example") SysWorkflowExample example);

    int updateByPrimaryKeySelective(SysWorkflow record);

    int updateByPrimaryKey(SysWorkflow record);

	String getProcessKey(String name);

	Integer save(SysWorkflow model);

}