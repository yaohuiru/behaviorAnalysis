package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.User2;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User2 record);

    User2 selectByPrimaryKey(String userId);

    List<User2> selectAll();

    int updateByPrimaryKey(User2 record);
}