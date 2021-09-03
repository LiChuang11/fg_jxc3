package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: rime
 * @Date: 2021/5/13 10:49
 **/
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;


    @Override
    public List<Goods> listInventory(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        page = (page - 1) * rows;
        List<Goods> goods = goodsDao.listInventory(page, rows, codeOrName, goodsTypeId);
        return goods;
    }
}
