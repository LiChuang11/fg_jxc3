package com.atguigu.jxc.service;

public interface StatisticalManageService {
    void updateState(Integer purchaseListId);

    void updateSaleListGoodsState(Integer saleListId);

    String purchaseListGoodsCount(String sTime, String eTime, Integer goodsTypeId, String codeOrName);
}
