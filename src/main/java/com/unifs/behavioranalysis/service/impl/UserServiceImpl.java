package com.unifs.behavioranalysis.service.impl;

import com.unifs.behavioranalysis.bean.User;
import com.unifs.behavioranalysis.dao.UserMapper;
import com.unifs.behavioranalysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Author: fanweiqiang
 * @Date: 2019/6/17 14:27
 * @Description: TODO
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByUserNum(user);
    }

    @Override
    public List<User> allUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User getUser(String userNum) {
        return userMapper.selectByUserNum(userNum);
    }

    @Override
    public int delUser(String userNum) {
        return userMapper.deleteByUserNum(userNum);
    }
}
