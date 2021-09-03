package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.ManageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.PushBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: rime
 * @Date: 2021/5/13 15:58
 **/
@RestController
public class ManageController {
    @Autowired
    private ManageService manageService;

    private HashMap<String, Object> getMap() {
        return new HashMap<>();
    }

    /**
     * 客户列表分页（名称模糊查询）   http://localhost:8080 /customer/list
     * @param page
     * @param rows
     * @param customerName
     * @return
     */
    @PostMapping("/customer/list")
//    @ResponseBody
    public Map<String,Object> customerList(@RequestParam Integer page,
                                           @RequestParam Integer rows,
                                           @RequestParam(required = false) String  customerName) {
        Map<String, Object> map = getMap();
        List<Customer> list = manageService.getCustomerList(page, rows, customerName);
        map.put("rows", list);
        map.put("total", list.size());
        return map;
    }

    /**
     * 客户添加或修改
     * 请求URL：http://localhost:8080/ customer/save?customerId=1
     * @param customer
     * @return
     */
    @PostMapping("/customer/save")
//    @ResponseBody
    public ServiceVO saveOrUpdate(Customer customer) {
        manageService.saveOrUpdate(customer);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 客户删除（支持批量删除）
     * 请求URL：http://localhost:8080/customer/delete
     * @param ids
     * @return
     */
    @PostMapping("/customer/delete")
//    @ResponseBody
    public ServiceVO deleteByIds(@RequestParam String ids) {
        manageService.deleteByIds(ids);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    // TODO

    /**
     * 查询商品所有分类
     * 请求URL：http://localhost:8080/goodsType/loadGoodsType
     * @return
     */
    @PostMapping("/goodsType/loadGoodsType")
//    @ResponseBody
    public List<Map<String, Object>> getLoadGoodsType() {
        Map<String, Object> map = manageService.loadGoodsType();
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
//        Gson gson = new Gson();
        return list;
    }

    /**
     * 查询所有商品单位
     * 请求URL：http://localhost:8080/unit/list
     * @return
     */
    @PostMapping("/unit/list")
//    @ResponseBody
    public Map<String, Object> getUnitList() {
        return manageService.getUnitList();
    }

    /**
     * 查询所有商品信息（可家具家电及诶诶你们的额死你们以根据分类、名称查询）
     * 请求URL：http://localhost:8080/goods/list
     * @param page
     * @param rows
     * @param goodsName
     * @param goodsTypeId
     * @return
     */
    @PostMapping("/goods/list")
//    @ResponseBody
    public Map<String, Object> getGoodsList(@RequestParam Integer page,
                                            @RequestParam Integer rows,
                                            @RequestParam(required = false) String goodsName,
                                            @RequestParam(required = false) Integer goodsTypeId) {
        HashMap<String, Object> map = getMap();
        List<Goods> goods = manageService.getGoodsList(page, rows, goodsName, goodsTypeId);
        map.put("rows", goods);
        map.put("total", goods.size());
        return map;
    }

    /**
     * 新增分类
     * 请求URL：http://localhost:8080/goodsType/save
     * @param goodsTypeName
     * @param pId
     * @return
     */
    @PostMapping("/goodsType/save")
//    @ResponseBody
    public ServiceVO saveGoodsType(@RequestParam(required = false) String  goodsTypeName,
                                   @RequestParam Integer  pId) {
        manageService.saveGoodsType(goodsTypeName, pId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 删除分类
     * 请求URL：http://localhost:8080/goodsType/delete
     * @param goodsTypeId
     * @return
     */
    @PostMapping("/goodsType/delete")
//    @ResponseBody
    public ServiceVO deleteById(@RequestParam Integer  goodsTypeId) {
        manageService.deleteGoodsTypeById(goodsTypeId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 商品添加或修改
     * 请求URL：http://localhost:8080/goods/save?goodsId=37
     * @param goods
     * @return
     */
    @PostMapping("/goods/save")
//    @ResponseBody
    public ServiceVO saveGoods(@RequestParam(required = false) Integer goodsId, Goods goods) {
        manageService.saveGoods(goodsId, goods);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 商品删除（要判断商品状态,入库、有进货和销售单据的不能删除）
     * 请求URL：http://localhost:8080/goods/delete
     * @param goodsId
     * @return
     */
    @PostMapping("/goods/delete")
//    @ResponseBody
    public ServiceVO deleteGoodsById(@RequestParam Integer goodsId) {
        boolean flag = manageService.deleteGoodsById(goodsId);
        if (flag) {
            // 删除成功
            return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
        } else {
            // 删除失败
            int number = manageService.getNoDeleteById(goodsId);
            if (number == 1) {
                // 已经入库
                return new ServiceVO(ErrorCode.STORED_ERROR_CODE, ErrorCode.STORED_ERROR_MESS, null );
            } else {
                // 有进货或者销售单据
                return new ServiceVO(ErrorCode.HAS_FORM_ERROR_CODE, ErrorCode.HAS_FORM_ERROR_MESS, null );
            }
        }
    }

    /**
     * 无库存商品列表展示（可以根据商品名称或编码查询）
     * 请求URL：http://localhost:8080/goods/getNoInventoryQuantity
     * @param page
     * @param rows
     * @param nameOrCode
     * @return
     */
    @PostMapping("/goods/getNoInventoryQuantity")
    public Map<String, Object> getNoInventoryQuantity(@RequestParam Integer page,
                                                      @RequestParam Integer rows,
                                                      @RequestParam(required = false) String nameOrCode) {
        Map<String, Object> map = getMap();
        List<Goods> goodsList = manageService.getNoInventoryQuantity(page, rows, nameOrCode);
        map.put("rows", goodsList);
        map.put("total", goodsList.size());
        return map;
    }

    /**
     * 有库存商品列表展示（可以根据商品名称或编码查询）
     * 请求URL：http://localhost:8080/goods/getHasInventoryQuantity
     * @param page
     * @param rows
     * @param nameOrCode
     * @return
     */
    @PostMapping("/goods/getHasInventoryQuantity")
//    @ResponseBody
    public Map<String, Object> getHasInventoryQuantity(@RequestParam Integer page,
                                                      @RequestParam Integer rows,
                                                      @RequestParam(required = false) String nameOrCode) {
        Map<String, Object> map = getMap();
        List<Goods> goodsList = manageService.getHasInventoryQuantity(page, rows, nameOrCode);
        map.put("rows", goodsList);
        map.put("total", goodsList.size());
        return map;
    }

    /**
     * 添加库存、修改数量或成本价
     * 请求URL：http://localhost:8080/goods/saveStock?goodsId=25
     * @param goodsId
     * @param inventoryQuantity
     * @param purchasingPrice
     * @return
     */
    @PostMapping("/goods/saveStock")
//    @ResponseBody
    public ServiceVO saveStock(@RequestParam Integer goodsId,
                               @RequestParam Integer inventoryQuantity,
                               @RequestParam double purchasingPrice) {
        manageService.saveStock(goodsId, inventoryQuantity, BigDecimal.valueOf(purchasingPrice));
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 删除库存（要判断商品状态 入库、有进货和销售单据的不能删除）
     * 请求URL：http://localhost:8080/goods/deleteStock
     * @param goodsId
     * @return
     */
    @PostMapping("/goods/deleteStock")
//    @ResponseBody
    public ServiceVO deleteStockById(@RequestParam Integer goodsId) {
        boolean flag = manageService.deleteStockById(goodsId);
        if (!flag) {
            // 删除失败
            int number = manageService.getNoDeleteById(goodsId);
            if (number == 1) {
                // 已经入库
                return new ServiceVO(ErrorCode.STORED_ERROR_CODE, ErrorCode.STORED_ERROR_MESS, null );
            } else {
                // 有进货或者销售单据
                return new ServiceVO(ErrorCode.HAS_FORM_ERROR_CODE, ErrorCode.HAS_FORM_ERROR_MESS, null );
            }

        } else {
            return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
        }
    }
}
