package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsDao {

    List<Goods> listInventory(@Param("page") Integer page,
                              @Param("rows") Integer rows,
                              @Param("codeOrName") String codeOrName,
                              @Param("goodsTypeId") Integer goodsTypeId);

    List<Goods> getGoodsList(@Param("page") Integer page,
                             @Param("rows") Integer rows,
                             @Param("goodsName") String goodsName,
                             @Param("goodsTypeId") Integer goodsTypeId);

    int saveGoods(Goods goods);

    void updateById(Goods goods);

    boolean deleteGoodsById(Integer goodsId);

    List<Goods> getNoInventoryQuantity(@Param("page") Integer page,
                                       @Param("rows") Integer rows,
                                       @Param("nameOrCode") String nameOrCode);

    List<Goods> getHasInventoryQuantity(@Param("page") Integer page,
                                        @Param("rows") Integer rows,
                                        @Param("nameOrCode") String nameOrCode);

    void saveStock(@Param("goodsId") Integer goodsId,
                   @Param("inventoryQuantity") Integer inventoryQuantity,
                   @Param("purchasingPrice") BigDecimal purchasingPrice);

    boolean deleteStockById(Integer goodsId);

    int getNoDeleteById(Integer goodsId);

    String getPreGoods(Integer id);

    List<Goods> getAlarmList();

}
