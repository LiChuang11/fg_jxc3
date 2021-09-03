package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.OverflowList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OverflowListDao {
    int saveOverFlowList(OverflowList overflowList);

    List<OverflowList> getOverflowListGoods(@Param("sTime") String sTime,
                                            @Param("eTime") String eTime);
}
