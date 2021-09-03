package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.PurchaseList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseListDao {
    void savePurchaseList(PurchaseList purchaseList);

    List<PurchaseList> getPurchaseListGoods(@Param("supplierId") Integer supplierId,
                                            @Param("state") Integer state,
                                            @Param("purchaseNumber") String purchaseNumber,
                                            @Param("sTime") String sTime,
                                            @Param("eTime") String eTime);

    void deletePurchaseList(Integer purchaseListId);

    void updateState(Integer purchaseListId);
}
