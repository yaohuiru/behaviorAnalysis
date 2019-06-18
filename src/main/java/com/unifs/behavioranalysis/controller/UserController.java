package com.unifs.behavioranalysis.controller;


import com.unifs.behavioranalysis.base.Resp;
import com.unifs.behavioranalysis.bean.User;
import com.unifs.behavioranalysis.enums.RespCode;
import com.unifs.behavioranalysis.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "UserController", description = "用户操作控制层")
@RequestMapping(value = "/user")
public class UserController
{
    @Autowired
    private UserServiceImpl userserviceimpl;
    @PostMapping(value = "/add")
    public Resp addUser(@RequestBody @ApiParam(value = "添加用户信息的对象",required = true) User user){

        System.out.println(user);

        userserviceimpl.insertUsertotable(user);


        Resp resp = new Resp(RespCode.SUCCESS,"哈哈");
        return resp;

       // return user;
    }

    @GetMapping(value = "/getTable")
    @ApiOperation(value = "获取新闻列表")
    public void getTable(){


        userserviceimpl.parseList();

//        return newsList.getList();
    }

}
