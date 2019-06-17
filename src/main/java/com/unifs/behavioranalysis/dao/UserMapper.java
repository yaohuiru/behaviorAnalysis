package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.User2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;



@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User2 record);

    User2 selectByPrimaryKey(String userId);

    List<User2> selectAll();

    int updateByPrimaryKey(User2 record);
}