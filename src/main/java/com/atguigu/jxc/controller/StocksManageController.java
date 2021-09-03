package com.atguigu.jxc.controller;

import com.atguigu.jxc.dao.UserDao;
import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.StocksManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.interfaces.PBEKey;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Max;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: rime
 * @Date: 2021/5/14 18:12
 **/
@RestController
public class StocksManageController {
    @Autowired
    private StocksManageService stocksManageService;
    @Autowired
    UserDao userDao;

    /**
     * 保存报损单
     * 请求URL：http://localhost:8080/damageListGoods/save?damageNumber=BS1605766644460（报损单号,前端生成）
     * @param damageList
     * @param damageListGoodsStr
     * @return
     */
    @PostMapping("/damageListGoods/save")
    public ServiceVO saveDamageListGoods(DamageList damageList,
                                         @RequestParam(required = false) String damageListGoodsStr,
                                         HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (!StringUtils.isEmpty(user)) {
            damageList.setUserId(user.getUserId());
        } else {
            return new ServiceVO(ErrorCode.NO_TOKEN_CODE, ErrorCode.NO_TOKEN_MESS, null);
        }
        stocksManageService.saveDamageListGoods(damageList, damageListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 新增报溢单
     * 请求URL：http://localhost:8080/overflowListGoods/save?overflowNumber=BY1605767033015（报溢单号）
     * @param overflowList
     * @param overflowListGoodsStr
     * @return
     */
    @PostMapping("/overflowListGoods/save")
    public ServiceVO saveOverflowListGoods(OverflowList overflowList,
                                           @RequestParam String overflowListGoodsStr,
                                           HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (!StringUtils.isEmpty(user)) {
            overflowList.setUserId(user.getUserId());
        } else {
            return new ServiceVO(ErrorCode.NO_TOKEN_CODE, ErrorCode.NO_TOKEN_MESS, null);
        }

        stocksManageService.saveOverflowListGoods(overflowList, overflowListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 查询所有 当前库存量 小于 库存下限的商品信息
     * 请求URL：http://localhost:8080/goods/listAlarm
     * @return
     */
    @PostMapping("/goods/listAlarm")
    public Map<String, Object> getAlarmList() {
        Map<String, Object> map = new HashMap<>();
        List<Goods> goodsList = stocksManageService.getAlarmList();
        map.put("rows", goodsList);
        return map;
    }

    /**
     * 报损单查询
     * 请求URL：http://localhost:8080/damageListGoods/list
     * @param sTime
     * @param eTime
     * @return
     */
    @PostMapping("/damageListGoods/list")
    public Map<String, Object> getDamageListGoods(@RequestParam String  sTime,
                                                  @RequestParam String  eTime) {
        Map<String, Object> map = new HashMap<>();
//        User user = (User) session.getAttribute("currentUser");
        List<DamageList> goodsList = stocksManageService.getDamageListGoods(sTime, eTime);
        if (!CollectionUtils.isEmpty(goodsList)) {
            for (DamageList damageList : goodsList) {
                Integer userId = damageList.getUserId();
                User userById = userDao.getUserById(userId);
                damageList.setTrueName(userById.getTrueName());
            }
        }
        map.put("rows", goodsList);
        return map;
    }

    /**
     * 查询报损单商品信息
     * 请求URL：http://localhost:8080/damageListGoods/goodsList
     * @param damageListId
     * @return
     */
    @PostMapping("/damageListGoods/goodsList")
    public Map<String, Object> getDamageListGoods(@RequestParam Integer damageListId) {
        Map<String, Object> map = new HashMap<>();
        List<DamageListGoods> damageListGoodsList = stocksManageService.getDamageListGoodsById(damageListId);
        map.put("rows", damageListGoodsList);

        return map;
    }

    /**
     * 报溢单查询
     * 请求URL：http://localhost:8080/overflowListGoods/list
     * @param sTime
     * @param eTime
     * @param session
     * @return
     */
    @PostMapping("/overflowListGoods/list")
    public Map<String, Object> getOverflowListGoods(@RequestParam String  sTime,
                                                  @RequestParam String  eTime,
                                                  HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();
        List<OverflowList> overflowLists = stocksManageService.getOverflowListGoods(sTime, eTime);
        if (!CollectionUtils.isEmpty(overflowLists)) {
            map.put("rows", overflowLists);
        }
        return map;
    }

    /**
     * 报溢单商品信息
     * 请求URL：http://localhost:8080/overflowListGoods/goodsList
     * @param overflowListId
     * @return
     */
    @PostMapping("/overflowListGoods/goodsList")
    public Map<String, Object> getOverflowListGoods(@RequestParam Integer overflowListId) {
        Map<String, Object> map = new HashMap<>();
        List<OverflowListGoods> overflowListGoodsList = stocksManageService.getOverflowListGoodsById(overflowListId);
        map.put("rows", overflowListGoodsList);
        return map;
    }
}
