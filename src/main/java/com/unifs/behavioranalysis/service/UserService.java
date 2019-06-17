package com.unifs.behavioranalysis.service;

import com.unifs.behavioranalysis.bean.User;

import java.util.List;

/**
 * @ClassName: UserService
 * @Author: fanweiqiang
 * @Date: 2019/6/17 14:26
 * @Description: TODO
 */
public interface UserService {
    int addUser(User user);

    int updateUser(User user);

    List<User> allUsers();

    User getUser(String userNum);

    int delUser(String userNum);
}
