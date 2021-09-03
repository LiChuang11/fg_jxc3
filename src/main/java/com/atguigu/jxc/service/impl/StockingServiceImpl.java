package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.*;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.StockingService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: rime
 * @Date: 2021/5/15 9:15
 **/
@Service
public class StockingServiceImpl implements StockingService {
    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private PurchaseListDao purchaseListDao;

    @Autowired
    private PurchaseListGoodsDao purchaseListGoodsDao;

    @Autowired
    private ReturnListDao returnListDao;

    @Autowired
    private ReturnListGoodsDao returnListGoodsDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Supplier> getComboboxList(String query) {
        return supplierDao.getComboboxList(query);
    }

    @Override
    public void savePurchaseListGoods(PurchaseList purchaseList, String purchaseListGoodsStr) {
        purchaseListDao.savePurchaseList(purchaseList);
        // t_purchase_list_goods 同时对这张表进行保存
        Gson gson = new Gson();
        List<PurchaseListGoods> purchaseListGoodsList = gson.fromJson(purchaseListGoodsStr, new TypeToken<List<PurchaseListGoods>>() {
        }.getType());
        for (PurchaseListGoods purchaseListGoods : purchaseListGoodsList) {
            purchaseListGoods.setPurchaseListId(purchaseList.getPurchaseListId());
            purchaseListGoodsDao.savePurchaseListGoods(purchaseListGoods);
        }
    }

    @Override
    public void saveReturnListGoods(ReturnList returnList, String returnListGoodsStr) {
        returnListDao.saveReturnList(returnList);

        Gson gson = new Gson();
        List<ReturnListGoods> returnListGoodsList = gson.fromJson(returnListGoodsStr, new TypeToken<List<ReturnListGoods>>() {
        }.getType());
        for (ReturnListGoods returnListGoods : returnListGoodsList) {
            returnListGoods.setReturnListId(returnList.getReturnListId());
            returnListGoodsDao.saveReturnListGoods(returnListGoods);
        }
    }

    @Override
    public List<PurchaseList> getPurchaseListGoods(Integer supplierId, Integer state, String purchaseNumber, String sTime, String eTime) {
//        supplierDao.getSupplierId(purchaseNumber);
        List<PurchaseList> purchaseLists = purchaseListDao.getPurchaseListGoods(supplierId, state, purchaseNumber, sTime, eTime);
        for (PurchaseList purchaseList : purchaseLists) {
            // 封装trueName
            Integer userId = purchaseList.getUserId();
            User userById = userDao.getUserById(userId);
            purchaseList.setTrueName(userById.getTrueName());
            // 封装supplierName
            String supplierName = supplierDao.getSupplierName(purchaseList.getSupplierId());
            purchaseList.setSupplierName(supplierName);
        }
        return purchaseLists;
    }

    @Override
    public List<PurchaseListGoods> getPurchaseListGoodsById(Integer purchaseListId) {
        return purchaseListGoodsDao.getPurchaseListGoodsById(purchaseListId);
    }

    @Override
    public void deletePurchaseListGoods(Integer purchaseListId) {
        purchaseListGoodsDao.deletePurchaseListGoods(purchaseListId);
        purchaseListDao.deletePurchaseList(purchaseListId);
    }

    @Override
    public List<ReturnList> getReturnListGoods(Integer supplierId, Integer state, String returnNumber, String sTime, String eTime) {
        List<ReturnList> returnLists = returnListDao.getReturnListGoods(supplierId, state, returnNumber, sTime, eTime);
        for (ReturnList returnList : returnLists) {
            // 封装trueName
            Integer userId = returnList.getUserId();
            User userById = userDao.getUserById(userId);
            returnList.setTrueName(userById.getTrueName());
            // 封装supplierName
            String supplierName = supplierDao.getSupplierName(returnList.getSupplierId());
            returnList.setSupplierName(supplierName);
        }
        return returnLists;
    }

    @Override
    public List<ReturnListGoods> getReturnListGoodsById(Integer returnListId) {
        return returnListGoodsDao.getReturnListGoodsById(returnListId);
    }

    @Override
    public void deleteReturnListGoods(Integer returnListId) {
        returnListGoodsDao.deleteReturnListGoods(returnListId);
        returnListDao.deleteReturnList(returnListId);
    }
}
