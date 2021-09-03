package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.SellingManageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: rime
 * @Date: 2021/5/15 14:18
 **/
@RestController
public class SellingManageController {
    @Autowired
    private SellingManageService sellingManageService;

    /**
     * 客户下拉列表
     * 请求URL：http://localhost:8080/customer/getComboboxList
     *
     * @param q
     * @return
     */
    @PostMapping("/customer/getComboboxList")
    public List<Customer> getComboboxList(@RequestParam(value = "query", required = false) String q) {
        return sellingManageService.getComboboxList(q);
    }

    /**
     * 销售单保存
     * 请求URL：http://localhost:8080/saleListGoods/save?saleNumber=XS1605772263999 （销售单号）
     *
     * @param saleList
     * @param saleListGoodsStr
     * @return
     */
    @PostMapping("/saleListGoods/save")
    public ServiceVO saveSaleListGoods(SaleList saleList, HttpSession session,
                                       @RequestParam String saleListGoodsStr) {
        saleList.setUserId(getUserId(session));
        sellingManageService.saveSaleListGoods(saleList, saleListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 客户退货单保存
     * 请求URL：http://localhost:8080/customerReturnListGoods/save?returnNumber=XT1605772786495（退货单号）
     *
     * @param customerReturnList
     * @param session
     * @param customerReturnListGoodsStr
     * @return
     */
    @PostMapping("/customerReturnListGoods/save")
    public ServiceVO saveCustomerReturnListGoods(CustomerReturnList customerReturnList, HttpSession session,
                                                 @RequestParam String customerReturnListGoodsStr) {
        customerReturnList.setUserId(getUserId(session));
        sellingManageService.saveCustomerReturnListGoods(customerReturnList, customerReturnListGoodsStr);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 销售单查询（可查询条件：销售单号、客户、付款状态）
     * 请求URL：http://localhost:8080/saleListGoods/list
     * @param saleNumber
     * @param customerId
     * @param state
     * @param sTime
     * @param eTime
     * @return
     */
    @PostMapping("/saleListGoods/list")
    public Map<String, Object> getSaleListGoods(@RequestParam(required = false) String saleNumber,
                                                @RequestParam(required = false) Integer customerId,
                                                @RequestParam(required = false) Integer state,
                                                @RequestParam(required = false) String sTime,
                                                @RequestParam(required = false) String eTime) {
        Map<String, Object> map = new HashMap<>();
//        String saleNumber = (String) request.getAttribute("saleNumber");
//        String sTime = (String) request.getAttribute("sTime");
//        String eTime = (String) request.getAttribute("eTime");
//        Integer customerId = (Integer) request.getAttribute("customerId");
//        Integer state = (Integer) request.getAttribute("state");
        List<SaleList> saleLists = sellingManageService.getSaleListGoods(saleNumber, customerId, state, sTime, eTime);
        map.put("rows", saleLists);
        return map;
    }

    /**
     * 销售单商品信息
     * 请求URL：http://localhost:8080/saleListGoods/goodsList
     *
     * @param saleListId
     * @return
     */
    @PostMapping("/saleListGoods/goodsList")
    public Map<String, Object> getSaleListGoods(@RequestParam(required = false) Integer saleListId) {
        Map<String, Object> map = new HashMap<>();
        List<SaleListGoods> saleListGoodsList = sellingManageService.getSaleListGoodsById(saleListId);
        map.put("rows", saleListGoodsList);
        return map;
    }

    /**
     * 删除销售单
     * 请求URL：http://localhost:8080/saleListGoods/delete
     *
     * @param saleListId
     * @return
     */
    @PostMapping("/saleListGoods/delete")
    public ServiceVO deleteSaleList(@RequestParam(required = false) Integer saleListId) {
        sellingManageService.deleteSaleList(saleListId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 客户退货单查询（可查询条件：退货单号、客户、退款状态）
     * 请求URL：http://localhost:8080/customerReturnListGoods/list
     * @param returnNumber
     * @param customerId
     * @param state
     * @param sTime
     * @param eTime
     * @return
     */
    @PostMapping("/customerReturnListGoods/list")
    public Map<String, Object> getCustomerReturnList(@RequestParam(required = false) String returnNumber,
                                                     @RequestParam(required = false) Integer customerId,
                                                     @RequestParam(required = false) Integer state,
                                                     @RequestParam(required = false) String sTime,
                                                     @RequestParam(required = false) String eTime) {
        Map<String, Object> map = new HashMap<>();
//        String returnNumber = (String) request.getAttribute("returnNumber");
//        String sTime = (String) request.getAttribute("sTime");
//        String eTime = (String) request.getAttribute("eTime");
//        Integer customerId = (Integer) request.getAttribute("customerId");
//        Integer state = (Integer) request.getAttribute("state");
        List<CustomerReturnList> saleLists = sellingManageService.getCustomerReturnList(returnNumber, customerId, state, sTime, eTime);
        map.put("rows", saleLists);
        return map;
    }

    /**
     * 退货单商品信息
     * 请求URL：http://localhost:8080/customerReturnListGoods/goodsList
     * @param customerReturnListId
     * @return
     */
    @PostMapping("/customerReturnListGoods/goodsList")
    public Map<String,Object> getCustomerReturnListGoods(@RequestParam(required = false) Integer customerReturnListId) {
        List<CustomerReturnListGoods> customerReturnListGoodsList = sellingManageService.getCustomerReturnListGoods(customerReturnListId);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("rows", customerReturnListGoodsList);
        return hashMap;
    }

    @PostMapping("/customerReturnListGoods/delete")
    public ServiceVO deleteCustomerReturnById(@RequestParam(required = false) Integer customerReturnListId) {
        sellingManageService.deleteCustomerReturnById(customerReturnListId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }


    private int getUserId(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        return user.getUserId();


    }
}
