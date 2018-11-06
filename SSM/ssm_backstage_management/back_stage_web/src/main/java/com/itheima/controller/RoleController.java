package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public String findAll(Model model){

       List<Role> roleList =roleService.findAll();
       model.addAttribute("roleList",roleList);
       return "roleList";
    }

    @RequestMapping("/save.do")
    public String save(Role role){

        roleService.save(role);

        return "redirect:findAll.do";

    }
}
