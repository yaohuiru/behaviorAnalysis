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

    //    新增用户：userinsert
    public void userinsert(User user);

    //    删除用户：userdelete
    public void  userdelete(String id);
    //    更新用户：userupdate
    public void userupdate(User user);
    //    查询所有：userselectall
    public List<User> userselectall();
    //    查询用户：userselect
    public List<User> userselect(User user);

 	public List<HashMap<String, Object>> parseList();
}
