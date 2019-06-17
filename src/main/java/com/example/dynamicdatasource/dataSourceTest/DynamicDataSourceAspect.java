package com.example.dynamicdatasource.dataSourceTest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by meridian on 2018/9/26.
 */

@Aspect
@Component
public class DynamicDataSourceAspect {
    @Pointcut("execution(public * com.example.dynamicdatasource..*.save(..))")
    public void createDataSource(){}

    @Before("createDataSource()")
    public void doBefore(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uid = request.getParameter("id");
        DataSourceContextHolder.setDB(uid);
    }

    @After("createDataSource()")
    public void doAfter(){
        DataSourceContextHolder.clearDB();
//        DynamicDataSourceOfDruid.closeConnection();
    }

}
