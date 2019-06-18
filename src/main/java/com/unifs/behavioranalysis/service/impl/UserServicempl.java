package com.unifs.behavioranalysis.service.impl;

import com.unifs.behavioranalysis.bean.User;
import com.unifs.behavioranalysis.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @Classname: UserService
 * @User: 19911
 * @date: 2019/6/17 14:16
 * @discription: 用户管理Service层方法
 */

@Service
public class UserServicempl {
    @Resource
    private UserMapper userMapper;


//    新增用户：userinsert
    public void userinsert(User user){
        user.setUserId( UUID.randomUUID().toString().replace("-", ""));
        userMapper.insert(user);
    }
//    删除用户：userdelete
    public void  userdelete(String id){
        userMapper.deleteByPrimaryKey(id);
    }
//    修改用户：userupdate
    public void userupdate(User user){
        userMapper.updateByPrimaryKey(user);
    }
//    查询所有：userselectall
    public List<User> userselectall(){
        return userMapper.selectAll();

    }
//    查询用户：userselect
    public List<User> userselect(User user){
        return userMapper.selectByPrimaryKey(user);
    }


}
