package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getSupplierList(Integer page, Integer rows, String supplierName);

    void saveSupplier(Supplier supplier);

    void deleteByIds(String ids);
}
