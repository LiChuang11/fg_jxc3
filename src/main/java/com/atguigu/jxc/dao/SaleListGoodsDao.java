package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.SaleListGoods;

import java.util.List;

public interface SaleListGoodsDao {
    void saveSaleListGoods(SaleListGoods saleListGoods);

    List<SaleListGoods> getSaleListGoodsById(Integer saleListId);

    void deleteSaleGoods(Integer saleListId);
}
