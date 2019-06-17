package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.User;

import java.util.List;

public interface UserMapper {
    int deleteByUserNum(String userNum);

    int insert(User record);

    User selectByUserNum(String userNum);

    List<User> selectAll();

    int updateByUserNum(User record);
}