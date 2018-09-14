package com.jo.dy.ot.dao;

import com.jo.dy.ot.entity.SysWorkflowStep;
import com.jo.dy.ot.entity.SysWorkflowStepExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysWorkflowStepMapper {
    int countByExample(SysWorkflowStepExample example);

    int deleteByExample(SysWorkflowStepExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysWorkflowStep record);

    int insertSelective(SysWorkflowStep record);

    List<SysWorkflowStep> selectByExample(SysWorkflowStepExample example);

    SysWorkflowStep selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysWorkflowStep record, @Param("example") SysWorkflowStepExample example);

    int updateByExample(@Param("record") SysWorkflowStep record, @Param("example") SysWorkflowStepExample example);

    int updateByPrimaryKeySelective(SysWorkflowStep record);

    int updateByPrimaryKey(SysWorkflowStep record);

	void batchCreate(List<SysWorkflowStep> sysWorkflowSteps);
}