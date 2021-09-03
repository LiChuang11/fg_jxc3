package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierDao {
    List<Supplier> getSupplierList(@Param("page") Integer page,
                                   @Param("rows") Integer rows,
                                   @Param("supplierName") String supplierName);

    void saveSupplier(Supplier supplier);

    void updateSupplierById(Supplier supplier);

    void deleteById(Integer id);

    void deleteByIds(@Param("ids") List<Integer> ids);

    List<Supplier> getComboboxList(String query);

    String getSupplierName(Integer supplierId);
}
