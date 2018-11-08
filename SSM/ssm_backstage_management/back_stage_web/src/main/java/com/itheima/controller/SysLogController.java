package com.itheima.controller;

import com.itheima.domain.Syslog;
import com.itheima.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView modelAndView =new ModelAndView();
    List<Syslog> list=sysLogService.findAll();
    modelAndView.addObject("sysLogs",list);
    modelAndView.setViewName("syslogList");
    return modelAndView;
    }
}
