package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IProductController {

    @RequestMapping("/findAll.do")
    public String findAll(Model model){




        return "";
    }
}
