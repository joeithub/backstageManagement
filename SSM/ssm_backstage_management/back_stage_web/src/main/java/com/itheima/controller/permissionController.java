package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class permissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public String findAll(Model model){
       List<Permission>  list = permissionService.findAll();
       model.addAttribute("permissionList",list);
       return "permissionList";
    }

    @RequestMapping("/save.do")
    public String save(Permission permission ){
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

}
