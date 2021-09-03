package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SupplierDao;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import com.atguigu.jxc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: rime
 * @Date: 2021/5/13 15:51
 **/
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierDao supplierDao;

    @Override
    public List<Supplier> getSupplierList(Integer page, Integer rows, String supplierName) {
        page = (page - 1) * rows;
        List<Supplier> supplierList = supplierDao.getSupplierList(page, rows, supplierName);
        return supplierList;
    }

    @Override
    public void saveSupplier(Supplier supplier) {
        if (supplier.getSupplierId() == null) {
            // 保存操作
            supplierDao.saveSupplier(supplier);
        } else {
            // 更新操作
            supplierDao.updateSupplierById(supplier);
        }
    }

    @Override
    public void deleteByIds(String ids) {
        if (!StringUtil.isEmpty(ids)) {
//            String[] split = ids.split(",");
//            if (split != null && split.length > 0) {
//                for (String s : split) {
//                    supplierDao.deleteById(Integer.parseInt(s));
//                }
//            }
            // 第二种
            List<Integer> idList = new ArrayList<>();
            String[] split = ids.split(",");
            if (split != null && split.length > 0) {
                for (String s : split) {
                    idList.add(Integer.parseInt(s));
                }
                supplierDao.deleteByIds(idList);
            }

        }
    }
}
