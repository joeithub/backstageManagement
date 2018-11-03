package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;


public interface IOrderService {

    /*查询所有订单*/
    public List<Orders> findAll(int page, int size);
}
