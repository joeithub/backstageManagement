package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    @RolesAllowed("USER")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<UserInfo> list= userService.findAll();
        modelAndView.addObject("userList",list);
        modelAndView.setViewName("userList");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username=='tom'")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView modelAndView =new ModelAndView();
     UserInfo userInfo = userService.findById(id);
     modelAndView.addObject("user",userInfo);
     modelAndView.setViewName("userShow");
     return modelAndView;
    }


    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserAndAllRole(@RequestParam(name = "id" ,required = true) String userId){
        ModelAndView modelAndView = new ModelAndView();
        // 根据用户ID查询用户
        UserInfo userInfo = userService.findById(userId);
        //根据用户ID查询用户可添加的角色
       List<Role> roles=userService.findOtherRoles(userId);
       modelAndView.addObject("user",userInfo);
       modelAndView.addObject("roleList",roles);
       modelAndView.setViewName("userRoleAdd");
       return modelAndView;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId" ,required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds){
        for (String roleId : roleIds) {
             userService.addRoleToUser(userId,roleId);
        }
        return "redirect:findAll.do";
    }
}
