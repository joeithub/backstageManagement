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
    public String findAll(Model model){
       List<UserInfo> list= userService.findAll();
       model.addAttribute("userList",list);
        return "userList";
    }

    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username=='tom'")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public String findById(String id,Model model){
     UserInfo userInfo = userService.findById(id);
     model.addAttribute("user",userInfo);
     return "userShow";
    }


    @RequestMapping("/findUserByIdAndAllRole.do")
    public String findUserAndAllRole(Model model,@RequestParam(name = "id" ,required = true) String userId){
        // 根据用户ID查询用户
        UserInfo userInfo = userService.findById(userId);
        //根据用户ID查询用户可添加的角色
       List<Role> roles=userService.findOtherRoles(userId);
       model.addAttribute("user",userInfo);
       model.addAttribute("roleList",roles);
       return"userRoleAdd";
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId" ,required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds){
        for (String roleId : roleIds) {
             userService.addRoleToUser(userId,roleId);
        }
        return "redirect:findAll.do";
    }
}
