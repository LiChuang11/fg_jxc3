package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.ReturnListGoods;

import java.util.List;

public interface ReturnListGoodsDao {
    void saveReturnListGoods(ReturnListGoods returnListGoods);

    List<ReturnListGoods> getReturnListGoodsById(Integer returnListId);

    void deleteReturnListGoods(Integer returnListId);
}
