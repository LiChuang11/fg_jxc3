package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.entity.GoodsType;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CustomerDao {

    List<Customer> getCustomerList(@RequestParam("page") Integer page,
                                   @RequestParam("rows") Integer rows,
                                   @RequestParam("customerName") String customerName);

    void save(Customer customer);

    void update(Customer customer);

    void deleteById(Integer id);


    List<Customer> getComboboxList(String query);

    Customer getCustomerName(Integer customerId);
}
