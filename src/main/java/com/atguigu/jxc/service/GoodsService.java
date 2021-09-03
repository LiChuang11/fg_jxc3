package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.Goods;
import java.util.List;


public interface GoodsService {

    /**
     * 根据商品编码或者商品类型进行查询
     * @param page
     * @param codeOrName
     * @param goodsTypeId
     * @return
     */
    List<Goods> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId);
}
