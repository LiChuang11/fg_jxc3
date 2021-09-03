package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.PurchaseListGoods;
import com.atguigu.jxc.service.StatisticalManageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: rime
 * @Date: 2021/5/15 16:10
 **/
@RestController
public class StatisticalManageController {
    @Autowired
    private StatisticalManageService statisticalManageService;

    /**
     * 支付结算（修改进货单付款状态） 2:未支付   1:已支付
     * 请求URL：http://localhost:8080/purchaseListGoods/updateState
     * @param purchaseListId
     * @return
     */
    @PostMapping("/purchaseListGoods/updateState")
    public ServiceVO updatePurchaseListGoodsState(@RequestParam(required = false) Integer purchaseListId) {
        statisticalManageService.updateState(purchaseListId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 支付结算（修改销售单付款状态） 2:未支付   1:已支付
     * 请求URL：http://localhost:8080/saleListGoods/updateState
     * @param saleListId
     * @return
     */
    @PostMapping("/saleListGoods/updateState")
    public ServiceVO updateSaleListGoodsState(@RequestParam(required = false) Integer saleListId) {
        statisticalManageService.updateSaleListGoodsState(saleListId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 进货统计（可根据 商品类别、商品编码或名称 条件查询）
     * 请求URL：http://localhost:8080/purchaseListGoods/count
     * @param sTime
     * @param eTime
     * @param goodsTypeId
     * @param codeOrName
     * @return
     */
    @PostMapping("/purchaseListGoods/count")
    public String purchaseListGoodsCount(String sTime, String eTime,
                                         @RequestParam(required = false) Integer goodsTypeId,
                                         @RequestParam(required = false) String codeOrName) {
//        List<PurchaseListGoods>
        System.out.println("sTime = " + sTime);
        System.out.println("eTime = " + eTime);
        return statisticalManageService.purchaseListGoodsCount(sTime, eTime, goodsTypeId, codeOrName);
    }
}
