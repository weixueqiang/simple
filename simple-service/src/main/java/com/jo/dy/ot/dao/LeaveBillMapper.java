package com.jo.dy.ot.dao;

import com.jo.dy.ot.entity.LeaveBill;
import com.jo.dy.ot.entity.LeaveBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LeaveBillMapper {
    int countByExample(LeaveBillExample example);

    int deleteByExample(LeaveBillExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LeaveBill record);

    int insertSelective(LeaveBill record);

    List<LeaveBill> selectByExample(LeaveBillExample example);

    LeaveBill selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LeaveBill record, @Param("example") LeaveBillExample example);

    int updateByExample(@Param("record") LeaveBill record, @Param("example") LeaveBillExample example);

    int updateByPrimaryKeySelective(LeaveBill record);

    int updateByPrimaryKey(LeaveBill record);
}