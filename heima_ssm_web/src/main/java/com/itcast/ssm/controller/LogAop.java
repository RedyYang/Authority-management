package com.itcast.ssm.controller;

import com.itcast.ssm.domain.SysLog;
import com.itcast.ssm.service.ISysLogService;
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
    private Class aClass;
    private Method method;

    //前置通知 主要是获取开始时间和执行的类是哪一个
    @Before("execution(* com.itcast.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        aClass = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        //获取具体执行的方法对象
        if(args == null||args.length == 0){
            method = aClass.getMethod(methodName);//只能获取无参数的方法
        }else {
            Class[] classArgs = new Class[args.length];
            for(int i=0;i<args.length;i++){
                classArgs[i] = args[i].getClass();
            }
            aClass.getMethod(methodName,classArgs);
        }

    }
    //后置通知
    @After("execution(* com.itcast.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception{
        long time = new Date().getTime()-visitTime.getTime();//获取访问时长
        //获取URL
        String url = "";
        if(aClass != null&&method != null&&aClass!=LogAop.class){
            RequestMapping classAnnotation  = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
            if(classAnnotation !=null){
                String[] classValue = classAnnotation.value();
                //获取方法上的value值
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];
                    //获取访问的ip地址
                    String ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
                    User user = (User)context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将日志相关系信息封装到Syslog中
                    SysLog syslog = new SysLog();
                    syslog.setExecutionTime(time);
                    syslog.setIp(ip);
                    syslog.setMethod("[类名]"+aClass.getName()+"[方法名]"+method.getName());
                    syslog.setUrl(url);
                    syslog.setUsername(username);
                    syslog.setVisitTime(visitTime);

                    //调用service完成操作
                    sysLogService.save(syslog);

                }
            }
        }
    }
}
