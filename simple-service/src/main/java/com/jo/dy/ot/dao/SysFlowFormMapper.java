package com.jo.dy.ot.dao;

import com.jo.dy.ot.entity.SysFlowForm;
import com.jo.dy.ot.entity.SysFlowFormExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysFlowFormMapper {
    int countByExample(SysFlowFormExample example);

    int deleteByExample(SysFlowFormExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysFlowForm record);

    int insertSelective(SysFlowForm record);

    List<SysFlowForm> selectByExample(SysFlowFormExample example);

    SysFlowForm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysFlowForm record, @Param("example") SysFlowFormExample example);

    int updateByExample(@Param("record") SysFlowForm record, @Param("example") SysFlowFormExample example);

    int updateByPrimaryKeySelective(SysFlowForm record);

    int updateByPrimaryKey(SysFlowForm record);
}