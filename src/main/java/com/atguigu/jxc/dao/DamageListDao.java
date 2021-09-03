package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DamageListDao {
    int saveDamageList(DamageList damageList);

    List<DamageList> getDamageListGoods(@Param("sTime") String sTime,
                                        @Param("eTime") String eTime);
}
