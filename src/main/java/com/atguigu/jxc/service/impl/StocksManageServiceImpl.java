package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.*;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.StocksManageService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: rime
 * @Date: 2021/5/14 18:20
 **/
@Service
public class StocksManageServiceImpl implements StocksManageService {

    @Autowired
    private DamageListDao damageListDao;
    @Autowired
    private DamageListGoodsDao damageListGoodsDao;
    @Autowired
    private OverflowListDao overflowListDao;
    @Autowired
    private OverflowListGoodsDao overflowListGoodsDao;
    @Autowired
    private GoodsDao goodsDao;


    @Override
    public void saveDamageListGoods(DamageList damageList, String damageListGoodsStr) {
        // 对商品报损单进行保存
        int flag = damageListDao.saveDamageList(damageList);
        if (flag == 1) {
            Integer damageListId = damageList.getDamageListId();
            // 对商品报损单商品的列表进行保存
            Gson gson = new Gson();
//            DamageListGoods damageListGoods = gson.fromJson(damageListGoodsStr, DamageListGoods.class);
//            DamageListGoods damageListGoods = new DamageListGoods();
            List<DamageListGoods> listGoods = gson.fromJson(damageListGoodsStr, new TypeToken<List<DamageListGoods>>() {
            }.getType());
            for (DamageListGoods damageListGoods : listGoods) {
                damageListGoods.setDamageListId(damageListId);
                damageListGoodsDao.saveDamageListGoods(damageListGoods);
            }

//            DamageListGoods damageListGoods = new DamageListGoods();
//            BeanUtils.copyProperties(goods, damageListGoods);
//            damageListGoods.setDamageListId(damageListId);
//            damageListGoods.setTotal(damageListGoods.getPrice() * damageListGoods.getGoodsNum());
//            damageListGoodsDao.saveDamageListGoods(damageListGoods);
        }
    }

    @Override
    public void saveOverflowListGoods(OverflowList overflowList, String overflowListGoodsStr) {
        int flag = overflowListDao.saveOverFlowList(overflowList);
        if (flag == 1) {
            Integer overflowListId = overflowList.getOverflowListId();

            Gson gson = new Gson();
            List<OverflowListGoods> overflowListGoodsList = gson.fromJson(overflowListGoodsStr, new TypeToken<List<OverflowListGoods>>() {
            }.getType());
            for (OverflowListGoods overflowListGoods : overflowListGoodsList) {
                overflowListGoods.setOverflowListId(overflowListId);
                overflowListGoodsDao.saveOverflowListGoods(overflowListGoods);
            }
//            OverflowListGoods overflowListGoods = new OverflowListGoods();
//            BeanUtils.copyProperties(goods, overflowListGoods);
//            overflowListGoods.setOverflowListId(overflowListId);
////            overflowListGoods.setTotal(overflowListGoods.getPrice() * overflowListGoods.getGoodsNum());
//            overflowListGoodsDao.saveOverflowListGoods(overflowListGoods);
        }
    }

    @Override
    public List<Goods> getAlarmList() {
        return goodsDao.getAlarmList();
    }

    @Override
    public List<DamageList> getDamageListGoods(String sTime, String eTime) {
        return damageListDao.getDamageListGoods(sTime, eTime);
    }

    @Override
    public List<DamageListGoods> getDamageListGoodsById(Integer damageListId) {
        return damageListGoodsDao.getDamageListGoodsById(damageListId);
    }

    @Override
    public List<OverflowList> getOverflowListGoods(String sTime, String eTime) {
        return overflowListDao.getOverflowListGoods(sTime, eTime);
    }

    @Override
    public List<OverflowListGoods> getOverflowListGoodsById(Integer overflowListId) {
        return overflowListGoodsDao.getOverflowListGoodsById(overflowListId);
    }
}
