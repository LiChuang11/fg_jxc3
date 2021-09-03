package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.CustomerReturnListGoods;

import java.util.List;

public interface CustomerReturnListGoodsDao {
    void saveCustomerReturnListGoods(CustomerReturnListGoods customerReturnListGoods);

    List<CustomerReturnListGoods> getCustomerReturnListGoods(Integer customerReturnListId);

    void deleteCustomerReturnGoods(Integer customerReturnListId);
}
