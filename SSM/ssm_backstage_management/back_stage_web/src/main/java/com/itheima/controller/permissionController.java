package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class permissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView modelAndView =new ModelAndView();
       List<Permission>  list = permissionService.findAll();
       modelAndView.setViewName("permissionList");
       modelAndView.addObject("permissionList",list);
       return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission ){
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

}
