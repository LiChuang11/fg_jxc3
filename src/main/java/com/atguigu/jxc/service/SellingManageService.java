package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.*;

import java.util.List;

public interface SellingManageService {

    List<Customer> getComboboxList(String query);

    void saveSaleListGoods(SaleList saleList, String saleListGoodsStr);

    void saveCustomerReturnListGoods(CustomerReturnList customerReturnList, String customerReturnListGoodsStr);

    List<SaleList> getSaleListGoods(String saleNumber, Integer customerId, Integer state, String sTime, String eTime);

    List<SaleListGoods> getSaleListGoodsById(Integer saleListId);

    void deleteSaleList(Integer saleListId);

    List<CustomerReturnList> getCustomerReturnList(String returnNumber, Integer customerId, Integer state, String sTime, String eTime);

    List<CustomerReturnListGoods> getCustomerReturnListGoods(Integer customerReturnListId);

    void deleteCustomerReturnById(Integer customerReturnListId);
}
