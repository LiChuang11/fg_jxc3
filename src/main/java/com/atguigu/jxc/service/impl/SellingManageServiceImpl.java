package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.*;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.SellingManageService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: rime
 * @Date: 2021/5/15 14:19
 **/
@Service
public class SellingManageServiceImpl implements SellingManageService {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private SaleListDao salelistDao;

    @Autowired
    private SaleListGoodsDao saleListGoodsDao;

    @Autowired
    private CustomerReturnListDao customerReturnListDao;

    @Autowired
    private CustomerReturnListGoodsDao customerReturnListGoodsDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Customer> getComboboxList(String query) {
        return customerDao.getComboboxList(query);
    }

    @Override
    public void saveSaleListGoods(SaleList saleList, String saleListGoodsStr) {
        salelistDao.saveSaleList(saleList);
        saleList.getSaleListId();
        Gson gson = new Gson();
        List<SaleListGoods> saleListGoodsList = gson.fromJson(saleListGoodsStr, new TypeToken<SaleListGoods>() {
        }.getType());
        for (SaleListGoods saleListGoods : saleListGoodsList) {
            saleListGoods.setSaleListId(saleList.getSaleListId());
            saleListGoodsDao.saveSaleListGoods(saleListGoods);
        }
    }

    @Override
    public void saveCustomerReturnListGoods(CustomerReturnList customerReturnList, String customerReturnListGoodsStr) {
        customerReturnListDao.saveCustomerReturnList(customerReturnList);

        List<CustomerReturnListGoods> customerReturnListGoodsList = new Gson().fromJson(customerReturnListGoodsStr, new TypeToken<List<CustomerReturnListGoods>>() {
        }.getType());
        for (CustomerReturnListGoods customerReturnListGoods : customerReturnListGoodsList) {
            customerReturnListGoods.setCustomerReturnListId(customerReturnList.getCustomerReturnListId());
            customerReturnListGoodsDao.saveCustomerReturnListGoods(customerReturnListGoods);
        }
    }

    @Override
    public List<SaleList> getSaleListGoods(String saleNumber, Integer customerId, Integer state, String sTime, String eTime) {
        List<SaleList> saleLists = salelistDao.getSaleListGoods(saleNumber, customerId, state, sTime, eTime);
        for (SaleList saleList : saleLists) {
            Customer customer = customerDao.getCustomerName(saleList.getCustomerId());
            saleList.setCustomerName(customer.getCustomerName());
            User user = userDao.getUserById(saleList.getUserId());
            saleList.setTrueName(user.getTrueName());
        }
        return saleLists;
    }

    @Override
    public List<SaleListGoods> getSaleListGoodsById(Integer saleListId) {
        return saleListGoodsDao.getSaleListGoodsById(saleListId);
    }

    @Override
    public void deleteSaleList(Integer saleListId) {
        saleListGoodsDao.deleteSaleGoods(saleListId);
        salelistDao.deleteSale(saleListId);
    }

    @Override
    public List<CustomerReturnList> getCustomerReturnList(String returnNumber, Integer customerId, Integer state, String sTime, String eTime) {

        List<CustomerReturnList> customerReturnLists = customerReturnListDao.getSaleListGoods(returnNumber, customerId, state, sTime, eTime);
        for (CustomerReturnList customerReturnList : customerReturnLists) {
            Customer customer = customerDao.getCustomerName(customerReturnList.getCustomerId());
            customerReturnList.setCustomerName(customer.getCustomerName());
            User user = userDao.getUserById(customerReturnList.getUserId());
            customerReturnList.setTrueName(user.getTrueName());
        }
        return customerReturnLists;
    }

    @Override
    public List<CustomerReturnListGoods> getCustomerReturnListGoods(Integer customerReturnListId) {
        return customerReturnListGoodsDao.getCustomerReturnListGoods(customerReturnListId);
    }

    @Override
    public void deleteCustomerReturnById(Integer customerReturnListId) {
        customerReturnListGoodsDao.deleteCustomerReturnGoods(customerReturnListId);
        customerReturnListDao.deleteCustomerReturn(customerReturnListId);
    }
}
