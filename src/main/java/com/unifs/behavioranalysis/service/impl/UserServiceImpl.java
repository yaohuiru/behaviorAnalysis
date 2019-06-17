package com.unifs.behavioranalysis.service.impl;

import com.unifs.behavioranalysis.bean.User;
import com.unifs.behavioranalysis.dao.UserMapper;
import com.unifs.behavioranalysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService
{

    @Autowired
    private UserMapper usermapper;

    @Override
    public List<User> getAllUser()
    {


        return null;
    }
}
