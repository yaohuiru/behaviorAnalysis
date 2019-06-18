package com.unifs.behavioranalysis.controller;

import com.unifs.behavioranalysis.bean.User;
import com.unifs.behavioranalysis.service.UserService;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.List;

/**
 * @Classname: Usercontroller
 * @User: 19911
 * @date: 2019/6/17 15:44
 * @discription: todo
 */

@RestController
@RequestMapping("user")
public class Usercontroller {
    @Autowired
    private UserService userService;
//    新增
    @PostMapping("insert")
    public String userinsert(@RequestBody User user){
        userService.userinsert(user);
        return "新增成功";
    }
//    条件查询
    @PostMapping("select")
    public List<User> userselect(@RequestBody User user){
        return userService.userselect(user);
    }
//    查询所有数据
    @PostMapping("selectall")
    public List<User> userselectall(){
        return userService.userselectall();
    }
//    修改
    @PostMapping("update")
    public String userupdate(@RequestBody User user){
        userService.userupdate(user);
        return "修改成功";
    }
//    删除
    @PostMapping("delete")
    public String userdelete(@RequestBody String id){
        userService.userdelete(id);
        return "删除成功";
    }


}
