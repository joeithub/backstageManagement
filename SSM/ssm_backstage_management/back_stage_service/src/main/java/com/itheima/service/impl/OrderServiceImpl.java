package com.itheima.service.impl;

import com.itheima.dao.IOrderDao;
import com.itheima.domain.Orders;
import com.itheima.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    @Override
    public List<Orders> findAll() {
        return orderDao.findAll();
    }
}
