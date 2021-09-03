package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: rime
 * @Date: 2021/5/13 15:52
 **/
@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    /**
     * 分页查询供应商
     * @param page
     * @param rows
     * @param supplierName
     * @return
     */
    @PostMapping("/supplier/list")
    @ResponseBody
    public Map<String, Object> getSupplierList(@RequestParam Integer page,
                                               @RequestParam Integer rows,
                                               @RequestParam(required = false) String supplierName) {
        Map<String, Object> map = new HashMap<>();
        List<Supplier> supplierList = supplierService.getSupplierList(page, rows, supplierName);
        if (CollectionUtils.isEmpty(supplierList)) return map;
        map.put("total", supplierList.size());
        map.put("rows", supplierList);
        return map;
    }

    /**
     * 供应商添加或修改  http://localhost:8080/supplier/save?supplierId=1
     * @return
     */
    @PostMapping("/supplier/save")
    @ResponseBody
    public ServiceVO saveSupplier(Supplier supplier) {
//        response.setContentType("text/html;charset=UTF-8");
        supplierService.saveSupplier(supplier);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

    /**
     * 删除供应商（支持批量删除）http://localhost:8080/supplier/delete   ids: 1,4,6
     * @param ids
     * @return
     */
    @PostMapping("/supplier/delete")
    @ResponseBody
    public ServiceVO deleteByIds(@RequestParam String ids) {
        supplierService.deleteByIds(ids);
        return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, null);
    }

}
