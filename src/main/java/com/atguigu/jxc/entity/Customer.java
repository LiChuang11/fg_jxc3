package com.atguigu.jxc.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 客户实体
 */
@Data
@Component
public class Customer {

  private Integer customerId;
  private String customerName;
  private String contacts;
  private String phoneNumber;
  private String address;
  private String remarks;

}
