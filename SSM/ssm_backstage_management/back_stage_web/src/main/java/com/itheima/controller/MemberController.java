package com.itheima.controller;

import com.itheima.domain.Member;
import com.itheima.service.IMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    private IMemberService memberService;

    @RequestMapping("/findById.do")
    public String findById(String id,Model model){
    Member member =  memberService.findById(id);
    model.addAttribute("memberList",member);
        return "memberList";
    }
}
