package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Secured("ROLE_ADMIN")
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
     ModelAndView modelAndView =new ModelAndView();
        List<Product> products = productService.findAll();
        modelAndView.addObject("productList",products);
        modelAndView.setViewName("productList");
        return  modelAndView;
    }
    @RequestMapping("/save.do")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll.do";
    }
}
