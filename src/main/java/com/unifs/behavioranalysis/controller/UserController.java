package com.unifs.behavioranalysis.controller;

import com.unifs.behavioranalysis.base.Resp;
import com.unifs.behavioranalysis.bean.User;
import com.unifs.behavioranalysis.enums.RespCode;
import com.unifs.behavioranalysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: UserController
 * @Author: fanweiqiang
 * @Date: 2019/6/17 14:29
 * @Description: TODO
 */
@Controller("user")
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/adduser")
    @ResponseBody
    public Resp addUser(@RequestBody User user) {
        if(StringUtils.isEmpty(user.getUserNum())||StringUtils.isEmpty(user.getPassword())) {
            return new Resp(RespCode.DEFAULT, "员工编号和密码为必填项");
        }
        //生成主键
        String primaryKey = UUID.randomUUID().toString().replace("-", "");
        user.setUserId(primaryKey);

        if(userService.addUser(user) > 0) {
            return new Resp(RespCode.SUCCESS, user);
        }else {
            return new Resp(RespCode.DEFAULT, "创建用户失败");
        }
    }

    @PostMapping(value = "/updateuser")
    @ResponseBody
    public Resp updateUser(@RequestBody User user) {
        if (user.getUserNum() == null || "".equals(user.getUserNum())) {
            return new Resp(RespCode.DEFAULT, "用户编号为必填项");
        }

        if(userService.updateUser(user) > 0) {
            return new Resp(RespCode.SUCCESS, user);
        }else {
            return new Resp(RespCode.DEFAULT, "更新用户信息失败");
        }
    }

    @GetMapping("/allusers")
    @ResponseBody
    public Resp listUser() {
        List<User> users = userService.allUsers();
        return new Resp(RespCode.SUCCESS, users);
    }

    @GetMapping("/getuser")
    @ResponseBody
    public Resp getUser(@RequestParam("userNum") String userNum) {
        if(userNum == null || "".equals(userNum)) {
            return new Resp(RespCode.DEFAULT, "员工编号为 必填项");
        }
        User user = userService.getUser(userNum);
        if (user == null) {
            return new Resp(RespCode.SUCCESS, "没有该员工得信息");
        } else {
            return new Resp(RespCode.SUCCESS, user);
        }
    }

    @GetMapping("/deleteuser")
    @ResponseBody
    public Resp deleteUser(@RequestParam(value = "userNum") String userNum) {
        if(userNum == null || "".equals(userNum)) {
            return new Resp(RespCode.DEFAULT, "员工编号为必填项");
        }

        if(userService.delUser(userNum) > 0) {
            return new Resp(RespCode.SUCCESS, "删除成功");
        }else {
            return new Resp(RespCode.DEFAULT, "删除失败");
        }
    }

    @PostMapping(value = "login")
    @ResponseBody
    public Resp login(@RequestParam(value = "userNum") String userNum,
                      @RequestParam(value = "password") String password,
                      HttpServletRequest request) {
        if(StringUtils.isEmpty(userNum) || StringUtils.isEmpty(password)) {
            return new Resp(RespCode.DEFAULT, "请输入员工编号和密码");
        }

        User user = userService.getUser(userNum);
        if (user == null) {
            return new Resp(RespCode.DEFAULT, "用户未注册");
        }

        if(!password.equals(user.getPassword())) {
            return new Resp(RespCode.DEFAULT, "密码错误");
        }

        request.getSession().setAttribute("LOGIN_USER", user);

        return new Resp(RespCode.SUCCESS, "登陆成功");
    }

}
