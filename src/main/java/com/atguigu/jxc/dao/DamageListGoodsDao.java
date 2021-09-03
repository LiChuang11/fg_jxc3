package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageListGoods;

import java.util.List;

public interface DamageListGoodsDao {
    void saveDamageListGoods(DamageListGoods damageListGoods);

    List<DamageListGoods> getDamageListGoodsById(Integer damageListId);
}
