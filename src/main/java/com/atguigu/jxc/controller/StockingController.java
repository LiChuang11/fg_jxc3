package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.StockingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: rime
 * @Date: 2021/5/15 9:14
 **/
@RestController
public class StockingController {
    @Autowired
    private StockingService stockingService;

    /**
     * 供应商下拉列表查询
     * 请求URL：http://localhost:8080/supplier/getComboboxLis
     * @param query
     * @return
     */
    @PostMapping("/supplier/getComboboxList")
    public List<Supplier> getComboboxList(@RequestParam(required = false) String query) {
        return stockingService.getComboboxList(query);
    }

    /**
     * 进货单保存
     * 请求URL：http://localhost:8080/purchaseListGoods/save?purchaseNumber=JH1605768306735（进货单号前端生成）
     * @param purchaseList
     * @param purchaseListGoodsStr
     * @param session
     * @return
     */
    @PostMapping("/purchaseListGoods/save")
    public ServiceVO savePurchaseListGoods(PurchaseList purchaseList,
                                           @RequestParam String purchaseListGoodsStr,
                                           HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        purchaseList.setUserId(user.getUserId());
        stockingService.savePurchaseListGoods(purchaseList, purchaseListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 退货单保存
     * 请求URL：http://localhost:8080/returnListGoods/save?returnNumber=TH1605768591211（退货单号前端生成
     * @param returnList
     * @param returnListGoodsStr
     * @param session
     * @return
     */
    @PostMapping("/returnListGoods/save")
    public ServiceVO saveReturnListGoods(ReturnList returnList,
                                         @RequestParam String returnListGoodsStr,
                                         HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        returnList.setUserId(user.getUserId());
        stockingService.saveReturnListGoods(returnList, returnListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 进货单列表展示（可条件查询：单据号模糊、供应商、是否付款和日期区间）
     * 请求URL：http://localhost:8080/purchaseListGoods/list
     * @param purchaseNumber
     * @param supplierId
     * @param state
     * @param sTime
     * @param eTime
     * @return
     */
    @PostMapping("/purchaseListGoods/list")
    public Map<String, Object> getPurchaseListGoods(@RequestParam(required = false) String purchaseNumber,
                                                    @RequestParam(required = false) Integer supplierId,
                                                    @RequestParam(required = false) Integer state,
                                                    @RequestParam(required = false) String sTime,
                                                    @RequestParam(required = false) String eTime) {
        List<PurchaseList> purchaseListList = stockingService.getPurchaseListGoods(supplierId, state, purchaseNumber, sTime, eTime);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", purchaseListList);
        return map;
    }

    /**
     * 查询进货单商品信息
     * 请求URL：http://localhost:8080/purchaseListGoods/goodsList
     * @param purchaseListId
     * @return
     */
    @PostMapping("/purchaseListGoods/goodsList")
    public Map<String, Object> getPurchaseListGoodsById(@RequestParam Integer purchaseListId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<PurchaseListGoods> purchaseListGoodsList = stockingService.getPurchaseListGoodsById(purchaseListId);
        hashMap.put("rows", purchaseListGoodsList);
        return hashMap;
    }

    /**
     * 进货单删除
     * 请求URL：http://localhost:8080/purchaseListGoods/delete
     * @param purchaseListId
     * @return
     */
    @PostMapping("/purchaseListGoods/delete")
    public ServiceVO deletePurchaseListGoods(@RequestParam Integer purchaseListId) {
        stockingService.deletePurchaseListGoods(purchaseListId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 退货单列表展示（可条件查询：单据号模糊、供应商、是否付款和日期区间）
     * 请求URL：http://localhost:8080/returnListGoods/list
     * @param returnNumber
     * @param supplierId
     * @param state
     * @param sTime
     * @param eTime
     * @return
     */
    @PostMapping("/returnListGoods/list")
    public Map<String, Object> getReturnListGoods(@RequestParam(required = false) String returnNumber,
                                                    @RequestParam(required = false) Integer supplierId,
                                                    @RequestParam(required = false) Integer state,
                                                    @RequestParam(required = false) String sTime,
                                                    @RequestParam(required = false) String eTime) {
        List<ReturnList> purchaseListList = stockingService.getReturnListGoods(supplierId, state, returnNumber, sTime, eTime);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", purchaseListList);
        return map;
    }

    /**
     * 退货单商品信息
     * 请求URL：http://localhost:8080/returnListGoods/goodsList
     * @param returnListId
     * @return
     */
    @PostMapping("/returnListGoods/goodsList")
    public Map<String, Object> getReturnListGoodsById(@RequestParam Integer returnListId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<ReturnListGoods> returnListGoodsList = stockingService.getReturnListGoodsById(returnListId);
        hashMap.put("rows", returnListGoodsList);
        return hashMap;
    }

    /**
     * 删除退货单
     * 请求URL：http://localhost:8080/returnListGoods/delete
     * @param returnListId
     * @return
     */
    @PostMapping("/returnListGoods/delete")
    public ServiceVO deleteReturnListGoods(@RequestParam Integer returnListId) {
        stockingService.deleteReturnListGoods(returnListId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }
}
