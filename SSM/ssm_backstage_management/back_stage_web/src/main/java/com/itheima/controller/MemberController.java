package com.itheima.controller;

import com.itheima.domain.Member;
import com.itheima.service.IMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class MemberController {

    private IMemberService memberService;

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView modelAndView = new ModelAndView();
        Member member =  memberService.findById(id);
        modelAndView.addObject("memberList",member);
        modelAndView.setViewName("memberList");
        return modelAndView;
    }
}
