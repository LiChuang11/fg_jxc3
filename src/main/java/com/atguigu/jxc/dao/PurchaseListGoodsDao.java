package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.PurchaseListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseListGoodsDao {
    void savePurchaseListGoods(PurchaseListGoods purchaseListGoods);

    List<PurchaseListGoods> getPurchaseListGoodsById(Integer purchaseListId);

    void deletePurchaseListGoods(Integer purchaseListId);

    PurchaseListGoods getPurchaseListGoodsCount(@Param("purchaseListId") Integer purchaseListId,
                                                @Param("goodsTypeId") Integer goodsTypeId,
                                                @Param("codeOrName") String codeOrName);
}
