package com.atguigu;

import com.atguigu.jxc.dao.UserDao;
import com.atguigu.jxc.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: rime
 * @Date: 2021/5/17 23:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest// 作用启动spring容器
public class TestAuto {
    @Autowired
    private UserDao userDao;
    @Autowired
    Customer customer;

    @Test
    public void test() {
        System.out.println("customer = " + customer);
        System.out.println("userDao = " + userDao);
    }
}
