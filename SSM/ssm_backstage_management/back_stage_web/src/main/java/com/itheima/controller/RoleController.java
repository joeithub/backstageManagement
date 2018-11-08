package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList =roleService.findAll();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("roleList");
       return  modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll.do";
    }


    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findUserAndAllRole(@RequestParam(name = "id" ,required = true) String roleId){
        ModelAndView modelAndView =new ModelAndView();
        // 根据用户ID查询用户
        Role role =roleService.findById(roleId);
        //根据用户ID查询用户可添加的角色
        List<Permission> permissions=roleService.findOtherPermissions(roleId);
        modelAndView.addObject("role",role);
        modelAndView.addObject("permissionList",permissions);
        modelAndView.setViewName("rolePermissionAdd");

        return modelAndView;
    }


    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(value = "roleId",required = true)String roleId,@RequestParam(value = "ids",required = true) String[]permissionIds){

        for (String permissionId : permissionIds) {
            roleService.addPermissionToRole(roleId,permissionId);
        }
        return "redirect:findAll.do";
    }

}
