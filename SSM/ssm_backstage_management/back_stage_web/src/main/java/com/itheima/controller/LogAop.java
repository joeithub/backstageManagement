package com.itheima.controller;

import com.itheima.domain.Syslog;
import com.itheima.domain.UserInfo;
import com.itheima.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;
    private  Class  clazz ;
    private Method method;



    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint jp)throws Exception{
        visitTime=new Date();//记录访问的时间
        clazz=jp.getTarget().getClass();//具体要访问的类
        String methodName = jp.getSignature().getName();//具体要访问的方法名称
        Object[] args = jp.getArgs();//获取访问的方法的参数


        //获取具体要执行的方法的method对象
        if (args == null || args.length == 0){
            method=clazz.getMethod(methodName);//只能获取无参数的方法
        }else{
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,classArgs);
        }

    }


    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint jp)throws Exception{
        //获取访问的时长
        long time = new Date().getTime()- visitTime.getTime();
        //获取访问的URL
        String url = String.valueOf(request.getRequestURL());

        /*
        拼接类上注解与方法上注解的方式来获取
        String url = "";
        if (clazz!=null&&method!=null&clazz!=LogAop.class){
            //获取@RequestMapping("/orders") 强转成 RequestMapping
            RequestMapping clazzAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            //获取注解的值即"/orders" 注意它是数组从里面取出
            if (clazzAnnotation!=null){
                String[] value = clazzAnnotation.value();
                String classValue = value[0];
                //获取方法上的@RequestMapping
                RequestMapping methodAnnotation =(RequestMapping) method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String[] mvalue = methodAnnotation.value();
                    String methodValue = mvalue[0];
                    url=classValue+methodValue;
                }
            }
     }
*/
        //获取访问的ip
        String ip = request.getRemoteAddr();


        //获取操作用户通过Spring框架获取
        SecurityContext context = SecurityContextHolder.getContext();
        User principal = (User)context.getAuthentication().getPrincipal();
        String username = principal.getUsername();

        /*
        // 可以通过securityContext获取，也可以从request.getSession中获取
        User user = (User) request.getSession().getAttribute ("SPRING_SECURITY_CONTEXT");
        String username = user.getUsername();
        */
        Syslog syslog = new Syslog();
        syslog.setUsername(username);
        syslog.setExecutionTime(time);
        syslog.setIp(ip);
        syslog.setVisitTime(visitTime);
        syslog.setUrl(url);

        //注意一旦用了反射就不能在用model了只能用modelandview 否则得不出method
        syslog.setMethod("类名"+clazz.getName()+"方法名"+method.getName());

        //调用service 的保存方法
        sysLogService.save(syslog);



    }

}
