package com.example.dynamicdatasource.controller;

import com.example.dynamicdatasource.dataSourceTest.DataSourceContextHolder;
import com.example.dynamicdatasource.empty.UserDO;
import com.example.dynamicdatasource.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@RequestMapping("api/cxy")
public class UserController {

    @Resource
    private UserService userService;


    // http://localhost:8088/api/cxy/save?id=1&name=q&age=5&gender=1&telphone=54551&registerMode=5&encrptPassword=44
    @RequestMapping("/save")
    @ResponseBody
    public String save(UserDO user) {
        // 切换数据源，之后的请求会去连接池中取数据库连接，连接池不与线程绑定
        DataSourceContextHolder.setDB("test");
        userService.add(user);
        return "ok";
    }

    // http://localhost:8088/api/cxy/query?id=22&key=1
    @RequestMapping("/query")
    @ResponseBody
    public String query(Integer id, Integer key) {
        if (key == 1){
            DataSourceContextHolder.setDB("miaosha");
        }else {
            DataSourceContextHolder.setDB("test");
        }
        UserDO userDO = userService.query(id);
        System.out.println(userDO.toString());
        return "ok";
    }

}

