package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.*;

import java.util.List;

public interface StockingService {
    List<Supplier> getComboboxList(String query);

    void savePurchaseListGoods(PurchaseList purchaseList, String purchaseListGoodsStr);

    void saveReturnListGoods(ReturnList returnList, String returnListGoodsStr);

    List<PurchaseList> getPurchaseListGoods(Integer supplierId, Integer state, String purchaseNumber, String sTime, String eTime);

    List<PurchaseListGoods> getPurchaseListGoodsById(Integer purchaseListId);

    void deletePurchaseListGoods(Integer purchaseListId);

    List<ReturnList> getReturnListGoods(Integer supplierId, Integer state, String returnNumber, String sTime, String eTime);

    List<ReturnListGoods> getReturnListGoodsById(Integer returnListId);

    void deleteReturnListGoods(Integer returnListId);
}
