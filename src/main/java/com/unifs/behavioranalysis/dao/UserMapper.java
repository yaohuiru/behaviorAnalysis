package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.User;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    List<User> selectByPrimaryKey(User user);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}