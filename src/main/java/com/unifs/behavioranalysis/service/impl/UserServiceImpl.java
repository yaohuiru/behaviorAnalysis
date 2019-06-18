package com.unifs.behavioranalysis.service.impl;

import com.unifs.behavioranalysis.bean.AreaInfo;
import com.unifs.behavioranalysis.bean.User;
import com.unifs.behavioranalysis.dao.AreaInfoMapper;
import com.unifs.behavioranalysis.dao.UserMapper;
import com.unifs.behavioranalysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AreaInfoMapper areaInfoMapper;
    @Override
    public List<User> getAllUser()
    {
        return userMapper.selectAll();
    }

    @Override
    public void insertUsertotable(User user)
    {
        userMapper.insert(user);
    }

    @Override
    public void parseList()
    {

        List<AreaInfo> areaInfos = areaInfoMapper.selectByParentId("0");
        System.out.println(areaInfos);


    }
}
