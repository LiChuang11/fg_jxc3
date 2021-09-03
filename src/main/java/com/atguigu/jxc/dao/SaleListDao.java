package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.SaleList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleListDao {
    void saveSaleList(SaleList saleList);

    List<SaleList> getSaleListGoods(@Param("saleNumber") String saleNumber,
                                    @Param("customerId") Integer customerId,
                                    @Param("state") Integer state,
                                    @Param("sTime") String sTime,
                                    @Param("eTime") String eTime);

    void deleteSale(Integer saleListId);

    void updateSaleListGoodsState(Integer saleListId);
}
