package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsTypeDao {
//    Map<String, Object> loadGoodsType();

    List<GoodsType> findTwoList(Integer goodsTypeId);

    List<GoodsType> findCustomerList();

    void saveGoodsType(@Param("goodsTypeName") String goodsTypeName,
                       @Param("pId") Integer pId,
                       @Param("state") Integer state);

    void deleteGoodsTypeById(Integer goodsTypeId);
}
