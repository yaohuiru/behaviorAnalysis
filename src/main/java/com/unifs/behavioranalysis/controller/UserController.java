package com.unifs.behavioranalysis.controller;

import com.unifs.behavioranalysis.base.Resp;
import com.unifs.behavioranalysis.bean.User;
import com.unifs.behavioranalysis.enums.RespCode;
import com.unifs.behavioranalysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
            return new Resp(RespCode.DEFAULT, "员工编号为必填项");
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

    @GetMapping("login")
    @ResponseBody
    public Resp login(@RequestParam(value = "userNum", required = false)String userNum,
                      HttpServletRequest request) {
        if(userNum == null || "".equals(userNum)) {
            return new Resp(RespCode.DEFAULT, "员工编号为必填项");
        }

        User user = userService.getUser(userNum);
        if (user == null) {
            return new Resp(RespCode.DEFAULT, "用户未注册");
        }

        request.getSession().setAttribute("LOGIN_USER", user);

        return new Resp(RespCode.SUCCESS, "登陆成功");
    }

}
