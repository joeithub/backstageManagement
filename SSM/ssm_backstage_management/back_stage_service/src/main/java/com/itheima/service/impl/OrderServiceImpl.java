package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
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
    public List<Orders> findAll(int page, int size) {

        /*pageNum当前页码 pageSize每页显示条数*/

  /*PageHelper分页必须放在执行sql的方法前 中间不能再有其他任何语句否则就失效了
  * 因为它实际上是在我们执行分也操作前帮我们拼了一个分页语句*/
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }

    @Override
    public Orders findById(String id) {
        Orders order = orderDao.findById(id);
        return order;
    }
}
