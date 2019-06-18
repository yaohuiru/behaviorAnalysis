package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.User;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;



@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    User selectByPrimaryKey(String userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}