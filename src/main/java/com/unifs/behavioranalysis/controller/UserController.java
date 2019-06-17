package com.unifs.behavioranalysis.controller;


import com.unifs.behavioranalysis.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "UserController", description = "用户操作控制层")
@RequestMapping(value = "/user")
public class UserController
{

    @PostMapping(value = "/add")
    public void addUser(@RequestBody @ApiParam(value = "添加用户信息的对象",required = true) User user){

        System.out.println(user);

       // return user;
    }



}
