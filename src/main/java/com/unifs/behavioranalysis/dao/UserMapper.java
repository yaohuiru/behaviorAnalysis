package com.unifs.behavioranalysis.dao;

import com.unifs.behavioranalysis.bean.User;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public interface UserMapper {
    int deleteByUserNum(String userNum);

    int insert(User user);

    User selectByUserNum(String userNum);

    List<User> selectAll();

    int updateByUserNum(User user);
}