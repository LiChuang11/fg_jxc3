package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.OverflowListGoods;

import java.util.List;

public interface OverflowListGoodsDao {
    void saveOverflowListGoods(OverflowListGoods overflowListGoods);

    List<OverflowListGoods> getOverflowListGoodsById(Integer overflowListId);
}
