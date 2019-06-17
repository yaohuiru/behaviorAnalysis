package com.unifs.behavioranalysis.service;

import com.unifs.behavioranalysis.bean.User2;
import com.unifs.behavioranalysis.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname: UserService
 * @User: 19911
 * @date: 2019/6/17 14:16
 * @discription: 用户管理Service层方法
 */

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;


//    新增用户：userinsert
    public void userinsert(User2 user_i){
        userMapper.insert(user_i);
    }
//    删除用户：userdelete
    public void  userdelete(){

    }
//    修改用户：userupdate
    public void userupdate(){

    }
//    查询用户：userselect
    public void userselect(){

        
    }


}
