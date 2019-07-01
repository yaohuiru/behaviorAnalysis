package com.unifs.behavioranalysis.controller;

import com.unifs.behavioranalysis.base.Resp;
import com.unifs.behavioranalysis.bean.AreaInfo;
import com.unifs.behavioranalysis.bean.User;
import com.unifs.behavioranalysis.enums.RespCode;
import com.unifs.behavioranalysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;

/**
 * @Classname: Usercontroller
 * @User: 19911
 * @date: 2019/6/17 15:44
 * @discription: 用户管理模块
 */

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

//    登录
    @PostMapping("login")
    @ResponseBody
    public Resp login(@RequestParam("userNum") String userNum,
                      @RequestParam("password") String password){
       if(StringUtils.isEmpty(userNum) || StringUtils.isEmpty(password)) {
           return new Resp(RespCode.DEFAULT, "员工编号和密码不能为空");
       }
       User user = userService.selectchange(userNum);
       if(user == null){
           return new Resp(RespCode.DEFAULT, "不存在该员工");
       }

       if(!password.equals(user.getPassword())) {
           return new Resp(RespCode.DEFAULT, "密码错误");
       }

       request.getSession().setAttribute("LOGIN_USER", user);

       return new Resp(RespCode.SUCCESS, user);
    }

//    查询后转换地区字符
    @PostMapping("selectu")
    @ResponseBody
    public User selectu(@RequestBody String userNum){
        return userService.selectchange(userNum);
    }

//    新增
    @PostMapping("insert")
    @ResponseBody
    public String userinsert(@RequestBody User user){
        if (StringUtils.isEmpty(user.getUserNum())) {
            return "新增失败，员工编号不能为空";
        }
        User tempUser = userService.userselect(user.getUserNum());
        if (tempUser != null) {
            return "新增失败，员工编号已存在";
        }
        int ret = userService.userinsert(user);
        if (ret > 0) {
            return "新增成功";
        }else {
            return "新增失败";
        }
    }
//    条件查询
    @PostMapping("select")
    @ResponseBody
    public User userselect(@RequestBody String userNum){
        return userService.userselect(userNum);
    }
//    查询所有数据
    @PostMapping("selectall")
    @ResponseBody
    public List<User> userselectall(){
        return userService.userselectall();
    }
//    修改
    @PostMapping("update")
    @ResponseBody
    public String userupdate(@RequestBody User user){
        int ret = userService.userupdate(user);
        if (ret > 0) {
            return "修改成功";
        }else {
            return "修改失败";
        }

    }
//    删除
    @PostMapping("delete")
    @ResponseBody
    public String userdelete(@RequestParam(value = "userNum") String userNum){
        if(StringUtils.isEmpty(userNum)) {
            return "删除失败，员工编号为空";
        }
        User user = userService.userselect(userNum);
        if(user == null) {
            return "删除失败，没有该员工";
        }
        int ret = userService.userdelete(userNum);
        if(ret > 0) {
            return "删除成功";
        }else {
            return "删除失败";
        }
    }


    @GetMapping("/getArea")
    @ResponseBody
    public Resp getArea()
    {
        List<AreaInfo> areas = userService.selectAllArea();
        return new Resp(RespCode.SUCCESS,areas);

    }
}
