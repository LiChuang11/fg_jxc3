package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.CustomerDao;
import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.dao.UnitDao;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.entity.Unit;
import com.atguigu.jxc.service.ManageService;
import com.atguigu.jxc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: rime
 * @Date: 2021/5/13 16:11
 **/
@Service
public class ManageServiceImpl implements ManageService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private GoodsTypeDao goodsTypeDao;
    @Autowired
    private UnitDao unitDao;
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<Customer> getCustomerList(Integer page, Integer rows, String customerName) {
        page = (page - 1) * rows;
        return customerDao.getCustomerList(page, rows, customerName);
    }

    @Override
    public void saveOrUpdate(Customer customer) {
        if (customer.getCustomerId() == null) {
            customerDao.save(customer);
        } else {
            customerDao.update(customer);
        }
    }

    @Override
    public void deleteByIds(String ids) {
        if (!StringUtil.isEmpty(ids)) {
            String[] split = ids.split(",");
            if (split != null && split.length > 0) {
                for (String id : split) {
                    customerDao.deleteById(Integer.valueOf(id));
                }
            }
        }
    }

    @Override
    public Map<String, Object> loadGoodsType() {
        Map<String, Object> map = new HashMap<>();

        List<GoodsType> goodsTypeList = goodsTypeDao.findCustomerList();
        if (!CollectionUtils.isEmpty(goodsTypeList)) {
            for (GoodsType goodsType : goodsTypeList) {
                if (goodsType.getPId() == -1) {
                    addMapProperties(map, goodsType);
                    break;
                }
            }
            List<Map<String, Object>> oneList = new ArrayList<>();

//            List<GoodsType> collect = goodsTypeList.stream().filter(goodsType1 -> goodsType1.getPId() != 1).filter(goodsType1 -> goodsType1.getPId() != -1)
//                    .collect(Collectors.toList());
            List<GoodsType> collect = goodsTypeList.stream().filter(goodsType1 -> goodsType1.getPId() == 1)
                    .collect(Collectors.toList());
            for (GoodsType type : collect) {
                Map<String, Object> oneMap = new HashMap<>();
//                Map<String, Object> oneMap = new HashMap<>();
                Integer goodsTypeId = type.getGoodsTypeId();
                List<GoodsType> twoList = goodsTypeDao.findTwoList(goodsTypeId);
                List<Map<String, Object>> list = new ArrayList<>();

                for (GoodsType goodsType : twoList) {
                    Map<String, Object> twoMap = new HashMap<>();
                    addMapProperties(twoMap, goodsType);
                    list.add(twoMap);
                }
                oneMap.put("children", list);
                addMapProperties(oneMap, type);
                oneList.add(oneMap);
            }

            map.put("children", oneList);
        }
//        Gson gson = new Gson();
        return map;
    }

    private void addMapProperties(Map<String,Object> map, GoodsType goodsType) {
        map.put("id", goodsType.getGoodsTypeId());
        map.put("text", goodsType.getGoodsTypeName());
        map.put("state", goodsType.getGoodsTypeState() == 1?"closed":"open");
        map.put("iconCls", "goods-type");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("state", goodsType.getGoodsTypeState());
        map.put("attributes", hashMap);
    }

    @Override
    public Map<String, Object> getUnitList() {
        Map<String, Object> map = new HashMap<>();
        List<Unit> unitList = unitDao.getUnitList();
        map.put("rows", unitList);
        return map;
    }

    @Override
    public List<Goods> getGoodsList(Integer page, Integer rows, String goodsName, Integer goodsTypeId) {
        page = (page - 1) * rows;
        return goodsDao.getGoodsList(page, rows, goodsName, goodsTypeId);
    }

    @Override
    public void saveGoodsType(String goodsTypeName, Integer pId) {
        Integer state = -1;
        if (pId == 1) {
            state = 1;
            goodsTypeDao.saveGoodsType(goodsTypeName, pId, state);
        } else {
            state = 0;
            goodsTypeDao.saveGoodsType(goodsTypeName, pId, state);
        }
    }

    @Override
    public void deleteGoodsTypeById(Integer goodsTypeId) {
        goodsTypeDao.deleteGoodsTypeById(goodsTypeId);
    }

    @Override
    public void saveGoods(Integer goodsId, Goods goods) {
        if (goodsId == null) {
            // 默认库存为：1000
            goods.setInventoryQuantity(0);
            goods.setState(0);
            int selfId = goodsDao.saveGoods(goods);
            // 存取商品编码
            if (selfId == 1) {
                Integer goodsId1 = goods.getGoodsId();
                String preCode = goodsDao.getPreGoods(goodsId1);
                int intValue = Integer.valueOf(preCode).intValue();
                String str = Integer.valueOf(intValue + 1).toString();
                StringBuilder stringBuilder = new StringBuilder(str);
                int length = stringBuilder.length();
                if (length < 4) {
                    for (int i = 0; i < 4 - length; i++) {
                        stringBuilder.insert(0, '0');
                    }
                }
                Goods goods1 = new Goods();
                goods1.setGoodsId(goodsId1);
                goods1.setGoodsCode(stringBuilder.toString());
                goodsDao.updateById(goods1);
            }
        } else {
//            goodDao.
            goodsDao.updateById(goods);
        }
    }

    @Override
    public boolean deleteGoodsById(Integer goodsId) {
        return goodsDao.deleteGoodsById(goodsId);
    }

    @Override
    public List<Goods> getNoInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        page = (page - 1) * rows;
        return goodsDao.getNoInventoryQuantity(page, rows, nameOrCode);
    }

    @Override
    public List<Goods> getHasInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        page = (page - 1) * rows;
        return goodsDao.getHasInventoryQuantity(page, rows, nameOrCode);
    }

    @Override
    public void saveStock(Integer goodsId, Integer inventoryQuantity, BigDecimal purchasingPrice) {
        goodsDao.saveStock(goodsId, inventoryQuantity, purchasingPrice);
    }

    @Override
    public boolean deleteStockById(Integer goodsId) {
        return goodsDao.deleteStockById(goodsId);
    }

    @Override
    public int getNoDeleteById(Integer goodsId) {
        return goodsDao.getNoDeleteById(goodsId);
    }
}
