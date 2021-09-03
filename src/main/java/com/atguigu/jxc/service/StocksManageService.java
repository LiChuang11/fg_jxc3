package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.*;

import java.util.List;

public interface StocksManageService {
    void saveDamageListGoods(DamageList damageList, String damageListGoodsStr);

    void saveOverflowListGoods(OverflowList overflowList, String overflowListGoodsStr);

    List<Goods> getAlarmList();

    List<DamageList> getDamageListGoods(String sTime, String eTime);

    List<DamageListGoods> getDamageListGoodsById(Integer damageListId);

    List<OverflowList> getOverflowListGoods(String sTime, String eTime);

    List<OverflowListGoods> getOverflowListGoodsById(Integer overflowListId);
}
