package com.snowwolf.dtotool.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/27 21:42
 * @modified by:
 * @versions：0.1.0
 */
@Aspect
@Component
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    @Before("service() || tool()")
    public void before(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        //class_name 类名
        logger.info("class_name={}","当前类为："+className);
        //method 进入的方法名
        logger.info("method={}","当前方法为："+className+""+joinPoint.getSignature().getName());
        //入参为
        logger.info("data={}",joinPoint.getArgs());
    }
    @After("execution(public *  com.snowwolf.dtotool.service.impl..*(..))")
    public void afer(){

    }
    @AfterReturning(returning = "object",pointcut = "service() || tool()")
    public void getAferRetult(Object object){
        //获取方法返回的数据
        logger.info("retult={}","处理结果为:"+object);
    }

    @Pointcut("execution(public *  com.snowwolf.dtotool.service.impl..*(..))")
    public void service(){}
    @Pointcut("execution(public * com.snowwolf.dtotool.tool.BeanUtil.*(..))")
    public void tool(){}
}
