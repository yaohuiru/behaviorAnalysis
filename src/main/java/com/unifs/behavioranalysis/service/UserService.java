package com.unifs.behavioranalysis.service;


import com.unifs.behavioranalysis.bean.User;

import java.util.HashMap;
import java.util.List;

/**
 * @Classname: UserService123
 * @User: 19911
 * @date: 2019/6/18 10:55
 * @discription: todo
 */
public interface UserService {

//    查询后转换地区名称
    List<User> selectchange(User user);

    //    新增用户：userinsert
    void userinsert(User user);

    //    删除用户：userdelete
    void  userdelete(String id);
    //    更新用户：userupdate
    void userupdate(User user);
    //    查询所有：userselectall
    List<User> userselectall();
    //    查询用户：userselect
    List<User> userselect(User user);

 	List<HashMap<String, Object>> parseList();
}
