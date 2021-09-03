package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.ReturnList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReturnListDao {
    void saveReturnList(ReturnList returnList);

    List<ReturnList> getReturnListGoods(@Param("supplierId") Integer supplierId,
                                        @Param("state") Integer state,
                                        @Param("returnNumber") String returnNumber,
                                        @Param("sTime") String sTime,
                                        @Param("eTime") String eTime);

    void deleteReturnList(Integer returnListId);
}
