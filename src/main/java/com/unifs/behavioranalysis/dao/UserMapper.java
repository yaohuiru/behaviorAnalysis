package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    List<User> selectByPrimaryKey(User user);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}