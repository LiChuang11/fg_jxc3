package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.PurchaseListDao;
import com.atguigu.jxc.dao.PurchaseListGoodsDao;
import com.atguigu.jxc.dao.SaleListDao;
import com.atguigu.jxc.dao.SupplierDao;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.PurchaseListGoods;
import com.atguigu.jxc.service.StatisticalManageService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: rime
 * @Date: 2021/5/15 16:11
 **/
@Service
public class StatisticalManageServiceImpl implements StatisticalManageService {
    @Autowired
    private PurchaseListDao purchaseListDao;

    @Autowired
    private PurchaseListGoodsDao purchaseListGoodsDao;

    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private SaleListDao saleListDao;

    @Override
    public void updateState(Integer purchaseListId) {
        purchaseListDao.updateState(purchaseListId);
    }

    @Override
    public void updateSaleListGoodsState(Integer saleListId) {
        saleListDao.updateSaleListGoodsState(saleListId);
    }

    @Override
    public String purchaseListGoodsCount(String sTime, String eTime, Integer goodsTypeId, String codeOrName) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList();

        List<PurchaseList> purchaseLists = purchaseListDao.getPurchaseListGoods(null, null, null, sTime, eTime);
        for (PurchaseList purchaseList : purchaseLists) {
            PurchaseListGoods purchaseListGoods = purchaseListGoodsDao.getPurchaseListGoodsCount(purchaseList.getPurchaseListId(), goodsTypeId, codeOrName);
            if (StringUtils.isEmpty(purchaseListGoods)) continue;
            String supplierName = supplierDao.getSupplierName(purchaseList.getSupplierId());
            map.put("number", purchaseList.getPurchaseNumber());
            map.put("date", purchaseList.getPurchaseDate());
            map.put("supplierName", supplierName);
            map.put("code", purchaseListGoods.getGoodsCode());
            map.put("name", purchaseListGoods.getGoodsName());
            map.put("model", purchaseListGoods.getGoodsModel());
            map.put("goodsType", purchaseListGoods.getGoodsTypeId());
            map.put("unit", purchaseListGoods.getGoodsUnit());
            map.put("price", purchaseListGoods.getPrice());
            map.put("num", purchaseListGoods.getGoodsNum());
            map.put("total", purchaseListGoods.getTotal());
            list.add(map);
        }
        return new Gson().toJson(list);
    }
}
