package com.gr.config;

import com.alibaba.fastjson.JSONObject;
import com.gr.pojo.UmsUser;
import com.gr.util.MyEmail;
import com.gr.util.ResultJson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Aspect
public class AdminAop {
    //    // 前置
//    @Before(value = "execution(* com.gr.controller.UmsUserController.list(..))")
//    public void before() {
//        System.out.println("调用方法之前");
//    }

    // 通过RabbitTemplate发送请求到消息队列
    @Resource
    RabbitTemplate rabbitTemplate;

    // 后置
    @AfterReturning(value = "execution(* com.gr.controller.UmsUserController.getCode(..))")
    public void after(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        UmsUser user = (UmsUser) args[0];
        MyEmail myEmail = new MyEmail(user.getEmail()
                , "这是您的验证码：" + user.getLoginName()
                , "系统消息：");
        rabbitTemplate.convertAndSend("email", JSONObject.toJSONString(myEmail));
    }

//    // 环绕；代替了原方法
//    @Around(value = "execution(* com.gr.controller.UmsUserController.list(..))")
//    Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("进入环绕");
//        Object result = joinPoint.proceed();
//        System.out.println("方法后");
//        System.out.println(result);
//        return result;
//    }
}
