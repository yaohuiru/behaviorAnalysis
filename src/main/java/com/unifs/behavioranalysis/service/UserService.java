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
    User selectchange(String userNum);

    //    新增用户：userinsert
    int userinsert(User user);

    //    删除用户：userdelete
    int  userdelete(String userNum);

    //    更新用户：userupdate
    int userupdate(User user);

    //    查询所有：userselectall
    List<User> userselectall();

    //    查询用户：userselect
    User userselect(String userNum);

 	List<HashMap<String, Object>> parseList();
}
