package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import org.apache.catalina.LifecycleState;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ManageService {

    List<Customer> getCustomerList(Integer page, Integer rows, String customerName);

    void saveOrUpdate(Customer customer);

    void deleteByIds(String ids);

    Map<String, Object> loadGoodsType();

    Map<String, Object> getUnitList();

    List<Goods> getGoodsList(Integer page, Integer rows, String goodsName, Integer goodsTypeId);

    void saveGoodsType(String goodsTypeName, Integer pId);

    void deleteGoodsTypeById(Integer goodsTypeId);

    void saveGoods(Integer goodsId, Goods goods);

    boolean deleteGoodsById(Integer goodsId);

    List<Goods> getNoInventoryQuantity(Integer page, Integer rows, String nameOrCode);

    List<Goods> getHasInventoryQuantity(Integer page, Integer rows, String nameOrCode);

    void saveStock(Integer goodsId, Integer inventoryQuantity, BigDecimal purchasingPrice);

    boolean deleteStockById(Integer goodsId);

    int getNoDeleteById(Integer goodsId);
}
