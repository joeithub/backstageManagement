package com.itheima.controller;


import com.itheima.domain.Orders;
import com.itheima.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @RequestMapping("/findAll.do")
    public String findAll(Model model){
        List<Orders> orders = orderService.findAll();
        model.addAttribute("ordersList",orders);
        return "orderList";
    }
}
